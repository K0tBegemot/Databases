package com.example.databasebackend.Records.Form3;

import com.example.databasebackend.Records.SendStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form3SendRecord
{
    @Getter @Setter
    private SendStatusRecord status;
    @Getter @Setter
    private Form3SendList workshopSectionList;

    public Form3SendRecord()
    {
        workshopSectionList = new Form3SendList();
    }

    public void addWorkshopSection(Form3SendElement workshopSection)
    {
        workshopSectionList.add(workshopSection);
    }

    public void addAllWorkshopSections(List<Form3SendElement> list)
    {
        workshopSectionList.addAll(list);
    }
}
