package com.bharat.sms.accounts.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bharat.sms.accounts.dto.AccountsDto;
import com.bharat.sms.accounts.dto.CustomerDto;
import com.bharat.sms.accounts.entity.Accounts;
import com.bharat.sms.accounts.entity.Customer;
import com.bharat.sms.accounts.exception.CustomerAlreadyExistsException;
import com.bharat.sms.accounts.exception.ResourceNotFoundException;
import com.bharat.sms.accounts.repository.AccountsRepository;
import com.bharat.sms.accounts.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountsServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AccountsServiceImplDiffblueTest {
    @MockBean
    private AccountsRepository accountsRepository;

    @Autowired
    private AccountsServiceImpl accountsServiceImpl;

    @MockBean
    private CustomerRepository customerRepository;

    /**
     * Method under test: {@link AccountsServiceImpl#createAccount(CustomerDto)}
     */
    @Test
    void testCreateAccount() {
        // Arrange
        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByMobileNumber(Mockito.<String>any())).thenReturn(ofResult);

        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("3");
        accountsDto.setBranchAddress("42 Main St");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountsDto(accountsDto);
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setMobileNumber("42");
        customerDto.setName("Name");

        // Act and Assert
        assertThrows(CustomerAlreadyExistsException.class, () -> accountsServiceImpl.createAccount(customerDto));
        verify(customerRepository).findByMobileNumber(eq("42"));
    }

    /**
     * Method under test: {@link AccountsServiceImpl#createAccount(CustomerDto)}
     */
    @Test
    void testCreateAccount2() {
        // Arrange
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1234567890L);
        accounts.setAccountType("3");
        accounts.setBranchAddress("42 Main St");
        accounts.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        accounts.setCustomerId(1L);
        accounts.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setUpdatedBy("2020-03-01");
        when(accountsRepository.save(Mockito.<Accounts>any())).thenReturn(accounts);

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findByMobileNumber(Mockito.<String>any())).thenReturn(emptyResult);

        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("3");
        accountsDto.setBranchAddress("42 Main St");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountsDto(accountsDto);
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setMobileNumber("42");
        customerDto.setName("Name");

        // Act
        accountsServiceImpl.createAccount(customerDto);

        // Assert
        verify(customerRepository).findByMobileNumber(eq("42"));
        verify(accountsRepository).save(isA(Accounts.class));
        verify(customerRepository).save(isA(Customer.class));
    }

    /**
     * Method under test: {@link AccountsServiceImpl#fetchAccount(String)}
     */
    @Test
    void testFetchAccount() {
        // Arrange
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1234567890L);
        accounts.setAccountType("3");
        accounts.setBranchAddress("42 Main St");
        accounts.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        accounts.setCustomerId(1L);
        accounts.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setUpdatedBy("2020-03-01");
        Optional<Accounts> ofResult = Optional.of(accounts);
        when(accountsRepository.findByCustomerId(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        Optional<Customer> ofResult2 = Optional.of(customer);
        when(customerRepository.findByMobileNumber(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        CustomerDto actualFetchAccountResult = accountsServiceImpl.fetchAccount("42");

        // Assert
        verify(accountsRepository).findByCustomerId(eq(1L));
        verify(customerRepository).findByMobileNumber(eq("42"));
        AccountsDto accountsDto = actualFetchAccountResult.getAccountsDto();
        assertEquals("3", accountsDto.getAccountType());
        assertEquals("42 Main St", accountsDto.getBranchAddress());
        assertEquals("42", actualFetchAccountResult.getMobileNumber());
        assertEquals("Name", actualFetchAccountResult.getName());
        assertEquals("jane.doe@example.org", actualFetchAccountResult.getEmail());
        assertEquals(1234567890L, accountsDto.getAccountNumber().longValue());
    }

    /**
     * Method under test: {@link AccountsServiceImpl#fetchAccount(String)}
     */
    @Test
    void testFetchAccount2() {
        // Arrange
        when(accountsRepository.findByCustomerId(Mockito.<Long>any()))
                .thenThrow(new CustomerAlreadyExistsException("An error occurred"));

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByMobileNumber(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(CustomerAlreadyExistsException.class, () -> accountsServiceImpl.fetchAccount("42"));
        verify(accountsRepository).findByCustomerId(eq(1L));
        verify(customerRepository).findByMobileNumber(eq("42"));
    }

    /**
     * Method under test: {@link AccountsServiceImpl#fetchAccount(String)}
     */
    @Test
    void testFetchAccount3() {
        // Arrange
        Optional<Accounts> emptyResult = Optional.empty();
        when(accountsRepository.findByCustomerId(Mockito.<Long>any())).thenReturn(emptyResult);

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByMobileNumber(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> accountsServiceImpl.fetchAccount("42"));
        verify(accountsRepository).findByCustomerId(eq(1L));
        verify(customerRepository).findByMobileNumber(eq("42"));
    }

    /**
     * Method under test: {@link AccountsServiceImpl#fetchAccount(String)}
     */
    @Test
    void testFetchAccount4() {
        // Arrange
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findByMobileNumber(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> accountsServiceImpl.fetchAccount("42"));
        verify(customerRepository).findByMobileNumber(eq("42"));
    }

    /**
     * Method under test: {@link AccountsServiceImpl#updateAccount(CustomerDto)}
     */
    @Test
    void testUpdateAccount() {
        // Arrange
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1234567890L);
        accounts.setAccountType("3");
        accounts.setBranchAddress("42 Main St");
        accounts.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        accounts.setCustomerId(1L);
        accounts.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setUpdatedBy("2020-03-01");
        Optional<Accounts> ofResult = Optional.of(accounts);

        Accounts accounts2 = new Accounts();
        accounts2.setAccountNumber(1234567890L);
        accounts2.setAccountType("3");
        accounts2.setBranchAddress("42 Main St");
        accounts2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        accounts2.setCustomerId(1L);
        accounts2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts2.setUpdatedBy("2020-03-01");
        when(accountsRepository.save(Mockito.<Accounts>any())).thenReturn(accounts2);
        when(accountsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        Optional<Customer> ofResult2 = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer2.setCustomerId(1L);
        customer2.setEmail("jane.doe@example.org");
        customer2.setMobileNumber("42");
        customer2.setName("Name");
        customer2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer2.setUpdatedBy("2020-03-01");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("3");
        accountsDto.setBranchAddress("42 Main St");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountsDto(accountsDto);
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setMobileNumber("42");
        customerDto.setName("Name");

        // Act
        boolean actualUpdateAccountResult = accountsServiceImpl.updateAccount(customerDto);

        // Assert
        verify(accountsRepository).findById(eq(1234567890L));
        verify(customerRepository).findById(eq(1L));
        verify(accountsRepository).save(isA(Accounts.class));
        verify(customerRepository).save(isA(Customer.class));
        assertTrue(actualUpdateAccountResult);
    }

    /**
     * Method under test: {@link AccountsServiceImpl#updateAccount(CustomerDto)}
     */
    @Test
    void testUpdateAccount2() {
        // Arrange
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1234567890L);
        accounts.setAccountType("3");
        accounts.setBranchAddress("42 Main St");
        accounts.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        accounts.setCustomerId(1L);
        accounts.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setUpdatedBy("2020-03-01");
        Optional<Accounts> ofResult = Optional.of(accounts);

        Accounts accounts2 = new Accounts();
        accounts2.setAccountNumber(1234567890L);
        accounts2.setAccountType("3");
        accounts2.setBranchAddress("42 Main St");
        accounts2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        accounts2.setCustomerId(1L);
        accounts2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts2.setUpdatedBy("2020-03-01");
        when(accountsRepository.save(Mockito.<Accounts>any())).thenReturn(accounts2);
        when(accountsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        Optional<Customer> ofResult2 = Optional.of(customer);
        when(customerRepository.save(Mockito.<Customer>any()))
                .thenThrow(new CustomerAlreadyExistsException("An error occurred"));
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("3");
        accountsDto.setBranchAddress("42 Main St");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountsDto(accountsDto);
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setMobileNumber("42");
        customerDto.setName("Name");

        // Act and Assert
        assertThrows(CustomerAlreadyExistsException.class, () -> accountsServiceImpl.updateAccount(customerDto));
        verify(accountsRepository).findById(eq(1234567890L));
        verify(customerRepository).findById(eq(1L));
        verify(accountsRepository).save(isA(Accounts.class));
        verify(customerRepository).save(isA(Customer.class));
    }

    /**
     * Method under test: {@link AccountsServiceImpl#updateAccount(CustomerDto)}
     */
    @Test
    void testUpdateAccount3() {
        // Arrange
        Optional<Accounts> emptyResult = Optional.empty();
        when(accountsRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("3");
        accountsDto.setBranchAddress("42 Main St");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountsDto(accountsDto);
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setMobileNumber("42");
        customerDto.setName("Name");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> accountsServiceImpl.updateAccount(customerDto));
        verify(accountsRepository).findById(eq(1234567890L));
    }

    /**
     * Method under test: {@link AccountsServiceImpl#updateAccount(CustomerDto)}
     */
    @Test
    void testUpdateAccount4() {
        // Arrange
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1234567890L);
        accounts.setAccountType("3");
        accounts.setBranchAddress("42 Main St");
        accounts.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        accounts.setCustomerId(1L);
        accounts.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts.setUpdatedBy("2020-03-01");
        Optional<Accounts> ofResult = Optional.of(accounts);

        Accounts accounts2 = new Accounts();
        accounts2.setAccountNumber(1234567890L);
        accounts2.setAccountType("3");
        accounts2.setBranchAddress("42 Main St");
        accounts2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        accounts2.setCustomerId(1L);
        accounts2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        accounts2.setUpdatedBy("2020-03-01");
        when(accountsRepository.save(Mockito.<Accounts>any())).thenReturn(accounts2);
        when(accountsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("3");
        accountsDto.setBranchAddress("42 Main St");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountsDto(accountsDto);
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setMobileNumber("42");
        customerDto.setName("Name");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> accountsServiceImpl.updateAccount(customerDto));
        verify(accountsRepository).findById(eq(1234567890L));
        verify(customerRepository).findById(eq(1L));
        verify(accountsRepository).save(isA(Accounts.class));
    }

    /**
     * Method under test: {@link AccountsServiceImpl#deleteAccount(String)}
     */
    @Test
    void testDeleteAccount() {
        // Arrange
        doNothing().when(accountsRepository).deleteByCustomerId(Mockito.<Long>any());

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        Optional<Customer> ofResult = Optional.of(customer);
        doNothing().when(customerRepository).deleteById(Mockito.<Long>any());
        when(customerRepository.findByMobileNumber(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        boolean actualDeleteAccountResult = accountsServiceImpl.deleteAccount("42");

        // Assert
        verify(accountsRepository).deleteByCustomerId(eq(1L));
        verify(customerRepository).findByMobileNumber(eq("42"));
        verify(customerRepository).deleteById(eq(1L));
        assertTrue(actualDeleteAccountResult);
    }

    /**
     * Method under test: {@link AccountsServiceImpl#deleteAccount(String)}
     */
    @Test
    void testDeleteAccount2() {
        // Arrange
        doNothing().when(accountsRepository).deleteByCustomerId(Mockito.<Long>any());

        Customer customer = new Customer();
        customer.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customer.setCustomerId(1L);
        customer.setEmail("jane.doe@example.org");
        customer.setMobileNumber("42");
        customer.setName("Name");
        customer.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        customer.setUpdatedBy("2020-03-01");
        Optional<Customer> ofResult = Optional.of(customer);
        doThrow(new CustomerAlreadyExistsException("An error occurred")).when(customerRepository)
                .deleteById(Mockito.<Long>any());
        when(customerRepository.findByMobileNumber(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(CustomerAlreadyExistsException.class, () -> accountsServiceImpl.deleteAccount("42"));
        verify(accountsRepository).deleteByCustomerId(eq(1L));
        verify(customerRepository).findByMobileNumber(eq("42"));
        verify(customerRepository).deleteById(eq(1L));
    }

    /**
     * Method under test: {@link AccountsServiceImpl#deleteAccount(String)}
     */
    @Test
    void testDeleteAccount3() {
        // Arrange
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findByMobileNumber(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> accountsServiceImpl.deleteAccount("42"));
        verify(customerRepository).findByMobileNumber(eq("42"));
    }
}
