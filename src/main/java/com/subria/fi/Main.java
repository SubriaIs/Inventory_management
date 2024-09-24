package com.subria.fi;

import com.subria.fi.exceptions.GenericExceptionMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static void main(String[] args) throws IOException {
        final String BASE_URI = "http://0.0.0.0:8082/";
        // Create ResourceConfig and register packages and exception mappers
        final ResourceConfig rc = new ResourceConfig()
                .packages("com.subria.fi") // Register your resources
                .register(GenericExceptionMapper.class); // Register the exception mapper


        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        server.start();
        System.out.println("Jersey app started with endpoints available at " + BASE_URI);
    }
}