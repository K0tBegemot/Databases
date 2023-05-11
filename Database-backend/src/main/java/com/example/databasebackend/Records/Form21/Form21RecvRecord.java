package com.example.databasebackend.Records.Form21;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form21RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement testEquipmentId;
    @Getter @Setter
    private ValidRecvElement testEquipmentTypeName;
    @Getter @Setter
    private ValidRecvElement testLabName;
}
