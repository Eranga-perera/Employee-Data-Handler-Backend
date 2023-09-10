package com.example.EmployeeMS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {


    private int employeeID;
    private String employeeName;
    private String employeeAddress;
    private int employeeMNumber;
}
