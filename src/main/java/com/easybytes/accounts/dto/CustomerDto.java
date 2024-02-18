package com.easybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(name = "Customer", description = "Holds Customer and Account details")
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "Luke")
    @NotEmpty(message = "cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(description = "Email of the customer", example = "luke@domain.com")
    @NotEmpty(message = "cannot be null or empty")
    @Email(message = "Email address should hav a valid format")
    private String email;

    @Schema(description = "Phone number of the customer", example = "1234567890")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details")
    private AccountDto accountDto;
}
