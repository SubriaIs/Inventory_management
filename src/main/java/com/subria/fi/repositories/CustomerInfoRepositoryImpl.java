package com.subria.fi.repositories;

import com.subria.fi.interfaces.CustomerInfoRepository;
import com.subria.fi.model.CustomerInfo;
import com.subria.fi.utils.CustomerInfoServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CustomerInfoRepositoryImpl implements CustomerInfoRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("inventoryManagementPU");
    protected static final Logger logger = LogManager.getLogger(CustomerInfoRepositoryImpl.class);


    @Override
    public Optional<CustomerInfo> findByCompanyName(String companyName) {
        CustomerInfoServiceValidator.validateCustomerCompanyNameParameters(companyName);
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(CustomerInfo.class, companyName));
        } finally {
            em.close();
        }
    }

    @Override
    public List<CustomerInfo> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CustomerInfo> query = em.createQuery("SELECT s FROM CustomerInfo s", CustomerInfo.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<CustomerInfo> findById(Long id ) {
        CustomerInfoServiceValidator.validateCustomerIdParameters(id);
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(CustomerInfo.class, id));
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<CustomerInfo> findByName(String name) {
        CustomerInfoServiceValidator.validateCustomerNameParameters(name);
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(CustomerInfo.class, name));
        } finally {
            em.close();
        }
    }

    @Override
    public void save(CustomerInfo customerInfo) {
        CustomerInfoServiceValidator.validateCustomerParameters(customerInfo.getName(),customerInfo.getEmail(),customerInfo.getPhoneNumber());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (customerInfo.getContactPersonId() == null) {
                em.persist(customerInfo);
            } else {
                logger.error(customerInfo.getContactPersonId() + "Id already exist. ");
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @Override
    public CustomerInfo update(CustomerInfo customerInfo) {
        CustomerInfoServiceValidator.validateCustomerAllParameters(customerInfo.getContactPersonId(),customerInfo.getName(),customerInfo.getEmail(), customerInfo.getPhoneNumber());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (customerInfo.getContactPersonId() != null) {
                customerInfo = em.merge(customerInfo);
            } else {
                logger.error(customerInfo.getContactPersonId() + "Id not found. ");
            }
            em.getTransaction().commit();
            return customerInfo;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        CustomerInfoServiceValidator.validateCustomerIdParameters(id);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            CustomerInfo customerInfo = em.find(CustomerInfo.class, id);
            if (customerInfo != null) {
                em.remove(customerInfo);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
