package com.example.databasebackend.Records.Form16;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form16RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement productionOperationId;
    @Getter @Setter
    private ValidRecvElement unitId;
    @Getter @Setter
    private ValidRecvElement workshopName;
    @Getter @Setter
    private ValidRecvElement workshopSectionName;
    @Getter @Setter
    private ValidRecvElement brigadeId;
    @Getter @Setter
    private ValidRecvElement startDate;
    @Getter @Setter
    private ValidRecvElement endDate;
}
