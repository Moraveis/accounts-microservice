package com.udemy.microservices.course.accounts.mapper;

import com.udemy.microservices.course.accounts.dto.AccountDto;
import com.udemy.microservices.course.accounts.entities.Account;

public class AccountMapper {

    public static AccountDto mapToAccountsDto(Account accounts, AccountDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Account mapToAccounts(AccountDto accountsDto, Account accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
