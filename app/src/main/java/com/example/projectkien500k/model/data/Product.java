package com.example.projectkien500k.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import retrofit2.http.GET;
@AllArgsConstructor
public class  Product {
    @Setter @Getter
    String name;
    @Setter @Getter
    int price;
    @Setter @Getter
    int id;
    @Setter @Getter
    String description;
    @Setter @Getter
    String image;

}
