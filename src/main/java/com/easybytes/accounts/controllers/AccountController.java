package com.easybytes.accounts.controllers;

import com.easybytes.accounts.constantes.AccountsConstants;
import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.dto.ResponseDto;
import com.easybytes.accounts.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

}
