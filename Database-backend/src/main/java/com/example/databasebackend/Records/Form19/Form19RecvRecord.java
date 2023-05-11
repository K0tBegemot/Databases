package com.example.databasebackend.Records.Form19;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form19RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement testLabName;
    @Getter @Setter
    private ValidRecvElement testerId;
}
