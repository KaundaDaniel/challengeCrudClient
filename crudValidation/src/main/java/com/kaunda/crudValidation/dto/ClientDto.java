package com.kaunda.crudValidation.dto;

import com.kaunda.crudValidation.entities.Client;

import java.time.LocalDate;

public class ClientDto {
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private LocalDate birthdate;
    private Integer children;

    public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthdate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthdate = birthdate;
        this.children = children;
    }

    public  ClientDto(Client clientEntity){
        id= clientEntity.getId();
        name= clientEntity.getName();
        income= clientEntity.getIncome();
        birthdate=clientEntity.getBirthdate();
        children= clientEntity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}
