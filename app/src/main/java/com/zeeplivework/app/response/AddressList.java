package com.zeeplivework.app.response;

public class AddressList {
    public String user_name;
    public String mobile_number;
    public String pin_code;
    public String house_number;
    public String street_name;
    public String address_type;

    public  AddressList(){

    }

    public AddressList(String user_name, String mobile_number, String pin_code, String house_number, String street_name, String address_type) {
        this.user_name = user_name;
        this.mobile_number = mobile_number;
        this.pin_code = pin_code;
        this.house_number = house_number;
        this.street_name = street_name;
        this.address_type = address_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }
}
