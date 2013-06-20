package com.javaheadbrain.experiments.spring.dao

import com.javaheadbrain.experiments.spring.entity.Account
import com.javaheadbrain.experiments.spring.mapper.AccountMapper
import org.springframework.stereotype.Repository

import javax.inject.Inject

@Repository
class MyBatisAccountDAO implements AccountDAO {
    static int nextId = 3

    @Inject
    AccountMapper accountMapper

    @Override
    int createAccount(double initialBalance) {
        Account account = new Account(id: nextId++, balance: initialBalance)
        accountMapper.createAccount(account)
        account.id
    }

    @Override
    Account findAccountById(int id) {
        accountMapper.findAccountById(id)
    }

    @Override
    List<Account> findAllAccounts() {
        accountMapper.findAllAccounts()
    }

    @Override
    void updateAccount(Account account) {
        accountMapper.updateAccount(account)
    }

    @Override
    void deleteAccount(int id) {
        accountMapper.deleteAccount(id)
    }
}
