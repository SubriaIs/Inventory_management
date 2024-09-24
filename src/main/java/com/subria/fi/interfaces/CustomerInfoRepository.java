package com.subria.fi.interfaces;

import com.subria.fi.model.CustomerInfo;

import java.util.Optional;

public interface CustomerInfoRepository extends GenericRepository <CustomerInfo, Long>{
    Optional<CustomerInfo> findByCompanyName(String companyName);
}
