package com.example.databasebackend.Records.Form8;

import com.example.databasebackend.Records.SendStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form8SendRecord
{
    @Getter @Setter
    private SendStatusRecord status;
    @Getter @Setter
    private Form8SendList products;

    public Form8SendRecord()
    {
        products = new Form8SendList();
    }

    public void addProduct(Form8SendElement product)
    {
        products.add(product);
    }

    public void addAllProduct(List<Form8SendElement> productList)
    {
        products.addAll(productList);
    }
}
