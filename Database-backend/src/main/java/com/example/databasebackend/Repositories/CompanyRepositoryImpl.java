package com.example.databasebackend.Repositories;

import com.example.databasebackend.Records.Form1.Form1ProductTypeWorkshop;
import com.example.databasebackend.Records.Form1.Form1RecvRecord;
import com.example.databasebackend.Records.Form1.Form1SendRecord;
import com.example.databasebackend.Records.Form1.Product;
import com.example.databasebackend.Records.Form10.Form10RecvElement;
import com.example.databasebackend.Records.Form10.Form10RecvRecord;
import com.example.databasebackend.Records.Form10.Form10SendElement;
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
import com.example.databasebackend.Records.Form2.Form2SendElement;
import com.example.databasebackend.Records.Form2.Form2SendRecord;
import com.example.databasebackend.Records.Form2.Form2WorkshopEmployeeTypeSpec;
import com.example.databasebackend.Records.Form20.Form20RecvRecord;
import com.example.databasebackend.Records.Form21.Form21RecvRecord;
import com.example.databasebackend.Records.Form3.*;
import com.example.databasebackend.Records.Form4.Form4RecvElement;
import com.example.databasebackend.Records.Form4.Form4RecvRecord;
import com.example.databasebackend.Records.Form4.Form4SendElement;
import com.example.databasebackend.Records.Form4.Form4SendRecord;
import com.example.databasebackend.Records.Form5.Form5RecvElement;
import com.example.databasebackend.Records.Form5.Form5RecvRecord;
import com.example.databasebackend.Records.Form5.Form5SendElement;
import com.example.databasebackend.Records.Form5.Form5SendRecord;
import com.example.databasebackend.Records.Form6.Form6RecvElement;
import com.example.databasebackend.Records.Form6.Form6RecvRecord;
import com.example.databasebackend.Records.Form6.Form6SendElement;
import com.example.databasebackend.Records.Form6.Form6SendRecord;
import com.example.databasebackend.Records.Form7.Form7RecvElement;
import com.example.databasebackend.Records.Form7.Form7RecvRecord;
import com.example.databasebackend.Records.Form7.Form7SendElement;
import com.example.databasebackend.Records.Form7.Form7SendRecord;
import com.example.databasebackend.Records.Form8.Form8RecvElement;
import com.example.databasebackend.Records.Form8.Form8RecvRecord;
import com.example.databasebackend.Records.Form8.Form8SendElement;
import com.example.databasebackend.Records.Form8.Form8SendRecord;
import com.example.databasebackend.Records.Form9.Form9RecvElement;
import com.example.databasebackend.Records.Form9.Form9RecvRecord;
import com.example.databasebackend.Records.Form9.Form9SendElement;
import com.example.databasebackend.Records.Form9.Form9SendRecord;
import com.example.databasebackend.Records.SendArrayStatusRecord;
import com.example.databasebackend.Records.SendStatusRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository{
    @Autowired
    private JdbcTemplate template;

    private final String operationSuccess = "Success";
    private final String operationError = "Error";

    private final String operationInsert = "insert";
    private final String operationUpdate = "update";
    private final String operationDelete = "delete";

    @Override
    public Form1SendRecord getForm1Response(Form1RecvRecord request) throws DataAccessException
    {
        StringBuilder sqlString = new StringBuilder("Select Production.WorkshopName, Production.WorkshopSectionName, ProductUnit.ProductTypeName, ProductUnit.ProductTypeModelName, Count(Production.UnitId) As ProductItemsNumber " +
                "From ProductUnit " +
                "Inner Join Production " +
                "On ProductUnit.UnitId = Production.UnitId ");
        boolean isAllFlagSet = false;
        isAllFlagSet = Arrays.stream(request.getTypeCompanyFields()).anyMatch((Form1ProductTypeWorkshop element) -> {
            if(element.getType().equals("Все") && element.getWorkshop().equals("Все"))
            {
                return true;
            }
            return false;
        });
        if(request.getDate().isValid() || (request.getTypeCompanyFields().length > 0  && !isAllFlagSet))
        {
            sqlString.append("Where ");
        }
        boolean isDate = false;
        if(request.getDate().isValid())
        {
            sqlString.append("(Production.StartDate > ");

            sqlString.append("'");
            sqlString.append(request.getDate().getStartDate());
            sqlString.append("'");

            sqlString.append(" And ");
            sqlString.append("Production.EndDate < ");

            sqlString.append("'");
            sqlString.append(request.getDate().getEndDate());
            sqlString.append("'");

            sqlString.append(") ");
            isDate = true;
        }
        if(!isAllFlagSet)
        {
            if(isDate)
            {
                sqlString.append("And ( ");
            }
            boolean isFirstTime = true;
            for(Form1ProductTypeWorkshop workshop : request.getTypeCompanyFields())
            {
                if(!isFirstTime)
                {
                    sqlString.append("Or ");
                }else {
                    isFirstTime = false;
                }
                sqlString.append("( ");
                boolean isElementBefore = false;
                if(!workshop.getWorkshop().equals("Все"))
                {
                    sqlString.append("Production.WorkshopName = ");

                    sqlString.append("'");
                    sqlString.append(workshop.getWorkshop());
                    sqlString.append("'");

                    sqlString.append(" ");
                    isElementBefore = true;
                }
                if(!workshop.getType().equals("Все"))
                {
                    if(isElementBefore)
                    {
                        sqlString.append("And ");
                    }
                    sqlString.append("ProductUnit.ProductTypeName = ");

                    sqlString.append("'");
                    sqlString.append(workshop.getType());
                    sqlString.append("'");

                    sqlString.append(" ");
                }
                sqlString.append(") ");
            }
            if(isDate)
            {
                sqlString.append(") ");
            }
        }
        sqlString.append("Group By (Production.WorkshopName, Production.WorkshopSectionName, ProductUnit.ProductTypeName, ProductUnit.ProductTypeModelName);");
        Form1SendRecord record = new Form1SendRecord();
        try{
            List<Product> productList = template.query(sqlString.toString(), new RowMapper<Product>() {
                @Override
                public Product mapRow(ResultSet rs, int i) throws SQLException
                {
                    Product newProduct = new Product();
                    newProduct.setWorkshopName(rs.getString("WorkshopName"));
                    newProduct.setWorkshopSectionName(rs.getString("WorkshopSectionName"));
                    newProduct.setProductTypeName(rs.getString("ProductTypeName"));
                    newProduct.setProductTypeModelName(rs.getString("ProductTypeModelName"));
                    newProduct.setProductItemsNumber(rs.getInt("ProductItemsNumber"));
                    return newProduct;
                }
            });
            record.addAllProduct(productList);
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationSuccess);
            stat.setMessage("");
            record.setStatus(stat);
        }catch(DataAccessException e)
        {
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationError);
            stat.setMessage(e.getMessage());
            record.setStatus(stat);
        }
        return record;
    }

    @Override
    public Form2SendRecord getForm2Response(Form2RecvRecord request) throws DataAccessException
    {
        StringBuilder sqlString = new StringBuilder("SELECT WorkerList.FirstName, WorkerList.SecondName, WorkerList.EmployeeTypeName, WorkerList.EmployeeTypeSpecName, WorkshopBrigade.WorkshopName, WorkshopBrigade.WorkshopSectionName " +
                "FROM WorkerList " +
                "Inner Join Brigade " +
                "On Brigade.BrigadeId = WorkerList.BrigadeId " +
                "Inner Join WorkshopBrigade " +
                "On WorkshopBrigade.BrigadeId = Brigade.BrigadeId ");
        boolean isAllFlagSet = false;
        isAllFlagSet = Arrays.stream(request.getTypeCompanyFields()).anyMatch((Form2WorkshopEmployeeTypeSpec element) -> {
            if(element.getWorkshop().equals("Все") && element.getEmployeeTypeSpec().equals("Все"))
            {
                return true;
            }
            return false;
        });
        if(request.getTypeCompanyFields().length > 0  && !isAllFlagSet)
        {
            sqlString.append("Where ");
        }
        if(!isAllFlagSet)
        {
            boolean isFirstTime = true;
            for(Form2WorkshopEmployeeTypeSpec workshop : request.getTypeCompanyFields())
            {
                if(!isFirstTime)
                {
                    sqlString.append("Or ");
                }else {
                    isFirstTime = false;
                }
                sqlString.append("( ");
                boolean isElementBefore = false;
                if(!workshop.getWorkshop().equals("Все"))
                {
                    sqlString.append("WorkshopBrigade.WorkshopName = ");

                    sqlString.append("'");
                    sqlString.append(workshop.getWorkshop());
                    sqlString.append("'");

                    sqlString.append(" ");
                    isElementBefore = true;
                }
                if(!workshop.getEmployeeTypeSpec().equals("Все"))
                {
                    if(isElementBefore)
                    {
                        sqlString.append("And ");
                    }
                    sqlString.append("WorkerList.EmployeeTypeSpecName = ");

                    sqlString.append("'");
                    sqlString.append(workshop.getEmployeeTypeSpec());
                    sqlString.append("'");

                    sqlString.append(" ");
                }
                sqlString.append(") ");
            }
        }
        Form2SendRecord record = new Form2SendRecord();
        try{
            List<Form2SendElement> productList = template.query(sqlString.toString(), new RowMapper<Form2SendElement>() {
                @Override
                public Form2SendElement mapRow(ResultSet rs, int i) throws SQLException
                {
                    Form2SendElement newProduct = new Form2SendElement();
                    newProduct.setFirstName(rs.getString("FirstName"));
                    newProduct.setSecondName(rs.getString("SecondName"));
                    newProduct.setWorkshopName(rs.getString("WorkshopName"));
                    newProduct.setWorkshopSectionName(rs.getString("WorkshopSectionName"));
                    newProduct.setEmployeeTypeName(rs.getString("EmployeeTypeName"));
                    newProduct.setEmployeeTypeSpecName(rs.getString("EmployeeTypeSpecName"));
                    return newProduct;
                }
            });
            record.addAllEmployee(productList);
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationSuccess);
            stat.setMessage("");
            record.setStatus(stat);
        }catch(DataAccessException e)
        {
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationError);
            stat.setMessage(e.getMessage());
            record.setStatus(stat);
        }
        return record;
    }

    @Override
    public Form3SendRecord getForm3Response(Form3RecvRecord request) throws DataAccessException
    {
        StringBuilder sqlString = new StringBuilder("SELECT WorkshopSection.WorkshopName, WorkshopSection.WorkshopSectionName, WorkerList.WorkerId, WorkerList.FirstName, WorkerList.SecondName " +
                "FROM WorkshopSection " +
                "Inner Join WorkerList " +
                "On WorkerList.WorkerId = WorkshopSection.SectionForemanId ");
        boolean isAllFlagSet = false;
        isAllFlagSet = Arrays.stream(request.getTypeCompanyFields()).anyMatch((Form3RecvElement element) -> {
            if(element.getWorkshop().equals("Все"))
            {
                return true;
            }
            return false;
        });
        if(request.getTypeCompanyFields().length > 0  && !isAllFlagSet)
        {
            sqlString.append("Where ");
        }
        if(!isAllFlagSet)
        {
            boolean isFirstTime = true;
            for(Form3RecvElement workshop : request.getTypeCompanyFields())
            {
                if(!isFirstTime)
                {
                    sqlString.append("Or ");
                }else {
                    isFirstTime = false;
                }
                sqlString.append("( ");
                boolean isElementBefore = false;
                if(!workshop.getWorkshop().equals("Все"))
                {
                    sqlString.append("WorkshopSection.WorkshopName = ");

                    sqlString.append("'");
                    sqlString.append(workshop.getWorkshop());
                    sqlString.append("'");

                    sqlString.append(" ");
                    isElementBefore = true;
                }
                sqlString.append(") ");
            }
        }
        Form3SendRecord record = new Form3SendRecord();
        try{
            List<Form3SendElement> productList = template.query(sqlString.toString(), new RowMapper<Form3SendElement>() {
                @Override
                public Form3SendElement mapRow(ResultSet rs, int i) throws SQLException
                {
                    Form3SendElement newProduct = new Form3SendElement();
                    newProduct.setWorkshopName(rs.getString("WorkshopName"));
                    newProduct.setWorkshopSectionName(rs.getString("WorkshopSectionName"));
                    newProduct.setWorkerId(rs.getInt("WorkerId"));
                    newProduct.setFirstName(rs.getString("FirstName"));
                    newProduct.setSecondName(rs.getString("SecondName"));
                    return newProduct;
                }
            });
            record.addAllWorkshopSections(productList);
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationSuccess);
            stat.setMessage("");
            record.setStatus(stat);
        }catch(DataAccessException e)
        {
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationError);
            stat.setMessage(e.getMessage());
            record.setStatus(stat);
        }
        return record;
    }

    @Override
    public Form4SendRecord getForm4Response(Form4RecvRecord request) throws DataAccessException
    {
        StringBuilder sqlString = new StringBuilder("Select WorkshopSection.WorkshopName, WorkshopSection.WorkshopSectionName, WorkerList.BrigadeId, WorkerList.WorkerId, WorkerList.FirstName, WorkerList.SecondName " +
                "From WorkshopSection " +
                "Inner Join WorkshopBrigade " +
                "On WorkshopBrigade.WorkshopName = WorkshopSection.WorkshopName And WorkshopBrigade.WorkshopSectionName = WorkshopSection.WorkshopSectionName " +
                "Inner Join WorkerList " +
                "On WorkerList.BrigadeId = WorkshopBrigade.BrigadeId ");
        boolean isAllFlagSet = false;
        isAllFlagSet = Arrays.stream(request.getTypeCompanyFields()).anyMatch((Form4RecvElement element) -> {
            if(element.getWorkshop().equals("Все"))
            {
                return true;
            }
            return false;
        });
        if(request.getTypeCompanyFields().length > 0  && !isAllFlagSet)
        {
            sqlString.append("Where ");
        }
        if(!isAllFlagSet)
        {
            boolean isFirstTime = true;
            for(Form4RecvElement workshop : request.getTypeCompanyFields())
            {
                if(!isFirstTime)
                {
                    sqlString.append("Or ");
                }else {
                    isFirstTime = false;
                }
                sqlString.append("( ");
                boolean isElementBefore = false;
                if(!workshop.getWorkshop().equals("Все"))
                {
                    sqlString.append("WorkshopSection.WorkshopName = ");

                    sqlString.append("'");
                    sqlString.append(workshop.getWorkshop());
                    sqlString.append("'");

                    sqlString.append(" ");
                    isElementBefore = true;
                }
                sqlString.append(") ");
            }
        }
        sqlString.append(";");
        Form4SendRecord record = new Form4SendRecord();
        try{
            List<Form4SendElement> productList = template.query(sqlString.toString(), new RowMapper<Form4SendElement>() {
                @Override
                public Form4SendElement mapRow(ResultSet rs, int i) throws SQLException
                {
                    Form4SendElement newProduct = new Form4SendElement();
                    newProduct.setWorkshopName(rs.getString("WorkshopName"));   //WorkshopSection.WorkshopName
                    newProduct.setWorkshopSectionName(rs.getString("WorkshopSectionName")); //WorkshopSection.WorkshopSectionName
                    newProduct.setBrigadeId(rs.getInt("BrigadeId")); //WorkerList.BrigadeId
                    newProduct.setWorkerId(rs.getInt("WorkerId"));   //WorkerList.WorkerId
                    newProduct.setFirstName(rs.getString("FirstName"));  //WorkerList.FirstName
                    newProduct.setSecondName(rs.getString("SecondName"));    //WorkerList.SecondName
                    return newProduct;
                }
            });
            record.addAllBrigades(productList);
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationSuccess);
            stat.setMessage("");
            record.setStatus(stat);
        }catch(DataAccessException e)
        {
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationError);
            stat.setMessage(e.getMessage());
            record.setStatus(stat);
        }
        return record;
    }

    @Override
    public Form5SendRecord getForm5Response(Form5RecvRecord request) throws DataAccessException
    {
        StringBuilder sqlString = new StringBuilder("SELECT WorkshopSection.WorkshopName, WorkshopSection.WorkshopSectionName, WorkerList.BrigadeId, WorkerList.WorkerId, WorkerList.FirstName, WorkerList.SecondName " +
                "FROM WorkshopSection " +
                "Inner Join WorkshopSectionPersonal " +
                "On (WorkshopSectionPersonal.WorkshopName = WorkshopSection.WorkshopName And WorkshopSectionPersonal.WorkshopSectionName = WorkshopSection.WorkshopSectionName) " +
                "Inner Join WorkerList " +
                "On (WorkerList.WorkerId = WorkshopSectionPersonal.SectionMasterId) ");
        boolean isAllFlagSet = false;
        isAllFlagSet = Arrays.stream(request.getTypeCompanyFields()).anyMatch((Form5RecvElement element) -> {
            if(element.getWorkshop().equals("Все"))
            {
                return true;
            }
            return false;
        });
        if(request.getTypeCompanyFields().length > 0  && !isAllFlagSet)
        {
            sqlString.append("Where ");
        }
        if(!isAllFlagSet)
        {
            boolean isFirstTime = true;
            for(Form5RecvElement workshop : request.getTypeCompanyFields())
            {
                if(!isFirstTime)
                {
                    sqlString.append("Or ");
                }else {
                    isFirstTime = false;
                }
                sqlString.append("( ");
                boolean isElementBefore = false;
                if(!workshop.getWorkshop().equals("Все"))
                {
                    sqlString.append("WorkshopSection.WorkshopName = ");

                    sqlString.append("'");
                    sqlString.append(workshop.getWorkshop());
                    sqlString.append("'");

                    sqlString.append(" ");
                    isElementBefore = true;
                }
                sqlString.append(") ");
            }
        }
        Form5SendRecord record = new Form5SendRecord();
        try{
            List<Form5SendElement> productList = template.query(sqlString.toString(), new RowMapper<Form5SendElement>() {
                @Override
                public Form5SendElement mapRow(ResultSet rs, int i) throws SQLException
                {
                    Form5SendElement newProduct = new Form5SendElement();
                    newProduct.setWorkshopName(rs.getString("WorkshopName"));
                    newProduct.setWorkshopSectionName(rs.getString("WorkshopSectionName"));
                    newProduct.setWorkerId(rs.getInt("WorkerId"));
                    newProduct.setFirstName(rs.getString("FirstName"));
                    newProduct.setSecondName(rs.getString("SecondName"));
                    return newProduct;
                }
            });
            record.addAllPersonal(productList);
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationSuccess);
            stat.setMessage("");
            record.setStatus(stat);
        }catch(DataAccessException e)
        {
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationError);
            stat.setMessage(e.getMessage());
            record.setStatus(stat);
        }
        return record;
    }

    @Override
    public Form6SendRecord getForm6Response(Form6RecvRecord request) throws DataAccessException
    {
        StringBuilder sqlString = new StringBuilder("Select UniquePair.UnitId, UniquePair.BrigadeId, WorkerList.WorkerId, WorkerList.FirstName, WorkerList.SecondName " +
                "From (SELECT Distinct Production.BrigadeId, Production.UnitId " +
                "FROM Production ");
        boolean isAllFlagSet = false;
        boolean isAllNull = false;

        isAllFlagSet = Arrays.stream(request.getTypeCompanyFields()).anyMatch((Form6RecvElement element) -> {
            if(element.getProductNumber().equals("Все"))
            {
                return true;
            }
            return false;
        });

        isAllNull = Arrays.stream(request.getTypeCompanyFields()).allMatch((Form6RecvElement element) -> {
            if(element.getProductNumber().equals(""))
            {
                return true;
            }
            return false;
        });

        if(!isAllFlagSet && !isAllNull)
        {
            sqlString.append("Where ");
            boolean isFirstTime = true;
            for(Form6RecvElement unit : request.getTypeCompanyFields())
            {
                if(!unit.getProductNumber().equals(""))
                {
                    if(!isFirstTime)
                    {
                        sqlString.append("Or ");
                    }else {
                        isFirstTime = false;
                    }
                    sqlString.append("( ");
                    boolean isElementBefore = false;
                    if(!unit.getProductNumber().equals("Все"))
                    {
                        sqlString.append("Production.UnitId = ");

                        sqlString.append("'");
                        sqlString.append(unit.getProductNumber());
                        sqlString.append("'");

                        sqlString.append(" ");
                        isElementBefore = true;
                    }
                    sqlString.append(") ");
                }
            }
        }
        sqlString.append(")UniquePair " +
                "Inner Join WorkerList " +
                "On WorkerList.BrigadeId = UniquePair.BrigadeId");
        Form6SendRecord record = new Form6SendRecord();
        try{
            List<Form6SendElement> productList = template.query(sqlString.toString(), new RowMapper<Form6SendElement>() {
                @Override
                public Form6SendElement mapRow(ResultSet rs, int i) throws SQLException
                {
                    Form6SendElement worker = new Form6SendElement();
                    worker.setUnitId(rs.getInt("UnitId"));
                    worker.setWorkerId(rs.getInt("WorkerId"));
                    worker.setBrigadeId(rs.getInt("BrigadeId"));
                    worker.setFirstName(rs.getString("FirstName"));
                    worker.setSecondName(rs.getString("SecondName"));
                    return worker;
                }
            });
            record.addAllWorkers(productList);
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationSuccess);
            stat.setMessage("");
            record.setStatus(stat);
        }catch(DataAccessException e)
        {
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationError);
            stat.setMessage(e.getMessage());
            record.setStatus(stat);
        }
        return record;
    }

    @Override
    public Form7SendRecord getForm7Response(Form7RecvRecord request) throws DataAccessException
    {
        StringBuilder sqlString = new StringBuilder("SELECT Distinct Testing.UnitId, Testing.TestLabName " +
                "From Testing ");
        boolean isAllFlagSet = false;
        boolean isAllNull = false;

        isAllFlagSet = Arrays.stream(request.getTypeCompanyFields()).anyMatch((Form7RecvElement element) -> {
            if(element.getProductNumber().equals("Все"))
            {
                return true;
            }
            return false;
        });

        isAllNull = Arrays.stream(request.getTypeCompanyFields()).allMatch((Form7RecvElement element) -> {
            if(element.getProductNumber().equals(""))
            {
                return true;
            }
            return false;
        });

        if(!isAllFlagSet && !isAllNull)
        {
            sqlString.append("Where ");
            boolean isFirstTime = true;
            for(Form7RecvElement unit : request.getTypeCompanyFields())
            {
                if(!unit.getProductNumber().equals(""))
                {
                    if(!isFirstTime)
                    {
                        sqlString.append("Or ");
                    }else {
                        isFirstTime = false;
                    }
                    sqlString.append("( ");
                    boolean isElementBefore = false;

                        sqlString.append("Testing.UnitId = ");

                        sqlString.append("'");
                        sqlString.append(unit.getProductNumber());
                        sqlString.append("'");

                        sqlString.append(" ");

                    sqlString.append(") ");
                }
            }
        }

        Form7SendRecord record = new Form7SendRecord();
        try{
            List<Form7SendElement> testLabList = template.query(sqlString.toString(), new RowMapper<Form7SendElement>() {
                @Override
                public Form7SendElement mapRow(ResultSet rs, int i) throws SQLException
                {
                    Form7SendElement testLab = new Form7SendElement();
                    testLab.setUnitId(rs.getInt("UnitId"));
                    testLab.setTestLabName(rs.getString("TestLabName"));
                    return testLab;
                }
            });
            record.addAllTestLabs(testLabList);
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationSuccess);
            stat.setMessage("");
            record.setStatus(stat);
        }catch(DataAccessException e)
        {
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationError);
            stat.setMessage(e.getMessage());
            record.setStatus(stat);
        }
        return record;
    }

    @Override
    public Form8SendRecord getForm8Response(Form8RecvRecord request) throws DataAccessException
    {
        StringBuilder sqlString = new StringBuilder("SELECT Distinct Testing.UnitId, Testing.TestLabName, ProductUnit.ProductTypeName, ProductUnit.ProductTypeModelName " +
                "From Testing " +
                "Inner Join ProductUnit " +
                "On ProductUnit.UnitId = Testing.UnitId ");
        boolean isAllFlagSet = false;
        boolean isAllNull = false;

        isAllFlagSet = Arrays.stream(request.getTypeCompanyFields()).anyMatch((Form8RecvElement element) -> {
            if(element.getLab().equals("Все"))
            {
                return true;
            }
            return false;
        });

        isAllNull = Arrays.stream(request.getTypeCompanyFields()).allMatch((Form8RecvElement element) -> {
            if(element.getLab().equals(""))
            {
                return true;
            }
            return false;
        });

        if(!isAllFlagSet && !isAllNull)
        {
            sqlString.append("Where ");
            boolean isFirstTime = true;
            for(Form8RecvElement unit : request.getTypeCompanyFields())
            {
                if(!unit.getLab().equals(""))
                {
                    if(!isFirstTime)
                    {
                        sqlString.append("Or ");
                    }else {
                        isFirstTime = false;
                    }
                    sqlString.append("( ");

                    sqlString.append("Testing.TestLabName = ");

                    sqlString.append("'");
                    sqlString.append(unit.getLab());
                    sqlString.append("'");

                    sqlString.append(" ");

                    sqlString.append(") ");
                }
            }
        }
        Form8SendRecord record = new Form8SendRecord();
        try{
            List<Form8SendElement> productList = template.query(sqlString.toString(), new RowMapper<Form8SendElement>() {
                @Override
                public Form8SendElement mapRow(ResultSet rs, int i) throws SQLException
                {
                    Form8SendElement product = new Form8SendElement();
                    product.setUnitId(rs.getInt("UnitId"));
                    product.setTestLabName(rs.getString("TestLabName"));
                    product.setProductTypeName(rs.getString("ProductTypeName"));
                    product.setProductTypeModelName(rs.getString("ProductTypeModelName"));
                    return product;
                }
            });
            record.addAllProduct(productList);
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationSuccess);
            stat.setMessage("");
            record.setStatus(stat);
        }catch(DataAccessException e)
        {
            SendStatusRecord stat = new SendStatusRecord();
            stat.setOperationStatus(operationError);
            stat.setMessage(e.getMessage());
            record.setStatus(stat);
        }
        return record;
    }

    @Override
    public Form9SendRecord getForm9Response(Form9RecvRecord request) throws DataAccessException
    {
        Form9SendRecord record = new Form9SendRecord();
        SendArrayStatusRecord statuses = new SendArrayStatusRecord();
        for(Form9RecvElement unit : request.getTypeCompanyFields())
        {
            StringBuilder sqlString = null;
            switch(unit.getIsValid())
            {
                case 0:
                {
                    if(!unit.getLabNumber().equals(""))
                    {
                        sqlString = new StringBuilder("Select Distinct Testing.TesterId, WorkerList.FirstName, WorkerList.SecondName " +
                                "From Testing " +
                                "Inner Join WorkerList " +
                                "On WorkerList.WorkerId = Testing.TesterId ");
                        if(!unit.getLabNumber().equals("Все"))
                        {
                            sqlString.append("Where ");
                            sqlString.append("( ");

                            sqlString.append("Testing.TestLabName = ");

                            sqlString.append("'");
                            sqlString.append(unit.getLabNumber());
                            sqlString.append("'");

                            sqlString.append(" ");

                            sqlString.append(") ");
                        }
                    }
                    break;
                }
                case 1:
                {
                    if(!unit.getType().equals(""))
                    {
                        sqlString = new StringBuilder("Select Distinct Testing.TesterId, WorkerList.FirstName, WorkerList.SecondName " +
                                "From Testing " +
                                "Inner Join WorkerList " +
                                "On WorkerList.WorkerId = Testing.TesterId " +
                                "Inner Join ProductUnit " +
                                "On ProductUnit.UnitId = Testing.UnitId ");
                        if(!unit.getLabNumber().equals("Все"))
                        {
                            sqlString.append("Where ");
                            sqlString.append("( ");

                            sqlString.append("ProductUnit.ProductTypeName = ");

                            sqlString.append("'");
                            sqlString.append(unit.getType());
                            sqlString.append("'");

                            sqlString.append(" ");

                            sqlString.append(") ");
                        }
                    }
                    break;
                }
                case 2:
                {
                    if(!unit.getNumber().equals(""))
                    {
                        sqlString = new StringBuilder("Select Distinct Testing.TesterId, WorkerList.FirstName, WorkerList.SecondName " +
                                "From Testing " +
                                "Inner Join WorkerList " +
                                "On WorkerList.WorkerId = Testing.TesterId ");
                        if(!unit.getNumber().equals("Все"))
                        {
                            sqlString.append("Where ");
                            sqlString.append("( ");

                            sqlString.append("Testing.UnitId = ");

                            sqlString.append("'");
                            sqlString.append(unit.getNumber());
                            sqlString.append("'");

                            sqlString.append(" ");

                            sqlString.append(") ");
                        }
                    }
                    break;
                }
                default:
                {
                    break;
                }
            }
            if(sqlString != null)
            {
                try{
                    List<Form9SendElement> testerList = template.query(sqlString.toString(), new RowMapper<Form9SendElement>() {
                        @Override
                        public Form9SendElement mapRow(ResultSet rs, int i) throws SQLException
                        {
                            Form9SendElement tester = new Form9SendElement();
                            tester.setTesterId(rs.getInt("TesterId"));
                            tester.setFirstName(rs.getString("FirstName"));
                            tester.setSecondName(rs.getString("SecondName"));
                            return tester;
                        }
                    });
                    record.addAllTesters(testerList);
                    SendStatusRecord stat = new SendStatusRecord();
                    stat.setOperationStatus(operationSuccess);
                    stat.setMessage("");
                    statuses.getStatuses().add(stat);
                }catch(DataAccessException e)
                {
                    SendStatusRecord stat = new SendStatusRecord();
                    stat.setOperationStatus(operationError);
                    stat.setMessage(e.getMessage());
                    statuses.getStatuses().add(stat);
                }
            }
        }
        record.setStatuses(statuses);
        return record;
    }

    @Override
    public Form10SendRecord getForm10Response(Form10RecvRecord request) throws DataAccessException
    {
        Form10SendRecord record = new Form10SendRecord();
        SendArrayStatusRecord statuses = new SendArrayStatusRecord();
        for(Form10RecvElement unit : request.getTypeCompanyFields())
        {
            StringBuilder sqlString = null;
            switch(unit.getIsValid())
            {
                case 0:
                {
                    if(!unit.getLabNumber().equals(""))
                    {
                        sqlString = new StringBuilder("Select Distinct Testing.TestEquipmentId, TestEquipment.TestEquipmentTypeName " +
                                "From Testing " +
                                "Inner Join TestEquipment " +
                                "On TestEquipment.TestEquipmentId = Testing.TestEquipmentId ");
                        if(!unit.getLabNumber().equals("Все"))
                        {
                            sqlString.append("Where ");
                            sqlString.append("( ");

                            sqlString.append("Testing.TestLabName = ");

                            sqlString.append("'");
                            sqlString.append(unit.getLabNumber());
                            sqlString.append("'");

                            sqlString.append(" ");

                            sqlString.append(") ");
                        }
                    }
                    break;
                }
                case 1:
                {
                    if(!unit.getType().equals(""))
                    {
                        sqlString = new StringBuilder("Select Distinct Testing.TestEquipmentId, TestEquipment.TestEquipmentTypeName " +
                                "From Testing " +
                                "Inner Join TestEquipment " +
                                "On TestEquipment.TestEquipmentId = Testing.TestEquipmentId " +
                                "Inner Join ProductUnit " +
                                "On ProductUnit.UnitId = Testing.UnitId ");
                        if(!unit.getLabNumber().equals("Все"))
                        {
                            sqlString.append("Where ");
                            sqlString.append("( ");

                            sqlString.append("ProductUnit.ProductTypeName = ");

                            sqlString.append("'");
                            sqlString.append(unit.getType());
                            sqlString.append("'");

                            sqlString.append(" ");

                            sqlString.append(") ");
                        }
                    }
                    break;
                }
                case 2:
                {
                    if(!unit.getNumber().equals(""))
                    {
                        sqlString = new StringBuilder("Select Distinct Testing.TestEquipmentId, TestEquipment.TestEquipmentTypeName " +
                                "From Testing " +
                                "Inner Join TestEquipment " +
                                "On TestEquipment.TestEquipmentId = Testing.TestEquipmentId ");
                        if(!unit.getNumber().equals("Все"))
                        {
                            sqlString.append("Where ");
                            sqlString.append("( ");

                            sqlString.append("Testing.UnitId = ");

                            sqlString.append("'");
                            sqlString.append(unit.getNumber());
                            sqlString.append("'");

                            sqlString.append(" ");

                            sqlString.append(") ");
                        }
                    }
                    break;
                }
                default:
                {
                    break;
                }
            }
            if(sqlString != null)
            {
                try{
                    List<Form10SendElement> testEquipmentList = template.query(sqlString.toString(), new RowMapper<Form10SendElement>() {
                        @Override
                        public Form10SendElement mapRow(ResultSet rs, int i) throws SQLException
                        {
                            Form10SendElement testEquipment = new Form10SendElement();
                            testEquipment.setTestEquipmentId(rs.getInt("TestEquipmentId"));
                            testEquipment.setTestEquipmentTypeName(rs.getString("TestEquipmentTypeName"));
                            return testEquipment;
                        }
                    });
                    record.addAllEquipment(testEquipmentList);
                    SendStatusRecord stat = new SendStatusRecord();
                    stat.setOperationStatus(operationSuccess);
                    stat.setMessage("");
                    statuses.getStatuses().add(stat);
                }catch(DataAccessException e)
                {
                    SendStatusRecord stat = new SendStatusRecord();
                    stat.setOperationStatus(operationError);
                    stat.setMessage(e.getMessage());
                    statuses.getStatuses().add(stat);
                }
            }
        }
        record.setStatuses(statuses);
        return record;
    }

    @Override
    public SendStatusRecord getForm11Response(Form11RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getUnitId().isValid() || request.getProductTypeName().isValid() || request.getProductTypeModelName().isValid())
            {
                sqlString = new StringBuilder("Insert Into ProductUnit( ");
                boolean beforeValid = false;
                if(request.getUnitId().isValid())
                {
                    sqlString.append("UnitId");
                    beforeValid = true;
                }
                if(request.getProductTypeName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("ProductTypeName");
                    beforeValid = true;
                }
                if(request.getProductTypeModelName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("ProductTypeModelName");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getUnitId().isValid())
                {
                    sqlString.append(request.getUnitId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getProductTypeName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'" + request.getProductTypeName().getValue() + "'");
                    beforeValuesValid = true;
                }
                if(request.getProductTypeModelName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'" + request.getProductTypeModelName().getValue() + "'");
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            if(request.getUnitId().isValid() && (request.getProductTypeName().isValid() || request.getProductTypeModelName().isValid()))
            {
                sqlString = new StringBuilder("Update ProductUnit ");
                if(request.getProductTypeName().isValid())
                {
                    sqlString.append("Set ProductTypeName = ");
                    sqlString.append("'");
                    sqlString.append(request.getProductTypeName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getProductTypeModelName().isValid())
                {
                    sqlString.append("Set ProductTypeModelName = ");
                    sqlString.append("'");
                    sqlString.append(request.getProductTypeModelName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                sqlString.append("Where UnitId = ");
                sqlString.append(request.getUnitId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key UnitId isn't valid or all another fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationDelete)) {
            if(request.getUnitId().isValid())
            {
                sqlString = new StringBuilder("Delete From ProductUnit ");
                sqlString.append("Where UnitId = ");
                sqlString.append(request.getUnitId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key UnitId isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }


    @Override
    public SendStatusRecord getForm12Response(Form12RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        StringBuilder sqlString2 = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(!(request.getBrigadeId().isValid() || request.getForemanId().isValid() || request.getWorkshopName().isValid() || request.getWorkshopSectionName().isValid()))
            {
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
            if(request.getBrigadeId().isValid() || request.getForemanId().isValid())
            {
                sqlString = new StringBuilder("Insert Into Brigade( ");
                boolean beforeValid = false;
                if(request.getBrigadeId().isValid())
                {
                    sqlString.append("BrigadeId");
                    beforeValid = true;
                }
                if(request.getForemanId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("ForemanId");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getBrigadeId().isValid())
                {
                    sqlString.append(request.getBrigadeId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getForemanId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getForemanId().getValue());
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }
            if(request.getWorkshopName().isValid() || request.getWorkshopSectionName().isValid())
            {
                sqlString2 = new StringBuilder("Insert Into WorkshopBrigade( ");
                boolean beforeValid = false;
                if(request.getBrigadeId().isValid())
                {
                    sqlString2.append("BrigadeId");
                    beforeValid = true;
                }
                if(request.getWorkshopName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString2.append(", ");
                    }
                    sqlString2.append("WorkshopName");
                    beforeValid = true;
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString2.append(", ");
                    }
                    sqlString2.append("WorkshopSectionName");
                    beforeValid = true;
                }
                sqlString2.append(" ) ");
                sqlString2.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getBrigadeId().isValid())
                {
                    sqlString2.append(request.getBrigadeId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getWorkshopName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString2.append(", ");
                    }
                    sqlString2.append("'" + request.getWorkshopName().getValue() + "'");
                    beforeValuesValid = true;
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString2.append(", ");
                    }
                    sqlString2.append("'" + request.getWorkshopSectionName().getValue() + "'");
                    beforeValuesValid = true;
                }
                sqlString2.append(" );");
            }
        } else if (opType.equals(operationUpdate)) {
            if (!(request.getBrigadeId().isValid() && (request.getForemanId().isValid() || request.getWorkshopName().isValid() || request.getWorkshopSectionName().isValid())))
            {
                record.setOperationStatus(operationError);
                record.setMessage("Unique key UnitId isn't valid or all another fields are empty. Operation: " + opType);
            }
            if(request.getBrigadeId().isValid() && (request.getForemanId().isValid()))
            {
                sqlString = new StringBuilder("Update Brigade ");
                if(request.getForemanId().isValid())
                {
                    sqlString.append("Set ForemanId = ");
                    sqlString.append(request.getForemanId().getValue());
                    sqlString.append(" ");
                }
                sqlString.append("Where BrigadeId = ");
                sqlString.append(request.getBrigadeId().getValue());
                sqlString.append(";");
            }

            if(request.getBrigadeId().isValid() && (request.getWorkshopName().isValid() || request.getWorkshopSectionName().isValid()))
            {
                sqlString2 = new StringBuilder("Update WorkshopBrigade ");
                if(request.getWorkshopName().isValid())
                {
                    sqlString2.append("Set WorkshopName = ");
                    sqlString2.append("'");
                    sqlString2.append(request.getWorkshopName().getValue());
                    sqlString2.append("'");
                    sqlString2.append(" ");
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    sqlString2.append("Set WorkshopSectionName = ");
                    sqlString2.append("'");
                    sqlString2.append(request.getWorkshopSectionName().getValue());
                    sqlString2.append("'");
                    sqlString2.append(" ");
                }
                sqlString2.append("Where BrigadeId = ");
                sqlString2.append(request.getBrigadeId().getValue());
                sqlString2.append(";");
            }
        } else if (opType.equals(operationDelete)) {
            if(request.getBrigadeId().isValid())
            {
                sqlString = new StringBuilder("Delete From Brigade ");
                sqlString.append("Where BrigadeId = ");
                sqlString.append(request.getBrigadeId().getValue());
                sqlString.append(";");
                sqlString2 = new StringBuilder("Delete From WorkshopBrigade ");
                sqlString2.append("Where BrigadeId = ");
                sqlString2.append(request.getBrigadeId().getValue());
                sqlString2.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key UnitId isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        if(sqlString2 != null)
        {
            try{
                int count = template.update(sqlString2.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(record.getMessage() + Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(record.getMessage() + e.getMessage());
            }
        }
        return record;
    }

    @Override
    public SendStatusRecord getForm13Response(Form13RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getWorkerId().isValid() || request.getFirstName().isValid() || request.getSecondName().isValid() || request.getBrigadeId().isValid() ||
                    request.getStartDate().isValid() || request.getEndDate().isValid() || request.getEmployeeTypeName().isValid() ||
                    request.getEmployeeTypeSpecName().isValid())
            {
                sqlString = new StringBuilder("Insert Into WorkerList( ");
                boolean beforeValid = false;
                if(request.getWorkerId().isValid())
                {
                    sqlString.append("WorkerId");
                    beforeValid = true;
                }
                if(request.getFirstName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("FirstName");
                    beforeValid = true;
                }
                if(request.getSecondName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("SecondName");
                    beforeValid = true;
                }
                if(request.getBrigadeId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("BrigadeId");
                    beforeValid = true;
                }
                if(request.getStartDate().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("StartDate");
                    beforeValid = true;
                }
                if(request.getEndDate().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("EndDate");
                    beforeValid = true;
                }
                if(request.getEmployeeTypeName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("EmployeeTypeName");
                    beforeValid = true;
                }
                if(request.getEmployeeTypeSpecName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("EmployeeTypeSpecName");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getWorkerId().isValid())
                {
                    sqlString.append(request.getWorkerId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getFirstName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'" + request.getFirstName().getValue() + "'");
                    beforeValuesValid = true;
                }
                if(request.getSecondName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'" + request.getSecondName().getValue() + "'");
                    beforeValuesValid = true;
                }
                if(request.getBrigadeId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getBrigadeId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getStartDate().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'" + request.getStartDate().getValue() + "'");
                    beforeValuesValid = true;
                }
                if(request.getEndDate().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'" + request.getEndDate().getValue() + "'");
                    beforeValuesValid = true;
                }
                if(request.getEmployeeTypeName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'" + request.getEmployeeTypeName().getValue() + "'");
                    beforeValuesValid = true;
                }
                if(request.getEmployeeTypeSpecName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'" + request.getEmployeeTypeSpecName().getValue() + "'");
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            if(request.getWorkerId().isValid() && (request.getFirstName().isValid() || request.getSecondName().isValid() || request.getBrigadeId().isValid() ||
                    request.getStartDate().isValid() || request.getEndDate().isValid() || request.getEmployeeTypeName().isValid() ||
                    request.getEmployeeTypeSpecName().isValid()))
            {
                sqlString = new StringBuilder("Update WorkerList ");
                if(request.getFirstName().isValid())
                {
                    sqlString.append("Set FirstName = ");
                    sqlString.append("'");
                    sqlString.append(request.getFirstName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getSecondName().isValid())
                {
                    sqlString.append("Set SecondName = ");
                    sqlString.append("'");
                    sqlString.append(request.getSecondName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getBrigadeId().isValid())
                {
                    sqlString.append("Set BrigadeId = ");
                    sqlString.append(request.getBrigadeId().getValue());
                    sqlString.append(" ");
                }
                if(request.getStartDate().isValid())
                {
                    sqlString.append("Set StartDate = ");
                    sqlString.append("'");
                    sqlString.append(request.getStartDate().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getEndDate().isValid())
                {
                    sqlString.append("Set EndDate = ");
                    sqlString.append("'");
                    sqlString.append(request.getEndDate().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getEmployeeTypeName().isValid())
                {
                    sqlString.append("Set EmployeeTypeName = ");
                    sqlString.append("'");
                    sqlString.append(request.getEmployeeTypeName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getEmployeeTypeSpecName().isValid())
                {
                    sqlString.append("Set EmployeeTypeSpecName = ");
                    sqlString.append("'");
                    sqlString.append(request.getEmployeeTypeSpecName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                sqlString.append("Where WorkerId = ");
                sqlString.append(request.getWorkerId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key WorkerId isn't valid or all another fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationDelete)) {
            if(request.getWorkerId().isValid())
            {
                sqlString = new StringBuilder("Delete From WorkerList ");
                sqlString.append("Where WorkerId = ");
                sqlString.append(request.getWorkerId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key WorkerId isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }

    @Override
    public SendStatusRecord getForm14Response(Form14RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getWorkshopName().isValid() || request.getWorkshopForemanId().isValid())
            {
                sqlString = new StringBuilder("Insert Into Company( ");
                boolean beforeValid = false;
                if(request.getWorkshopName().isValid())
                {
                    sqlString.append("WorkshopName");
                    beforeValid = true;
                }
                if(request.getWorkshopForemanId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("WorkshopForemanId");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getWorkshopName().isValid())
                {
                    sqlString.append("'");
                    sqlString.append(request.getWorkshopName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getWorkshopForemanId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getWorkshopForemanId().getValue());
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            if(request.getWorkshopName().isValid() && request.getWorkshopForemanId().isValid())
            {
                sqlString = new StringBuilder("Update Company ");

                if(request.getWorkshopForemanId().isValid())
                {
                    sqlString.append("Set WorkshopForemanId = ");
                    sqlString.append(request.getWorkshopForemanId().getValue());
                    sqlString.append(" ");
                }

                sqlString.append("Where WorkshopName = ");
                sqlString.append("'");
                sqlString.append(request.getWorkshopName().getValue());
                sqlString.append("'");
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key WorkshopName isn't valid or all another fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationDelete)) {
            if(request.getWorkshopName().isValid())
            {
                sqlString = new StringBuilder("Delete From Company ");
                sqlString.append("Where WorkshopName = ");
                sqlString.append("'");
                sqlString.append(request.getWorkshopName().getValue());
                sqlString.append("'");
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key WorkshopName isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }

    @Override
    public SendStatusRecord getForm15Response(Form15RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getTestLabName().isValid())
            {
                sqlString = new StringBuilder("Insert Into TestLab( ");
                boolean beforeValid = false;
                if(request.getTestLabName().isValid())
                {
                    sqlString.append("TestLabName");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getTestLabName().isValid())
                {
                    sqlString.append("'");
                    sqlString.append(request.getTestLabName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            record.setMessage("This operation isn't supported for this form: " + opType);
        } else if (opType.equals(operationDelete)) {
            if(request.getTestLabName().isValid())
            {
                sqlString = new StringBuilder("Delete From TestLab ");
                sqlString.append("Where TestLabName = ");
                sqlString.append("'");
                sqlString.append(request.getTestLabName().getValue());
                sqlString.append("'");
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (TestLabName) isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }

    @Override
    public SendStatusRecord getForm16Response(Form16RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getProductionOperationId().isValid() || request.getUnitId().isValid() || request.getWorkshopName().isValid() || request.getWorkshopSectionName().isValid()
                    || request.getBrigadeId().isValid() || request.getStartDate().isValid() || request.getEndDate().isValid())
            {
                sqlString = new StringBuilder("Insert Into Production( ");
                boolean beforeValid = false;
                if(request.getProductionOperationId().isValid())
                {
                    sqlString.append("ProductionOperationId");
                    beforeValid = true;
                }
                if(request.getUnitId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("UnitId");
                    beforeValid = true;
                }
                if(request.getWorkshopName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("WorkshopName");
                    beforeValid = true;
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("WorkshopSectionName");
                    beforeValid = true;
                }
                if(request.getBrigadeId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("BrigadeId");
                    beforeValid = true;
                }
                if(request.getStartDate().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("StartDate");
                    beforeValid = true;
                }
                if(request.getEndDate().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("EndDate");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getProductionOperationId().isValid())
                {
                    sqlString.append(request.getProductionOperationId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getUnitId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getUnitId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getWorkshopName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getWorkshopName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getWorkshopSectionName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getBrigadeId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getBrigadeId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getStartDate().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getStartDate().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getEndDate().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getEndDate().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            if(request.getProductionOperationId().isValid() && (request.getUnitId().isValid() || request.getWorkshopName().isValid() || request.getWorkshopSectionName().isValid()
                    || request.getBrigadeId().isValid() || request.getStartDate().isValid() || request.getEndDate().isValid()))
            {
                sqlString = new StringBuilder("Update Production ");

                if(request.getUnitId().isValid())
                {
                    sqlString.append("Set UnitId = ");
                    sqlString.append(request.getUnitId().getValue());
                    sqlString.append(" ");
                }
                if(request.getWorkshopName().isValid())
                {
                    sqlString.append("Set WorkshopName = ");
                    sqlString.append("'");
                    sqlString.append(request.getWorkshopName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    sqlString.append("Set WorkshopSectionName = ");
                    sqlString.append("'");
                    sqlString.append(request.getWorkshopSectionName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getBrigadeId().isValid())
                {
                    sqlString.append("Set BrigadeId = ");
                    sqlString.append(request.getBrigadeId().getValue());
                    sqlString.append(" ");
                }
                if(request.getStartDate().isValid())
                {
                    sqlString.append("Set StartDate = ");
                    sqlString.append("'");
                    sqlString.append(request.getStartDate().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getEndDate().isValid())
                {
                    sqlString.append("Set EndDate = ");
                    sqlString.append("'");
                    sqlString.append(request.getEndDate().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }

                sqlString.append("Where ProductionOperationId = ");
                sqlString.append(request.getProductionOperationId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (ProductionOperationId) isn't valid or all another fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationDelete)) {
            if(request.getProductionOperationId().isValid())
            {
                sqlString = new StringBuilder("Delete From Production ");
                sqlString.append("Where ProductionOperationId = ");
                sqlString.append(request.getProductionOperationId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (ProductionOperationId) isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }

    @Override
    public SendStatusRecord getForm17Response(Form17RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getTestingId().isValid() || request.getUnitId().isValid() || request.getTestLabName().isValid() || request.getTesterId().isValid() ||
                request.getTestPhaseName().isValid() || request.getTestEquipmentId().isValid() || request.getStartDate().isValid() || request.getEndDate().isValid())
            {
                sqlString = new StringBuilder("Insert Into Testing( ");
                boolean beforeValid = false;
                if(request.getTestEquipmentId().isValid())
                {
                    sqlString.append("TestingId");
                    beforeValid = true;
                }
                if(request.getUnitId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("UnitId");
                    beforeValid = true;
                }
                if(request.getTestLabName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("TestLabName");
                    beforeValid = true;
                }
                if(request.getTesterId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("TesterId");
                    beforeValid = true;
                }
                if(request.getTestPhaseName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("TestPhaseName");
                    beforeValid = true;
                }
                if(request.getTestEquipmentId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("TestEquipmentId");
                    beforeValid = true;
                }
                if(request.getStartDate().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("StartDate");
                    beforeValid = true;
                }
                if(request.getEndDate().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("EndDate");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getTestingId().isValid())
                {
                    sqlString.append(request.getTestingId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getUnitId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getUnitId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getTestLabName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getTestLabName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getTesterId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getTesterId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getTestPhaseName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getTestPhaseName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getTestEquipmentId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getTestEquipmentId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getStartDate().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getStartDate().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getEndDate().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getEndDate().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            if(request.getTestingId().isValid() && (request.getUnitId().isValid() || request.getTestLabName().isValid() || request.getTesterId().isValid() ||
                    request.getTestPhaseName().isValid() || request.getTestEquipmentId().isValid() || request.getStartDate().isValid() || request.getEndDate().isValid()))
            {
                sqlString = new StringBuilder("Update Testing ");

                if(request.getUnitId().isValid())
                {
                    sqlString.append("Set UnitId = ");
                    sqlString.append(request.getUnitId().getValue());
                    sqlString.append(" ");
                }
                if(request.getTestLabName().isValid())
                {
                    sqlString.append("Set TestLabName = ");
                    sqlString.append("'");
                    sqlString.append(request.getTestLabName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getTesterId().isValid())
                {
                    sqlString.append("Set TesterId = ");
                    sqlString.append(request.getTesterId().getValue());
                    sqlString.append(" ");
                }
                if(request.getTestPhaseName().isValid())
                {
                    sqlString.append("Set TestPhaseName = ");
                    sqlString.append("'");
                    sqlString.append(request.getTestPhaseName().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getTestEquipmentId().isValid())
                {
                    sqlString.append("Set TestEquipmentId = ");
                    sqlString.append(request.getTestEquipmentId().getValue());
                    sqlString.append(" ");
                }
                if(request.getStartDate().isValid())
                {
                    sqlString.append("Set StartDate = ");
                    sqlString.append("'");
                    sqlString.append(request.getStartDate().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }
                if(request.getEndDate().isValid())
                {
                    sqlString.append("Set EndDate = ");
                    sqlString.append("'");
                    sqlString.append(request.getEndDate().getValue());
                    sqlString.append("'");
                    sqlString.append(" ");
                }

                sqlString.append("Where TestingId = ");
                sqlString.append(request.getTestingId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (TestingId) isn't valid or all another fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationDelete)) {
            if(request.getTestingId().isValid())
            {
                sqlString = new StringBuilder("Delete From Testing ");
                sqlString.append("Where TestingId = ");
                sqlString.append(request.getTestingId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (TestingId) isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }

    @Override
    public SendStatusRecord getForm18Response(Form18RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getWorkshopName().isValid() && request.getWorkshopSectionName().isValid() && request.getSectionMasterId().isValid())
            {
                sqlString = new StringBuilder("Insert Into WorkshopSectionPersonal( ");
                boolean beforeValid = false;
                if(request.getWorkshopName().isValid())
                {
                    sqlString.append("WorkshopName");
                    beforeValid = true;
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("WorkshopSectionName");
                    beforeValid = true;
                }
                if(request.getSectionMasterId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("SectionMasterId");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getWorkshopName().isValid())
                {
                    sqlString.append("'");
                    sqlString.append(request.getWorkshopName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getWorkshopSectionName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getSectionMasterId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getSectionMasterId().getValue());
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            record.setOperationStatus(operationError);
            record.setMessage("This operation isn't supported on this form. Operation: " + opType);
        } else if (opType.equals(operationDelete)) {
            if(request.getWorkshopName().isValid() && request.getWorkshopSectionName().isValid() && request.getSectionMasterId().isValid())
            {
                sqlString = new StringBuilder("Delete From WorkshopSectionPersonal ");
                sqlString.append("Where WorkshopName = ");
                sqlString.append("'");
                sqlString.append(request.getWorkshopName().getValue());
                sqlString.append("' And WorkshopSectionName = ");
                sqlString.append("'");
                sqlString.append(request.getWorkshopSectionName().getValue());
                sqlString.append("' And SectionMasterId = ");
                sqlString.append(request.getSectionMasterId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (WorkshopName, WorkshopSectionName, SectionMasterId) isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }

    @Override
    public SendStatusRecord getForm19Response(Form19RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getTestLabName().isValid() && request.getTesterId().isValid())
            {
                sqlString = new StringBuilder("Insert Into TestLabPersonal( ");
                boolean beforeValid = false;
                if(request.getTestLabName().isValid())
                {
                    sqlString.append("TestLabName");
                    beforeValid = true;
                }
                if(request.getTesterId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("TesterId");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getTestLabName().isValid())
                {
                    sqlString.append("'");
                    sqlString.append(request.getTestLabName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getTesterId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getTesterId().getValue());
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            record.setOperationStatus(operationError);
            record.setMessage("This operation isn't supported on this form. Operation: " + opType);
        } else if (opType.equals(operationDelete)) {
            if(request.getTestLabName().isValid() && request.getTesterId().isValid())
            {
                sqlString = new StringBuilder("Delete From TestLabPersonal ");
                sqlString.append("Where TestLabName = ");
                sqlString.append("'");
                sqlString.append(request.getTestLabName().getValue());
                sqlString.append("' And TesterId = ");
                sqlString.append(request.getTesterId().getValue());
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (TestLabName, TesterId) isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }

    @Override
    public SendStatusRecord getForm20Response(Form20RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getWorkshopName().isValid() || request.getWorkshopSectionName().isValid() || request.getSectionForemanId().isValid())
            {
                sqlString = new StringBuilder("Insert Into WorkshopSection( ");
                boolean beforeValid = false;
                if(request.getWorkshopName().isValid())
                {
                    sqlString.append("WorkshopName");
                    beforeValid = true;
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("WorkshopSectionName");
                    beforeValid = true;
                }
                if(request.getSectionForemanId().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("SectionForemanId");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getWorkshopName().isValid())
                {
                    sqlString.append("'");
                    sqlString.append(request.getWorkshopName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getWorkshopSectionName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getWorkshopSectionName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getSectionForemanId().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getSectionForemanId().getValue());
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            if(request.getWorkshopName().isValid() && request.getWorkshopSectionName().isValid() && request.getSectionForemanId().isValid())
            {
                sqlString = new StringBuilder("Update WorkshopSection ");

                if(request.getSectionForemanId().isValid())
                {
                    sqlString.append("Set SectionForemanId = ");
                    sqlString.append(request.getSectionForemanId().getValue());
                    sqlString.append(" ");
                }

                sqlString.append("Where WorkshopName = ");
                sqlString.append("'");
                sqlString.append(request.getWorkshopName().getValue());
                sqlString.append("' And WorkshopSectionName = ");
                sqlString.append("'");
                sqlString.append(request.getWorkshopSectionName().getValue());
                sqlString.append("'");
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (WorkshopName, WorkshopSectionName) isn't valid or all another fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationDelete)) {
            if(request.getWorkshopName().isValid())
            {
                sqlString = new StringBuilder("Delete From WorkshopSection ");
                sqlString.append("Where WorkshopName = ");
                sqlString.append("'");
                sqlString.append(request.getWorkshopName().getValue());
                sqlString.append("' And WorkshopSectionName = ");
                sqlString.append("'");
                sqlString.append(request.getWorkshopSectionName().getValue());
                sqlString.append("'");
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (WorkshopName, WorkshopSectionName) isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }

    @Override
    public SendStatusRecord getForm21Response(Form21RecvRecord request) throws DataAccessException
    {
        SendStatusRecord record = new SendStatusRecord();
        StringBuilder sqlString = null;
        String opType = request.getRequestType().getValue();
        if(opType.equals(operationInsert))
        {
            if(request.getTestEquipmentId().isValid() || request.getTestEquipmentTypeName().isValid() || request.getTestLabName().isValid())
            {
                sqlString = new StringBuilder("Insert Into TestEquipment( ");
                boolean beforeValid = false;
                if(request.getTestEquipmentId().isValid())
                {
                    sqlString.append("TestEquipmentId");
                    beforeValid = true;
                }
                if(request.getTestEquipmentTypeName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("TestEquipmentTypeName");
                    beforeValid = true;
                }
                if(request.getTestLabName().isValid())
                {
                    if(beforeValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("TestLabName");
                    beforeValid = true;
                }
                sqlString.append(" ) ");
                sqlString.append("Values( ");
                boolean beforeValuesValid = false;
                if(request.getTestEquipmentId().isValid())
                {
                    sqlString.append(request.getTestEquipmentId().getValue());
                    beforeValuesValid = true;
                }
                if(request.getTestEquipmentTypeName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append("'");
                    sqlString.append(request.getTestEquipmentTypeName().getValue());
                    sqlString.append("'");
                    beforeValuesValid = true;
                }
                if(request.getTestLabName().isValid())
                {
                    if(beforeValuesValid)
                    {
                        sqlString.append(", ");
                    }
                    sqlString.append(request.getTestLabName().getValue());
                    beforeValuesValid = true;
                }
                sqlString.append(" );");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("All fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationUpdate)) {
            if(request.getTestEquipmentId().isValid())
            {
                sqlString = new StringBuilder("Update TestEquipment ");

                if(request.getTestEquipmentTypeName().isValid())
                {
                    sqlString.append("Set TestEquipmentTypeName = ");
                    sqlString.append(request.getTestEquipmentTypeName().getValue());
                    sqlString.append(" ");
                }
                if(request.getTestLabName().isValid())
                {
                    sqlString.append("Set TestLabName = ");
                    sqlString.append(request.getTestLabName().getValue());
                    sqlString.append(" ");
                }

                sqlString.append("Where TestEquipmentId = ");
                sqlString.append("'");
                sqlString.append(request.getTestEquipmentId().getValue());
                sqlString.append("'");
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (TestEquipmentId) isn't valid or all another fields are empty. Operation: " + opType);
            }
        } else if (opType.equals(operationDelete)) {
            if(request.getTestEquipmentId().isValid())
            {
                sqlString = new StringBuilder("Delete From TestEquipment ");
                sqlString.append("Where TestEquipmentId = ");
                sqlString.append("'");
                sqlString.append(request.getTestEquipmentId().getValue());
                sqlString.append("'");
                sqlString.append(";");
            }else{
                record.setOperationStatus(operationError);
                record.setMessage("Unique key (TestEquipmentId) isn't valid. Operation: " + opType);
            }
        } else {
            record.setOperationStatus(operationError);
            record.setMessage("Uncorrect operation with name: " + opType);
        }
        if(sqlString != null)
        {
            try{
                int count = template.update(sqlString.toString());
                record.setOperationStatus(operationSuccess);
                record.setMessage(Integer.toString(count) + " Row(s) Affected");
            }catch(DataAccessException e)
            {
                record.setOperationStatus(operationError);
                record.setMessage(e.getMessage());
            }
        }
        return record;
    }
}
