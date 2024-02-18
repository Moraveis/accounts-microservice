package com.easybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Schema(name = "Account", description = "Holds account details")
public class AccountDto {

    @Schema(description = "Account number")
    @NotEmpty(message = "cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits")
    private Long accountNumber;

    @Schema(description = "Account type", example = "Savings")
    @NotEmpty(message = "cannot be null or empty")
    private String accountType;

    @Schema(description = "Address for the bank branch")
    @NotEmpty(message = "cannot be null or empty")
    private String branchAddress;
}
