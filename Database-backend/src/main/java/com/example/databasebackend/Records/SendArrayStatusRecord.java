package com.example.databasebackend.Records;

import lombok.Getter;
import lombok.Setter;

public class SendArrayStatusRecord
{
    @Getter @Setter
    private SendArrayStatusList statuses;

    public SendArrayStatusRecord()
    {
        statuses = new SendArrayStatusList();
    }
}
