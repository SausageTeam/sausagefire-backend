package com.sausage.app.dao.applicationWorkFlow.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.applicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.entity.ApplicationWorkFlow;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationWorkFlowImpl extends AbstractHibernateDAO<ApplicationWorkFlow> implements ApplicationWorkFlowDAO {
    public ApplicationWorkFlowImpl() { setClazz(ApplicationWorkFlow.class);}
}
