package com.code.basicbankautomation.repository;

import com.code.basicbankautomation.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
