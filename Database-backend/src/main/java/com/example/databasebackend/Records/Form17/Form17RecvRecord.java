package com.example.databasebackend.Records.Form17;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form17RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement testingId;
    @Getter @Setter
    private ValidRecvElement unitId;
    @Getter @Setter
    private ValidRecvElement testLabName;
    @Getter @Setter
    private ValidRecvElement testerId;
    @Getter @Setter
    private ValidRecvElement testPhaseName;
    @Getter @Setter
    private ValidRecvElement testEquipmentId;
    @Getter @Setter
    private ValidRecvElement startDate;
    @Getter @Setter
    private ValidRecvElement endDate;
}
