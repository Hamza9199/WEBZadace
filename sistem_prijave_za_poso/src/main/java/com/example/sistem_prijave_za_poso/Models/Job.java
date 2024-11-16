package com.example.sistem_prijave_za_poso.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "poslovi")
public class Job {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private Job(){}


    public Job(int id) {
        this.id = id;
       
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
