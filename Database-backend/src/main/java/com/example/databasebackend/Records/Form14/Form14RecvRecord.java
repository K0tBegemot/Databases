package com.example.databasebackend.Records.Form14;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form14RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement workshopName;
    @Getter @Setter
    private ValidRecvElement workshopForemanId;
}
