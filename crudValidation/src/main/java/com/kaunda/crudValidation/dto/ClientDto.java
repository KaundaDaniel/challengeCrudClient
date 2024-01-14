package com.kaunda.crudValidation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaunda.crudValidation.entities.Client;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class ClientDto  implements Serializable {
    private Long id;
    @NotBlank(message = "O Campo nome é obrigatório!")
    @Size(min = 3, max = 70, message = "Deve ter no minimo 3 e maximo 70")
    private String name;
    @NotBlank(message = "O Campo nome é obrigatório!")
    @Size(min = 10, message = "O Campo CPF é obrigatório!")
    private String cpf;
    @Positive(message = "Não Pode ter valor Negativo!")
    private Double income;
    @FutureOrPresent(message = "Insira uma data válida!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Positive(message = "Não Pode ter valor Negativo!")
    private Integer children;

    public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public  ClientDto(Client clientEntity){
        id= clientEntity.getId();
        name= clientEntity.getName();
        income= clientEntity.getIncome();
        birthDate=clientEntity.getBirthDate();
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

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}
