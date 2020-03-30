package com.sausage.app.domain.hr.dashboard;

import com.sausage.app.domain.common.GenericResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardGetResponse extends GenericResponse {

    Dashboard dashboard;

}
