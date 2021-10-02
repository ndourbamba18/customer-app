package com.parlonsdev.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "phone")
        }
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @NotNull
    @Size(max = 60)
    private String name;
    @NotNull
    @Size(max = 80)
    @Email
    @NaturalId
    private String email;
    @NotNull
    @Size(max = 15)
    private String phone;
    @Column(name = "code_customer", nullable = false, updatable = false)
    private String customerCode;
    @NotNull
    private Boolean isActive;
    @NotNull
    @Lob
    private String address;

    public Customer() { }

    public Customer(String name, String email, String phone, String customerCode, Boolean isActive, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.customerCode = customerCode;
        this.isActive = isActive;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", isActive=" + isActive +
                ", address='" + address + '\'' +
                '}';
    }
}
