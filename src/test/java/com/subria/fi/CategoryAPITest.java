package com.subria.fi;

import com.subria.fi.apis.CategoryAPI;
import com.subria.fi.model.Category;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Ensure this annotation is present
public class CategoryAPITest extends JerseyTest {

    private static final String PATH_CATEGORY = "/v1/category";

    @Override
    protected Application configure() {
        return new ResourceConfig(CategoryAPI.class)
                .register(new CategoryAPI());
    }

    @BeforeAll
    public void setUp() throws Exception {
        super.setUp();
        seedDatabase();
    }


    @Test
    public void testGetAllCategories() {
        HashMap<String, Object> hashObjects = getAllCategories();
        List<Category> categories = (List<Category>) hashObjects.get("categories");
        assertNotNull(categories.get(categories.size() - 1).getCategoryName()); //last index name not null
    }

    @Test
    public void testUpdateCategories() {
        HashMap<String, Object> hashObjects = getAllCategories();
        List<Category> categories = (List<Category>) hashObjects.get("categories");
        Category updateCategory = categories.get(0);
        updateCategory.setCategoryName("Update Category");
        try (Response response = target(PATH_CATEGORY + "/id/{id}")
                .resolveTemplate("id", updateCategory.getCategoryId())
                .request()
                .put(Entity.json(updateCategory))) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus()); // Check if the update was successful (status 200)
            Category updated = response.readEntity(new GenericType<Category>() {
            });
            assertEquals(updateCategory.getCategoryName(), updated.getCategoryName());

        }
    }

    @Test
    public void testDeleteCategories() {
        HashMap<String, Object> hashObjects = getAllCategories();
        List<Category> categories = (List<Category>) hashObjects.get("categories");
        Category delectCategory = categories.get(0);
        final Response response = target(PATH_CATEGORY + "/id/{id}").resolveTemplate("id", delectCategory.getCategoryId()).request().delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    private HashMap<String, Object> getAllCategories() {
        HashMap<String, Object> returnObjects = new HashMap<>();
        Response response = target(PATH_CATEGORY).request(MediaType.APPLICATION_JSON).get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(response.getMediaType().toString(), MediaType.APPLICATION_JSON);
        // Read the response entity as a list of Category objects
        List<Category> categories = response.readEntity(new GenericType<>() {
        });

        returnObjects.put("response", response);
        returnObjects.put("categories", categories);
        assertFalse(categories.isEmpty());
        return returnObjects;
    }

    private void seedDatabase() {
        // Seed data for testing
        Category category = new Category();
        category.setCategoryId(null);
        category.setCategoryName("Test Category");

        // Perform a POST request to add the category
        Response response = target("/v1/category")
                .request()
                .post(Entity.json(category));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
