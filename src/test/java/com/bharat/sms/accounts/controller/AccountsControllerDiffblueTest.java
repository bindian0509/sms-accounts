package com.bharat.sms.accounts.controller;

import static org.mockito.Mockito.when;

import com.bharat.sms.accounts.dto.AccountsDto;
import com.bharat.sms.accounts.dto.CustomerDto;
import com.bharat.sms.accounts.service.IAccountsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AccountsController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AccountsControllerDiffblueTest {
    @Autowired
    private AccountsController accountsController;

    @MockBean
    private IAccountsService iAccountsService;

    /**
     * Method under test: {@link AccountsController#createAccount(CustomerDto)}
     */
    @Test
    void testCreateAccount() throws Exception {
        // Arrange
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("3");
        accountsDto.setBranchAddress("42 Main St");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountsDto(accountsDto);
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setMobileNumber("42");
        customerDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link AccountsController#deleteAccountDetails(String)}
     */
    @Test
    void testDeleteAccountDetails() throws Exception {
        // Arrange
        when(iAccountsService.deleteAccount(Mockito.<String>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/delete")
                .param("mobileNumber", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"statusCode\":\"200\",\"statusMsg\":\"Request processed successfully\"}"));
    }

    /**
     * Method under test: {@link AccountsController#deleteAccountDetails(String)}
     */
    @Test
    void testDeleteAccountDetails2() throws Exception {
        // Arrange
        when(iAccountsService.deleteAccount(Mockito.<String>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/delete")
                .param("mobileNumber", "foo");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(417))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":\"417\",\"statusMsg\":\"Delete operation failed. Please try again or contact Dev team\"}"));
    }

    /**
     * Method under test: {@link AccountsController#fetchAccountDetails(String)}
     */
    @Test
    void testFetchAccountDetails() throws Exception {
        // Arrange
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("3");
        accountsDto.setBranchAddress("42 Main St");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountsDto(accountsDto);
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setMobileNumber("42");
        customerDto.setName("Name");
        when(iAccountsService.fetchAccount(Mockito.<String>any())).thenReturn(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/fetch")
                .param("mobileNumber", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"mobileNumber\":\"42\",\"accountsDto\":{\"accountNumber\""
                                        + ":1234567890,\"accountType\":\"3\",\"branchAddress\":\"42 Main St\"}}"));
    }

    /**
     * Method under test:
     * {@link AccountsController#updateAccountDetails(CustomerDto)}
     */
    @Test
    void testUpdateAccountDetails() throws Exception {
        // Arrange
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("3");
        accountsDto.setBranchAddress("42 Main St");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccountsDto(accountsDto);
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setMobileNumber("42");
        customerDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}
