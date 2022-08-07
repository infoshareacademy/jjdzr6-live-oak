package com.infoshareacademy.model;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String telephoneNumber;
    private Address adress;
    private Vehicle vehicle;

    public Client (int id, String name, String surname, String email, String telephoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephoneNumber = telephoneNumber;

    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
