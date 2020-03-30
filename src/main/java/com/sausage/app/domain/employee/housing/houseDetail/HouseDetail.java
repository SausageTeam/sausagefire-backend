package com.sausage.app.domain.employee.housing.houseDetail;

import com.sausage.app.domain.common.AddressDomain;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseDetail {

    AddressDomain addressDomain;

    List<Resident> residentList;

}
