package com.code.basicbankautomation.Controller;


import com.code.basicbankautomation.Dto.AccountDto;
import com.code.basicbankautomation.Dto.BalanceRequest;
import com.code.basicbankautomation.Dto.CreateAccountRequest;
import com.code.basicbankautomation.Dto.UpdateAccountRequest;
import com.code.basicbankautomation.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return  ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }
    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<AccountDto> updateAccount
            (@PathVariable String id, @RequestBody UpdateAccountRequest updateAccountRequest){
        return ResponseEntity.ok(accountService.updateAccount(id,updateAccountRequest));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return  ResponseEntity.ok(accountService.getAllAccount());
    }
    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<AccountDto>getAccountById(@PathVariable String id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }
    @DeleteMapping("/deleteAccountById/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable String id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/addMoney")
    public ResponseEntity <AccountDto> addMoney(@RequestBody BalanceRequest balanceRequest){
     return   ResponseEntity.ok(accountService.addMoney(balanceRequest.getId(),balanceRequest.getAmount()));
    }

    @PutMapping("/withDraw")
    public ResponseEntity <AccountDto> withDrawMoney(@RequestBody BalanceRequest balanceRequest){
        return   ResponseEntity.ok(accountService.withDrawMoney(balanceRequest.getId(),balanceRequest.getAmount()));
    }
}