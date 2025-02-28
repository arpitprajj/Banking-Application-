package com.banking.Banking_Application.service.Impl;


import com.banking.Banking_Application.dto.AccountDto;
import com.banking.Banking_Application.entity.Account;
import com.banking.Banking_Application.repository.AccountRepository;
import com.banking.Banking_Application.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        System.out.println(" AccountServiceImpl Called");
//        System.out.println(" " + accountDto.getAccountHolderName() +" "+ accountDto.getBalance());

        Account account=this.modelMapper.map(accountDto,Account.class);
   Account savedAccount=this.accountRepository.save(account);
   return this.modelMapper.map(savedAccount,AccountDto.class);
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Optional<Account> account=this.accountRepository.findById(id);

        return this.modelMapper.map(account,AccountDto.class);
    }

    @Override
    public AccountDto depositAmount(Long id, Double amount) {
        Account account=this.accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Does not exist"));
        Double avlAmount=account.getBalance();
        account.setBalance(avlAmount+amount);
        Account newAccount=this.accountRepository.save(account);
        return this.modelMapper.map(newAccount,AccountDto.class);
    }

    @Override
    public AccountDto withdrawAmount(Long id, Double amount) {
        Account account=this.accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
        double avlAmount=account.getBalance();
        if(avlAmount>amount){
            account.setBalance(account.getBalance()-amount);
            Account newAccount=this.accountRepository.save(account);
            return this.modelMapper.map(newAccount,AccountDto.class);
        }
        else{
            throw new RuntimeException("Amount exceeds than than the available amount");
        }
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=this.accountRepository.findAll();
        List<AccountDto>accountDtos=accounts.stream().map((account )->this.modelMapper.map(account,AccountDto.class)).collect(Collectors.toList());
        return accountDtos;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account=this.accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
        this.accountRepository.delete(account);
    }
}
