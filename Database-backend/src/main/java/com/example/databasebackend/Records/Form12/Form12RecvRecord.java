package com.example.databasebackend.Records.Form12;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form12RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement brigadeId;
    @Getter @Setter
    private ValidRecvElement foremanId;
    @Getter @Setter
    private ValidRecvElement workshopName;
    @Getter @Setter
    private ValidRecvElement workshopSectionName;
}
