package com.example.databasebackend.Records.Form10;

import com.example.databasebackend.Records.SendArrayStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form10SendRecord {
    @Getter @Setter
    private SendArrayStatusRecord statuses;
    @Getter @Setter
    private Form10SendList equipments;

    public Form10SendRecord() {
        equipments = new Form10SendList();
    }

    public void addEquipment(Form10SendElement equipment) {
        equipments.add(equipment);
    }

    public void addAllEquipment(List<Form10SendElement> equipmentList)
    {
        equipments.addAll(equipmentList);
    }
}
