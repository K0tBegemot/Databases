package com.example.databasebackend.Records.Form1;

import com.example.databasebackend.Records.SendStatusRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Form1SendRecord {

    @Getter @Setter
    private SendStatusRecord status;
    @Getter @Setter
    private ProductList productList;

    public Form1SendRecord()
    {
        productList = new ProductList();
    }

    public void addProduct(Product newProduct)
    {
        productList.add(newProduct);
    }
    public void addAllProduct(List<Product> genericList)
    {
        productList.addAll(genericList);
    }
}
