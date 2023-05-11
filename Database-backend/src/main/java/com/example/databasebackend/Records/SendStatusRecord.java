package com.example.databasebackend.Records;

import lombok.Getter;
import lombok.Setter;

public class SendStatusRecord
{
    @Getter @Setter
    private String operationStatus;
    @Getter @Setter
    private String message;
}
