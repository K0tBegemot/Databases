package com.example.databasebackend.Records.Form15;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form15RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement testLabName;
}
