package com.example.databasebackend.Records.Form2;

import com.example.databasebackend.Records.SendStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form2SendRecord
{
    @Getter @Setter
    private SendStatusRecord status;
    @Getter @Setter
    private Form2SendList employeeList;

    public Form2SendRecord()
    {
        employeeList = new Form2SendList();
    }

    public void addEmployee(Form2SendElement employee)
    {
        employeeList.add(employee);
    }

    public void addAllEmployee(List<Form2SendElement> employees)
    {
        employeeList.addAll(employees);
    }
}
