package com.sausage.app.domain.visaStatus;

import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatusPostResponse {

    ServiceStatus serviceStatus;

}
