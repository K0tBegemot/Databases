package com.example.databasebackend.Records.Form20;

import com.example.databasebackend.Records.RequestTypeElement;
import com.example.databasebackend.Records.ValidRecvElement;
import lombok.Getter;
import lombok.Setter;

public class Form20RecvRecord
{
    @Getter @Setter
    private RequestTypeElement requestType;
    @Getter @Setter
    private ValidRecvElement workshopName;
    @Getter @Setter
    private ValidRecvElement workshopSectionName;
    @Getter @Setter
    private ValidRecvElement sectionForemanId;
}
