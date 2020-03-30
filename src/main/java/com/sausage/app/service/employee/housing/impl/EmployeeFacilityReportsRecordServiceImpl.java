package com.sausage.app.service.employee.housing.impl;

import com.sausage.app.dao.Contact.ContactDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.FacilityReport.FacilityReportDAO;
import com.sausage.app.dao.FacilityReportDetail.FacilityReportDetailDAO;
import com.sausage.app.dao.House.HouseDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.housing.facilityReports.record.FacilityRecordComment;
import com.sausage.app.domain.employee.housing.facilityReports.record.FacilityRecordUpdate;
import com.sausage.app.domain.employee.housing.facilityReports.record.FacilityReportsRecord;
import com.sausage.app.domain.employee.housing.facilityReports.record.FacilityReportsRecordDetail;
import com.sausage.app.entity.*;
import com.sausage.app.service.employee.housing.EmployeeFacilityReportsRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeFacilityReportsRecordServiceImpl implements EmployeeFacilityReportsRecordService {

    private UserDAO userDAO;

    private ContactDAO contactDAO;

    private HouseDAO houseDAO;

    private FacilityReportDAO facilityReportDAO;

    private FacilityReportDetailDAO facilityReportDetailDAO;

    @Override
    @Transactional
    public FacilityReportsRecord getFacilityReportsRecord(int userId) {
        List<FacilityReportsRecordDetail> facilityReportsRecordDetailList = new ArrayList<>();
        List<FacilityReport> facilityReportList = facilityReportDAO.getAllFacilityReport();
        for (FacilityReport facilityReport : facilityReportList){
            Employee employee = facilityReport.getEmployee();
            Person person = employee.getPerson();
            String name = person.getFirstName();
            if (person.getPreferredName() != null){
                name = person.getPreferredName();
            }

            List<FacilityRecordComment> facilityRecordCommentList = new ArrayList<>();
            List<FacilityReportDetail> facilityReportDetailList = facilityReportDetailDAO.getFacilityReportDetailsByReport(facilityReport);
            for (FacilityReportDetail facilityReportDetail : facilityReportDetailList){
                String description = facilityReportDetail.getComments();
                Employee e = facilityReportDetail.getEmployee();
                Person p = e.getPerson();
                String createdUser = p.getFirstName();
                if (person.getPreferredName() != null){
                    createdUser = person.getPreferredName();
                }

                String commentDate = facilityReportDetail.getCreatedDateTime();
                if (facilityReportDetail.getModificationDateTime() != null){
                    commentDate = facilityReportDetail.getModificationDateTime();
                }

                FacilityRecordComment facilityRecordComment = FacilityRecordComment.builder()
                        .facilityReportsRecordDetailId(facilityReportDetail.getId())
                        .description(description)
                        .createdUser(createdUser)
                        .commentDate(commentDate)
                        .build();
                facilityRecordCommentList.add(facilityRecordComment);
            }

            FacilityReportsRecordDetail facilityReportsRecordDetail = FacilityReportsRecordDetail.builder()
                    .title(facilityReport.getTitle())
                    .description(facilityReport.getDescription())
                    .createdUser(name)
                    .reportDate(facilityReport.getReportDate())
                    .status(facilityReport.getStatus())
                    .facilityRecordCommentList(facilityRecordCommentList)
                    .build();
            facilityReportsRecordDetailList.add(facilityReportsRecordDetail);
        }
        return FacilityReportsRecord.builder()
                .facilityReportsRecordDetailList(facilityReportsRecordDetailList)
                .build();
    }

    @Override
    @Transactional
    public void setFacilityReportsRecord(int userId, FacilityRecordUpdate facilityRecordUpdate) {
        int facilityReportDetailId = facilityRecordUpdate.getFacilityReportDetailId();
        String description = facilityRecordUpdate.getDescription();
        FacilityReportDetail facilityReportDetail = facilityReportDetailDAO.getFacilityReportDetailById(facilityReportDetailId);
        facilityReportDetail.setComments(description);
        facilityReportDetailDAO.setFacilityReportDetail(facilityReportDetail);
    }

}
