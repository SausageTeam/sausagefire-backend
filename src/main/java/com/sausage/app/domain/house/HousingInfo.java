package com.sausage.app.domain.house;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class HousingInfo implements Serializable{
    private String address;
//    private List<Employee> employeeList;
}
