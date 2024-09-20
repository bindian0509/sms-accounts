package com.bharat.sms.accounts.mapper;

import com.bharat.sms.accounts.dto.CustomerDto;
import com.bharat.sms.accounts.entity.Customer;

/*
 * @author Bharat V. <bindian0509@gmail.com>
 * @created Saturday, 21 September 2024
 */
public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}