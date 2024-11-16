package com.example.sistem_prijave_za_poso.Dto;



public class KorisnikDto {
    private int id;
    private String email;
    private String password;

    public KorisnikDto(int id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
        
    }

    public KorisnikDto(){}
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }



}
