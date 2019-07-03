package com.lwj.project.bean;

import lombok.Data;

@Data
public class Book {

    private String name;

    private String author;

    public Book(){

    }

    public Book(String name,String author){
        this.name = name;
        this.author = author;
    }

}
