package com.sausage.app.domain.hr.dashboard;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {

    List<Trouble> waitingList;

    List<Trouble> notifyList;

}
