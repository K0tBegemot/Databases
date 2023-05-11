package com.example.databasebackend.Records.Form18;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form18RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement workshopName;
    @Getter @Setter
    private ValidRecvElement workshopSectionName;
    @Getter @Setter
    private ValidRecvElement sectionMasterId;
}
