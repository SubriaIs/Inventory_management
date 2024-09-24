package com.subria.fi.repositories;

import com.subria.fi.interfaces.GenericRepository;
import com.subria.fi.model.Product;
import com.subria.fi.utils.ProductServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements GenericRepository<Product, Long> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("inventoryManagementPU");
    protected static final Logger logger = LogManager.getLogger(ProductRepositoryImpl.class);

    @Override
    public List<Product> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT s FROM Product s", Product.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    @Override
    public Optional<Product> findById(Long id) {
        ProductServiceValidator.validateProductIdParameters(id);
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Product.class, id));
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Product> findByName(String name) {
        ProductServiceValidator.validateProductNameParameters(name);
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Product.class, name));
        } finally {
            em.close();
        }
    }


    @Override
    public void save(Product product) {
        ProductServiceValidator.validateProductNameParameters(product.getProductName());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (product.getProductId() == null) {
                em.persist(product);
            } else {
                logger.error(product.getProductId() + "Id already exist. ");
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Product update(Product product) {
        ProductServiceValidator.validateProductParameters(product.getProductId(), product.getProductName());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (product.getProductId() != null) {
                product = em.merge(product);
            } else {
                logger.error(product.getProductId() + "Id not found. ");
            }
            em.getTransaction().commit();
            return product;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        ProductServiceValidator.validateProductIdParameters(id);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
