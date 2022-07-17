package com.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.model.enums.Gender;
import com.model.enums.UserType;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@JsonIgnoreProperties(value={ "userAddresses" }, allowSetters= true)
@Entity
@Table(name="User")
public class UserEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "First Name is required")
    @Size(min=2,max=50, message = "Name length should be between 2 and 100 characters")
    @Column(name="firstName")
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Size(min=2,max=50, message = "Name length should be between 2 and 100 characters")
    @Column(name="lastName")
    private String lastName;

    @Transient
    private String fullName;

    @NotNull(message = "Gender is required")
    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Mobile Phone is required")
    @Column(name="mobilePhone")
    private String mobilePhone;

    @NotNull(message = "User Type is required")
    @Column(name="userType")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotNull(message = "Email is required")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",message = "Invalid Email Address")
    @Column(name="email")
    private String email;

    @NotNull(message = "Date of Birth is required")
    @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Date of Birth should be in yyyy-mm-dd")
    @Column(name="dateOfBirth")
    private String dateOfBirth;

    @Column(name="annualSalary")
    private int annualSalary;

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY, mappedBy = "userEntity")
    @JsonProperty("userAddresses")
    private Set<UserAddress> userAddresses;

    @JsonbTransient
    public Set<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(Set<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName()+" "+getLastName();
    }

    public void setFullName(String fullName) {
        //This will be empty
        //Since Full Name is combination of First and Last Name
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }

}
