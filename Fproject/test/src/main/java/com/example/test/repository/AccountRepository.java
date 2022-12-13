package com.example.test.repository;

import com.example.test.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String Email);

}
