package com.sausage.app.dao.ApplicationWorkFlow;

import com.sausage.app.entity.ApplicationWorkFlow;
import com.sausage.app.entity.Employee;

public interface ApplicationWorkFlowDAO {

    ApplicationWorkFlow getApplicationWorkFlowByEmployee(Employee employee);

    void setApplicationWorkFlow(ApplicationWorkFlow applicationWorkFlow);

}
