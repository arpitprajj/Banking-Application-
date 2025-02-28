package com.banking.Banking_Application.dto;

import lombok.*;



public class AccountDto {
   // private Long id;
    private String accountHolderName;
    private Double balance;

    public AccountDto(String accountHolderName, Double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public AccountDto() {
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}