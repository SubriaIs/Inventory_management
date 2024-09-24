package com.subria.fi.utils;

public final class CustomerInfoServiceValidator {

    public static void validateCustomerAllParameters(Long contactPersonId, String name, String email, String phoneNumber){

        if (contactPersonId == null || contactPersonId < 1) {
            throw new IllegalArgumentException("contactPerson Id must be a positive number");
        }

        if (name == null || email == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        if (name.trim().isEmpty() || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!phoneNumber.matches("^[0-9]{7,15}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }

    }

    public static void validateCustomerParameters( String name, String email, String phoneNumber){

        if (name == null || email == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        if (name.trim().isEmpty() || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!phoneNumber.matches("^[0-9]{7,15}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }

    }
    public static void validateCustomerIdParameters(Long contactPersonId){
        if (contactPersonId == null || contactPersonId < 1) {
            throw new IllegalArgumentException("contactPerson Id must be a positive number");
        }
    }
    public static void validateCustomerNameParameters(String name){
        if(name == null  || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null");
        }
    }
    public static void validateCustomerCompanyNameParameters(String companyName){
        if(companyName == null  || companyName.isEmpty()){
            throw new IllegalArgumentException("Company Name cannot be null");
        }
    }
}
