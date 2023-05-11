package com.example.databasebackend.Records.Form9;

import com.example.databasebackend.Records.Form8.Form8SendElement;
import com.example.databasebackend.Records.SendArrayStatusRecord;
import com.example.databasebackend.Records.SendStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form9SendRecord
{
    @Getter @Setter
    private SendArrayStatusRecord statuses;
    @Getter @Setter
    private Form9SendList testers;

    public Form9SendRecord()
    {
        testers = new Form9SendList();
    }

    public void addTester(Form9SendElement tester)
    {
        testers.add(tester);
    }

    public void addAllTesters(List<Form9SendElement> testerList)
    {
        testers.addAll(testerList);
    }
}
