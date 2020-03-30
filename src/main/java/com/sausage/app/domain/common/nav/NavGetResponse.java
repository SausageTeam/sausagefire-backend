package com.sausage.app.domain.common.nav;

import com.sausage.app.domain.common.GenericResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NavGetResponse extends GenericResponse {

    String redirectUrl;

    Nav nav;

}
