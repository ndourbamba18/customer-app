package com.parlonsdev.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomerDto {
    @NotBlank
    @Size(max = 60)
    private String name;
    @NotBlank
    @Size(max = 80)
    private String email;
    @NotBlank
    @Size(max = 15)
    private String phone;
    private String customerCode;
    private Boolean isActive;
    @NotBlank
    private String address;

    public CustomerDto() { }

    public CustomerDto(@NotBlank @Size(max = 60) String name, @NotBlank @Size(max = 80) String email,
                       @NotBlank @Size(max = 15) String phone, String customerCode,
                       Boolean isActive, @NotBlank String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.customerCode = customerCode;
        this.isActive = isActive;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
