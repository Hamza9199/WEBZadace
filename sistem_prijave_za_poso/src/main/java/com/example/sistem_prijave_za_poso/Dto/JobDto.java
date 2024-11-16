package com.example.sistem_prijave_za_poso.Dto;

public class JobDto {

    private int id;



    private JobDto(){}


    public JobDto(int id) {
        this.id = id;
       
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
