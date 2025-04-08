package com.springboot.dev_spring_boot_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Tên công ty không được để trống")
    @Size(min = 3, message = "Tên công ty phải có ít nhất 3 ký tự")
    @Column(name = "name_of_company")
    private String nameOfCompany;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Thành phố không được để trống")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "Vui lòng chọn quốc gia")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "region khong duong de trong")
    @Column(name = "region")
    private String region;

    @Min(value = 10000, message = "Mã Zip phải có ít nhất 5 chữ số")
    @Max(value = 999999, message = "Mã Zip không được vượt quá 6 chữ số")
    @Column(name = "zip_code")
    private int zipCode;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}