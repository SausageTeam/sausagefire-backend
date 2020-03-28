package com.sausage.app.dao.ApplicationWorkFlow.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.entity.ApplicationWorkFlow;
import com.sausage.app.entity.Employee;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowNotifyEnums.*;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowUploadEnums.*;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowTypeEnums.*;

@Repository
public class ApplicationWorkFlowImpl extends AbstractHibernateDAO<ApplicationWorkFlow> implements ApplicationWorkFlowDAO {

    private static final String GET_APPLICATION_WORK_FLOW_BY_EMPLOYEE = "FROM ApplicationWorkFlow WHERE employee = :employee";
    private static final String GET_WAITING_APPLICATION_WORK_FLOW = "FROM ApplicationWorkFlow WHERE type = :type AND upload = :waiting";
    private static final String GET_NOTIFY_APPLICATION_WORK_FLOW = "FROM ApplicationWorkFlow WHERE type = :type AND (upload = :require OR upload = :reject) AND notify = :notify";

    public ApplicationWorkFlowImpl() { setClazz(ApplicationWorkFlow.class);}

    @Override
    public ApplicationWorkFlow getApplicationWorkFlowByEmployee(Employee employee) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_APPLICATION_WORK_FLOW_BY_EMPLOYEE);
        query.setParameter("employee", employee);

        @SuppressWarnings("unchecked")
        List<ApplicationWorkFlow> applicationWorkFlowList = query.getResultList();

        return (applicationWorkFlowList.size() > 0) ? applicationWorkFlowList.get(0) : null;
    }

    @Override
    public void setApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow) {
        Session session = getCurrentSession();
        session.merge(applicationWorkFlow);
    }

    @Override
    public List<ApplicationWorkFlow> getAllWaitingApplicationWorkFlow() {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_WAITING_APPLICATION_WORK_FLOW);
        query.setParameter("type", OPT.getStr());
        query.setParameter("waiting", WAITING.getValue());

        @SuppressWarnings("unchecked")
        List<ApplicationWorkFlow> applicationWorkFlowList = query.getResultList();
        return applicationWorkFlowList;
    }

    @Override
    public List<ApplicationWorkFlow> getAllNotifyApplicationWorkFlow() {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_NOTIFY_APPLICATION_WORK_FLOW);
        query.setParameter("type", "OPT");
        query.setParameter("require", REQUIRE.getValue());
        query.setParameter("reject", REQUIRE.getValue());
        query.setParameter("notify", NOTIFIED.getValue());

        @SuppressWarnings("unchecked")
        List<ApplicationWorkFlow> applicationWorkFlowList = query.getResultList();
        return applicationWorkFlowList;
    }

}
