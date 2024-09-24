package com.subria.fi.services;

import com.subria.fi.interfaces.CustomerInfoRepository;
import com.subria.fi.model.CustomerInfo;
import com.subria.fi.repositories.CustomerInfoRepositoryImpl;

import java.util.List;
import java.util.Optional;


public class CustomerInfoService {
    private CustomerInfoRepositoryImpl customerInfoRepository;

    public CustomerInfoService(CustomerInfoRepositoryImpl customerInfoRepository) {
        this.customerInfoRepository = customerInfoRepository;
    }

    public List<CustomerInfo> getAllCustomerInfo() {
        return customerInfoRepository.findAll();
    }

    public Optional<CustomerInfo> getCustomerInfoById(Long id) {
        return customerInfoRepository.findById(id);
    }

    public Optional<CustomerInfo> getCustomerInfoByName(String name) {
        return customerInfoRepository.findByName(name);
    }

    public Optional<CustomerInfo> getCustomerInfoByCompanyName(String companyName) {
        return customerInfoRepository.findByName(companyName);
    }

    public void createCustomerInfo(CustomerInfo customerInfo) {
        customerInfoRepository.save(customerInfo);
    }

    public CustomerInfo UpdateCustomerInfo(CustomerInfo customerInfo) {
        return customerInfoRepository.update(customerInfo);
    }

    public void removeCustomerInfo(Long id) {
        customerInfoRepository.delete(id);
    }
}
