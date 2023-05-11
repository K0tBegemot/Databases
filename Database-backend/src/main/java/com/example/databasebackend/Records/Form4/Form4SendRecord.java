package com.example.databasebackend.Records.Form4;

import com.example.databasebackend.Records.SendStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form4SendRecord{
    @Getter @Setter
    private SendStatusRecord status;
    @Getter @Setter
    private Form4SendList brigadeList;

    public Form4SendRecord()
    {
        brigadeList = new Form4SendList();
    }

    public void addBrigade(Form4SendElement brigade)
    {
        brigadeList.add(brigade);
    }

    public void addAllBrigades(List<Form4SendElement> brigades)
    {
        brigadeList.addAll(brigades);
    }
}
