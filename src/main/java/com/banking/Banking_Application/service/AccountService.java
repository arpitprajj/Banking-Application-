package com.banking.Banking_Application.service;



import com.banking.Banking_Application.dto.AccountDto;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto depositAmount(Long id, Double amount);
    AccountDto withdrawAmount(Long id, Double amount);

    List<AccountDto>getAllAccounts();

    void deleteAccount(Long id);

}
