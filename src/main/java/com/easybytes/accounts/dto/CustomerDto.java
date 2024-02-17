package com.easybytes.accounts.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CustomerDto {

    @NotEmpty(message = "cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "cannot be null or empty")
    @Email(message = "Email address should hav a valid format")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits")
    private String mobileNumber;
    private AccountDto accountDto;
}
