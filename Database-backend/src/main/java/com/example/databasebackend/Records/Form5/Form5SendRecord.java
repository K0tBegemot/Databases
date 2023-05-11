package com.example.databasebackend.Records.Form5;

import com.example.databasebackend.Records.SendStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form5SendRecord
{
    @Getter @Setter
    private SendStatusRecord status;
    @Getter @Setter
    private Form5SendList workshopPersonal;

    public Form5SendRecord()
    {
        workshopPersonal = new Form5SendList();
    }

    public void addPersonal(Form5SendElement person)
    {
        workshopPersonal.add(person);
    }

    public void addAllPersonal(List<Form5SendElement> persons)
    {
        workshopPersonal.addAll(persons);
    }
}
