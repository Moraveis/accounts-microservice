package com.easybytes.accounts.services.impl;

import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.entities.Account;
import com.easybytes.accounts.entities.Customer;
import com.easybytes.accounts.exceptions.CustomerExistsException;
import com.easybytes.accounts.mapper.CustomerMapper;
import com.easybytes.accounts.repositories.AccountRepository;
import com.easybytes.accounts.repositories.CustomerRepository;
import com.easybytes.accounts.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static com.easybytes.accounts.constantes.AccountsConstants.ADDRESS;
import static com.easybytes.accounts.constantes.AccountsConstants.SAVINGS;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerExists = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (customerExists.isPresent()) {
            throw new CustomerExistsException("Customer exists for the given mobile number: " + customer.getMobileNumber());
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");

        Customer saved = customerRepository.save(customer);
        accountRepository.save(createNewAccount(saved));
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountNumber(generateRandomAccountNumber());
        account.setAccountType(SAVINGS);
        account.setBranchAddress(ADDRESS);

        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");

        return account;
    }

    private static long generateRandomAccountNumber() {
        return 1000000000L + new Random().nextInt(900000000);
    }
}
