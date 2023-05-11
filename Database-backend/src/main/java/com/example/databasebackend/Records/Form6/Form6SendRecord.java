package com.example.databasebackend.Records.Form6;

import com.example.databasebackend.Records.SendStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form6SendRecord
{
    @Getter @Setter
    private SendStatusRecord status;
    @Getter @Setter
    private Form6SendList brigadeList;

    public Form6SendRecord()
    {
        brigadeList = new Form6SendList();
    }

    public void addWorker(Form6SendElement worker)
    {
        brigadeList.add(worker);
    }

    public void addAllWorkers(List<Form6SendElement> workers)
    {
        brigadeList.addAll(workers);
    }
}
