package com.banking.Banking_Application.controller;


import com.banking.Banking_Application.dto.AccountDto;
import com.banking.Banking_Application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/hi")
    public String greet(){
        return "Hello";
    }
    @PostMapping("/")
    public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto){

        AccountDto createdAccountDto=this.accountService.createAccount(accountDto);
        return  new ResponseEntity<>(createdAccountDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto>getAccount(@PathVariable Long id){
        AccountDto getDto=this.accountService.getAccountById(id);
        return  new ResponseEntity<>(getDto, HttpStatus.OK);
    }

    @PutMapping("/deposit/{id}")
    public  ResponseEntity<AccountDto>depositAmount(@PathVariable Long id, @RequestBody Map<String,Double>request){
        Double amount=request.get("amount");
        AccountDto accountDto=this.accountService.depositAmount(id,amount);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }
    @PutMapping("/withdraw/{id}")
    public  ResponseEntity<AccountDto>withdrawAmount(@PathVariable Long id, @RequestBody Map<String,Double>request){
        Double amount=request.get("amount");
        AccountDto accountDto=this.accountService.withdrawAmount(id,amount);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>>getAllAccounts(){
       List<AccountDto> accountDtoList=this.accountService.getAllAccounts();
       return new ResponseEntity<>(accountDtoList,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteAccount(@PathVariable Long id){
        this.accountService.deleteAccount(id);
        return new ResponseEntity<>("Account Deleted Successfully",HttpStatus.OK);
    }

}
