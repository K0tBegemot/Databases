package com.example.databasebackend.Controllers;

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
import com.example.databasebackend.Repositories.CompanyRepositoryImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin( origins = {"*"}, methods = {RequestMethod.POST})
@RestController
public class FetchDataController {
    Logger logger = LoggerFactory.getLogger(FetchDataController.class);
    @Autowired
    CompanyRepositoryImpl repository;

    @PostMapping (value = "/form/0", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form1SendRecord receiveForm1Req(@RequestBody Form1RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/0 controller receive request\n");
        Form1SendRecord newRecord = null;
        try{
            newRecord = repository.getForm1Response(recvRecord);
            logger.info("/form/0 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/0 controller can't get Form1 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/1", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form2SendRecord receiveForm2Req(@RequestBody Form2RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/1 controller receive request\n");
        Form2SendRecord newRecord = null;
        try{
            newRecord = repository.getForm2Response(recvRecord);
            logger.info("/form/1 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/1 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/2", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form3SendRecord receiveForm3Req(@RequestBody Form3RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/2 controller receive request\n");
        Form3SendRecord newRecord = null;
        try{
            newRecord = repository.getForm3Response(recvRecord);
            logger.info("/form/2 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/2 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/3", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form4SendRecord receiveForm4Req(@RequestBody Form4RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/3 controller receive request\n");
        Form4SendRecord newRecord = null;
        try{
            newRecord = repository.getForm4Response(recvRecord);
            logger.info("/form/3 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/3 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/4", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form5SendRecord receiveForm5Req(@RequestBody Form5RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/4 controller receive request\n");
        Form5SendRecord newRecord = null;
        try{
            newRecord = repository.getForm5Response(recvRecord);
            logger.info("/form/4 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/4 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/5", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form6SendRecord receiveForm6Req(@RequestBody Form6RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/5 controller receive request\n");
        Form6SendRecord newRecord = null;
        try{
            newRecord = repository.getForm6Response(recvRecord);
            logger.info("/form/5 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/5 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/6", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form7SendRecord receiveForm7Req(@RequestBody Form7RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/6 controller receive request\n");
        Form7SendRecord newRecord = null;
        try{
            newRecord = repository.getForm7Response(recvRecord);
            logger.info("/form/6 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/6 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/7", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form8SendRecord receiveForm8Req(@RequestBody Form8RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/7 controller receive request\n");
        Form8SendRecord newRecord = null;
        try{
            newRecord = repository.getForm8Response(recvRecord);
            logger.info("/form/7 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/7 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/8", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form9SendRecord receiveForm9Req(@RequestBody Form9RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/8 controller receive request\n");
        Form9SendRecord newRecord = null;
        try{
            newRecord = repository.getForm9Response(recvRecord);
            logger.info("/form/8 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/8 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/9", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Form10SendRecord receiveForm10Req(@RequestBody Form10RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/9 controller receive request\n");
        Form10SendRecord newRecord = null;
        try{
            newRecord = repository.getForm10Response(recvRecord);
            logger.info("/form/9 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/9 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/10", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm11Req(@RequestBody Form11RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/10 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm11Response(recvRecord);
            logger.info("/form/10 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/10 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/11", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm12Req(@RequestBody Form12RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/11 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm12Response(recvRecord);
            logger.info("/form/10 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/10 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/12", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm13Req(@RequestBody Form13RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/12 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm13Response(recvRecord);
            logger.info("/form/12 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/12 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/13", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm14Req(@RequestBody Form14RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/13 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm14Response(recvRecord);
            logger.info("/form/13 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/13 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/14", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm15Req(@RequestBody Form15RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/14 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm15Response(recvRecord);
            logger.info("/form/14 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/14 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/15", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm16Req(@RequestBody Form16RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/15 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm16Response(recvRecord);
            logger.info("/form/15 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/15 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/16", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm17Req(@RequestBody Form17RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/16 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm17Response(recvRecord);
            logger.info("/form/16 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/16 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/17", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm18Req(@RequestBody Form18RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/17 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm18Response(recvRecord);
            logger.info("/form/17 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/17 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/18", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm19Req(@RequestBody Form19RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/18 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm19Response(recvRecord);
            logger.info("/form/18 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/18 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/19", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm20Req(@RequestBody Form20RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/19 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm20Response(recvRecord);
            logger.info("/form/19 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/19 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }

    @PostMapping(value = "/form/20", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public SendStatusRecord receiveForm21Req(@RequestBody Form21RecvRecord recvRecord, HttpServletResponse response) {
        logger.info("/form/20 controller receive request\n");
        SendStatusRecord newRecord = null;
        try{
            newRecord = repository.getForm21Response(recvRecord);
            logger.info("/form/20 controller end execution successfully\n");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (DataAccessException e)
        {
            logger.error("/form/20 controller can't get Form2 response\n" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return newRecord;
    }
}
