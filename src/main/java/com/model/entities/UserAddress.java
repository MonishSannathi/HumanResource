package com.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.model.enums.AddrType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@JsonIgnoreProperties(value={ "userEntity" }, allowSetters= true)
@Entity
@Table(name="UserAddress")
public class UserAddress {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Address1 is required")
    @Column(name="addrLn1")
    private String addrLn1;

    @NotNull(message = "Address2 is required")
    @Column(name="addrLn2")
    private String addrLn2;

    @Column(name="addrName")
    private String addrName;

    @NotNull(message = "Address Type is required")
    @Column(name="addrType")
    @Enumerated(EnumType.STRING)
    private AddrType addrType;

    @NotNull(message = "City is required")
    @Column(name="city")
    private String city;

    @NotNull(message = "Country is required")
    @Column(name="country")
    private String country;

    @NotNull(message = "Location Code is required")
    @Column(name="locationCode")
    private String locationCode;

    @NotNull(message = "Postal Code is required")
    @Column(name="postalCode")
    private String postalCode;

    @NotNull(message = "State Code is required")
    @Column(name="stateCode")
    private String stateCode;

    @NotNull(message = "User Mapping is required")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("userEntity")
    private UserEntity userEntity;

    @JsonbTransient
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddrLn1() {
        return addrLn1;
    }

    public void setAddrLn1(String addrLn1) {
        this.addrLn1 = addrLn1;
    }

    public String getAddrLn2() {
        return addrLn2;
    }

    public void setAddrLn2(String addrLn2) {
        this.addrLn2 = addrLn2;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public AddrType getAddrType() {
        return addrType;
    }

    public void setAddrType(AddrType addrType) {
        this.addrType = addrType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

}
