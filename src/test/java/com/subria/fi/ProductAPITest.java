package com.subria.fi;

import com.subria.fi.apis.CategoryAPI;
import com.subria.fi.apis.ProductAPI;
import com.subria.fi.model.Category;
import com.subria.fi.model.Product;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductAPITest extends JerseyTest {

    private static final String PATH_CATEGORY = "/v1/category";

    private static final String PATH_PRODUCT = "/v1/product";

    @Override
    protected Application configure() {
        return new ResourceConfig()
                .packages("com.subria.fi");
    }

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        seedDatabase();
    }

    @Test
    public void testGetAllProducts(){
        HashMap<String, Object> hashObjects = getAllProducts();
        List<Product> products = (List<Product>) hashObjects.get("products");
        assertNotNull(products.get(products.size()-1).getProductName()); //last index name not null
    }

    private HashMap<String, Object> getAllProducts(){
        HashMap<String, Object> returnObjects= new HashMap<>();
        Response response = target(PATH_PRODUCT).request(MediaType.APPLICATION_JSON).get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(response.getMediaType().toString(), MediaType.APPLICATION_JSON);
        // Read the response entity as a list of Category objects
        List<Product> products = response.readEntity(new GenericType<>() {});

        returnObjects.put("response", response);
        returnObjects.put("products", products);
        assertFalse(products.isEmpty());
        return returnObjects;
    }

    private void seedDatabase() {
        Category category = new Category();
        category.setCategoryId(null);
        category.setCategoryName("Product Category");

        Response categoryResponse = target("/v1/category")
                .request()
                .post(Entity.json(category));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), categoryResponse.getStatus());



        Response response =target(PATH_CATEGORY +"/name/{name}").resolveTemplate("name",category.getCategoryName() ).request().get();
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
        Category resolvedCategory =  response.readEntity(new GenericType<Category>() {});
        assertEquals(category.getCategoryName(), resolvedCategory.getCategoryName());
        assertNotNull(resolvedCategory.getCategoryId());

        //product object
        Product product = new Product();
        product.setProductId(null);
        product.setProductName("sugar");
        product.setCategory(resolvedCategory);


        // Perform a POST request to add the category
        Response responseProduct = target(PATH_PRODUCT)
                .request()
                .post(Entity.json(product));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), responseProduct.getStatus());
    }

}
