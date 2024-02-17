package com.easybytes.accounts.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class AccountDto {

    @NotEmpty(message = "cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "cannot be null or empty")
    private String accountType;

    @NotEmpty(message = "cannot be null or empty")
    private String branchAddress;
}
