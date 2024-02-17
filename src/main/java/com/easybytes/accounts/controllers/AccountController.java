package com.easybytes.accounts.controllers;

import com.easybytes.accounts.constantes.AccountsConstants;
import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.dto.ResponseDto;
import com.easybytes.accounts.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/api/accounts/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final IAccountService accountService;

    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping(path = "fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits") String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping(path = "update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean updatedAccount = accountService.updateAccount(customerDto);
        if (!updatedAccount) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits") String mobileNumber) {
        boolean accountDeleted = accountService.deleteAccount(mobileNumber);
        if (!accountDeleted) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

}
