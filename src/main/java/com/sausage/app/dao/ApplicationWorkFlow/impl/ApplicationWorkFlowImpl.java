package com.sausage.app.dao.ApplicationWorkFlow.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.entity.ApplicationWorkFlow;
import com.sausage.app.entity.Employee;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ApplicationWorkFlowImpl extends AbstractHibernateDAO<ApplicationWorkFlow> implements ApplicationWorkFlowDAO {

    private static final String GET_APPLICATION_WORK_FLOW_BY_EMPLOYEE = "FROM ApplicationWorkFlow WHERE employee = :employee";

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

}
