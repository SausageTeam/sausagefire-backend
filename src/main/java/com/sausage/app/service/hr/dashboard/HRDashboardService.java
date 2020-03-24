package com.sausage.app.service.hr.dashboard;

import com.sausage.app.domain.hr.dashboard.Dashboard;
import com.sausage.app.domain.hr.dashboard.Trouble;

import java.util.List;

public interface HRDashboardService {

    Dashboard getHRDashboard();

    void postHRDashboard(int employeeId);

}
