package com.sausage.app.dao.applicationWorkFlow;

import com.sausage.app.entity.ApplicationWorkFlow;
import com.sausage.app.entity.Employee;

import java.util.List;

public interface ApplicationWorkFlowDAO {

    ApplicationWorkFlow getApplicationWorkFlowByEmployee(Employee employee);

    void setApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow);

    List<ApplicationWorkFlow> getAllWaitingApplicationWorkFlow();

    List<ApplicationWorkFlow> getAllNotifyApplicationWorkFlow();
}
