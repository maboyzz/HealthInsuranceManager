package com.nthuy.healthinsurancemanager.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String passWord;
    private String role; // Ví dụ: "Admin", "Nhân viên"
    private String phone;
    private String address;
    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<HealthInsuranceCard> cards;

    public Users(long id, String userName, String passWord, String phone, String role, String address, String email, List<HealthInsuranceCard> cards) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.email = email;
        this.cards = cards;
    }

    public Users() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<HealthInsuranceCard> getCards() {
        return cards;
    }

    public void setCards(List<HealthInsuranceCard> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", cards=" + cards +
                '}';
    }
}
