package com.example.databasebackend.Records.Form11;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form11RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement unitId;
    @Getter @Setter
    private ValidRecvElement productTypeName;
    @Getter @Setter
    private ValidRecvElement productTypeModelName;
}
