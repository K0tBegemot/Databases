package com.example.databasebackend.Records.Form13;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form13RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement workerId;
    @Getter @Setter
    private ValidRecvElement firstName;
    @Getter @Setter
    private ValidRecvElement secondName;
    @Getter @Setter
    private ValidRecvElement brigadeId;
    @Getter @Setter
    private ValidRecvElement startDate;
    @Getter @Setter
    private ValidRecvElement endDate;
    @Getter @Setter
    private ValidRecvElement employeeTypeName;
    @Getter @Setter
    private ValidRecvElement employeeTypeSpecName;
}
