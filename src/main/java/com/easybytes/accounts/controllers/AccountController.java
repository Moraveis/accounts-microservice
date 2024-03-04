package com.easybytes.accounts.controllers;

import com.easybytes.accounts.constantes.AccountsConstants;
import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.dto.ErrorResponseDto;
import com.easybytes.accounts.dto.ResponseDto;
import com.easybytes.accounts.services.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/api/accounts/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name = "Accounts API", description = "CRUD REST API manage accounts")
public class AccountController {

    private final IAccountService accountService;

    private final Environment environment;

    @Value("${build.version}")
    private String buildVersion;

    @Operation(summary = "Get build details")
    @ApiResponse(responseCode = "200", description = "Build Details")
    @GetMapping("build-details")
    public ResponseEntity<String> getBuildDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(summary = "Get java details")
    @ApiResponse(responseCode = "200", description = "Java Details")
    @GetMapping("java-details")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(System.getProperty("JAVA_HOME"));
    }

    @Operation(summary = "Create account and customer")
    @ApiResponse(responseCode = "201", description = "Account created with success")
    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(summary = "Find an account")
    @ApiResponse(responseCode = "200", description = "Account created with success")
    @GetMapping(path = "fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits") String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "Update an account details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account updated with success"),
            @ApiResponse(responseCode = "500", description = "Something went wrong", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping(path = "update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean updatedAccount = accountService.updateAccount(customerDto);
        if (!updatedAccount) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

    @Operation(summary = "Delete an account details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account deleted with success"),
            @ApiResponse(responseCode = "500", description = "Something went wrong", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping(path = "delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits") String mobileNumber) {
        boolean accountDeleted = accountService.deleteAccount(mobileNumber);
        if (!accountDeleted) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

}
