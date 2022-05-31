package com.code.basicbankautomation.service;
import com.code.basicbankautomation.Dto.AccountDto;
import com.code.basicbankautomation.Dto.AccountDtoConverter;
import com.code.basicbankautomation.Dto.CreateAccountRequest;
import com.code.basicbankautomation.Dto.UpdateAccountRequest;
import com.code.basicbankautomation.model.Account;
import com.code.basicbankautomation.model.Customer;
import com.code.basicbankautomation.repository.AccountRepository;
import com.code.basicbankautomation.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountDtoConverter accountDtoConverter;
    public AccountService(AccountRepository accountRepository, CustomerService customerService, CustomerRepository customerRepository, AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;

        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer=customerRepository.findById(createAccountRequest.getCustomerId()).get();
        if (Objects.equals(customer.getId(), "") || customer.getId()==null){
            return AccountDto.builder().build();
        }
        Account account= Account.builder().id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .city(createAccountRequest.getCity())
                .currency(createAccountRequest.getCurrency())
                .customerId(createAccountRequest.getCustomerId())
                .build();
        return accountDtoConverter.convert(accountRepository.save(account));
    }

    public AccountDto updateAccount(String id, UpdateAccountRequest updateAccountRequest){
        Customer customer= customerRepository.findById(id).get();
        if (customer.getId().equals("") || customer.getId()==null){
            return AccountDto.builder().build();
        }
        Optional<Account> optionalAccount= accountRepository.findById(id);

        optionalAccount.ifPresent(account -> {
            Account.builder()
                    .customerId(updateAccountRequest.getCustomerId())
                    .city(updateAccountRequest.getCity())
                    .balance(updateAccountRequest.getBalance())
                    .currency(updateAccountRequest.getCurrency())
                    .build();
            accountRepository.save(account);
        });
        return optionalAccount.map(account ->  accountDtoConverter.convert(account)).orElse(new AccountDto());

    }

    public void  deleteAccount(String id){
        accountRepository.deleteById(id);
    }

    public List<AccountDto> getAllAccount(){
        List<Account> accountList= accountRepository.findAll();
        List<AccountDto> accountDtoList= new ArrayList<>();
        for (Account account: accountList) {
            accountDtoList.add(accountDtoConverter.convert(account));
        }
        return  accountDtoList;
    }
    public AccountDto getAccountById(String id){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.map(account -> accountDtoConverter.convert(account)).orElse(new AccountDto());
    }

    public AccountDto withDrawMoney(String accountId,double amount){

        Optional<Account> optionalAccount= accountRepository.findById(accountId);
        optionalAccount.ifPresent(account -> {
            if (account.getBalance()>amount){
                account.setBalance(account.getBalance()-amount);
            }
            else {
                System.out.println("yeterli para yok"+account.getBalance()+" "+ amount);
            }
            accountRepository.save(account);
        });
       return  optionalAccount.map(account -> accountDtoConverter.convert(account)).orElse(new AccountDto());
    }

    public AccountDto addMoney(String accountId,double amount){

        Optional<Account> optionalAccount= accountRepository.findById(accountId);
        optionalAccount.ifPresent(account -> {
                account.setBalance(account.getBalance()+amount);
            accountRepository.save(account);
        });
        return  optionalAccount.map(account -> accountDtoConverter.convert(account)).orElse(new AccountDto());
    }
}

