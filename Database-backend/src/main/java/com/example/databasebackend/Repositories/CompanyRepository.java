package com.example.databasebackend.Repositories;

import com.example.databasebackend.Records.Form1.Form1RecvRecord;
import com.example.databasebackend.Records.Form1.Form1SendRecord;
import com.example.databasebackend.Records.Form10.Form10RecvRecord;
import com.example.databasebackend.Records.Form10.Form10SendRecord;
import com.example.databasebackend.Records.Form11.Form11RecvRecord;
import com.example.databasebackend.Records.Form12.Form12RecvRecord;
import com.example.databasebackend.Records.Form13.Form13RecvRecord;
import com.example.databasebackend.Records.Form14.Form14RecvRecord;
import com.example.databasebackend.Records.Form15.Form15RecvRecord;
import com.example.databasebackend.Records.Form16.Form16RecvRecord;
import com.example.databasebackend.Records.Form17.Form17RecvRecord;
import com.example.databasebackend.Records.Form18.Form18RecvRecord;
import com.example.databasebackend.Records.Form19.Form19RecvRecord;
import com.example.databasebackend.Records.Form2.Form2RecvRecord;
import com.example.databasebackend.Records.Form2.Form2SendRecord;
import com.example.databasebackend.Records.Form20.Form20RecvRecord;
import com.example.databasebackend.Records.Form21.Form21RecvRecord;
import com.example.databasebackend.Records.Form3.Form3RecvRecord;
import com.example.databasebackend.Records.Form3.Form3SendRecord;
import com.example.databasebackend.Records.Form4.Form4RecvRecord;
import com.example.databasebackend.Records.Form4.Form4SendRecord;
import com.example.databasebackend.Records.Form5.Form5RecvRecord;
import com.example.databasebackend.Records.Form5.Form5SendRecord;
import com.example.databasebackend.Records.Form6.Form6RecvRecord;
import com.example.databasebackend.Records.Form6.Form6SendRecord;
import com.example.databasebackend.Records.Form7.Form7RecvRecord;
import com.example.databasebackend.Records.Form7.Form7SendRecord;
import com.example.databasebackend.Records.Form8.Form8RecvRecord;
import com.example.databasebackend.Records.Form8.Form8SendRecord;
import com.example.databasebackend.Records.Form9.Form9RecvRecord;
import com.example.databasebackend.Records.Form9.Form9SendRecord;
import com.example.databasebackend.Records.SendStatusRecord;
import org.springframework.dao.DataAccessException;

public interface CompanyRepository
{
    public Form1SendRecord getForm1Response(Form1RecvRecord request);
    public Form2SendRecord getForm2Response(Form2RecvRecord request);
    public Form3SendRecord getForm3Response(Form3RecvRecord request);
    public Form4SendRecord getForm4Response(Form4RecvRecord request);
    public Form5SendRecord getForm5Response(Form5RecvRecord request);
    public Form6SendRecord getForm6Response(Form6RecvRecord request);
    public Form7SendRecord getForm7Response(Form7RecvRecord request);
    public Form8SendRecord getForm8Response(Form8RecvRecord request);
    public Form9SendRecord getForm9Response(Form9RecvRecord request);
    public Form10SendRecord getForm10Response(Form10RecvRecord request);
    public SendStatusRecord getForm11Response(Form11RecvRecord request);
    public SendStatusRecord getForm12Response(Form12RecvRecord request);
    public SendStatusRecord getForm13Response(Form13RecvRecord request);
    public SendStatusRecord getForm14Response(Form14RecvRecord request);
    public SendStatusRecord getForm15Response(Form15RecvRecord request);
    public SendStatusRecord getForm16Response(Form16RecvRecord request);
    public SendStatusRecord getForm17Response(Form17RecvRecord request);
    public SendStatusRecord getForm18Response(Form18RecvRecord request);
    public SendStatusRecord getForm19Response(Form19RecvRecord request);
    public SendStatusRecord getForm20Response(Form20RecvRecord request);
    public SendStatusRecord getForm21Response(Form21RecvRecord request);
}
