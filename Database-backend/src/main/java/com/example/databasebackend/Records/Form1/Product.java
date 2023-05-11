package com.example.databasebackend.Records.Form1;

import lombok.Getter;
import lombok.Setter;

public class Product {
    @Getter @Setter
    private String workshopName;
    @Getter @Setter
    private String workshopSectionName;
    @Getter @Setter
    private String productTypeName;
    @Getter @Setter
    private String productTypeModelName;
    @Getter @Setter
    private int productItemsNumber;
}
