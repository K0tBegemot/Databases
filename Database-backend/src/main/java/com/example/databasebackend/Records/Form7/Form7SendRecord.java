package com.example.databasebackend.Records.Form7;

import com.example.databasebackend.Records.SendStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form7SendRecord
{
    @Getter @Setter
    private SendStatusRecord status;
    @Getter @Setter
    private Form7SendList testLabList;

    public Form7SendRecord()
    {
        testLabList = new Form7SendList();
    }

    public void addTestLab(Form7SendElement testLab)
    {
        testLabList.add(testLab);
    }

    public void addAllTestLabs(List<Form7SendElement> testLabs)
    {
        testLabList.addAll(testLabs);
    }
}
