package com.example.parcours_mobile_7_gestiondesemployee;

public class User {

    private String _id,email,phone,password, firstname, lastname;

    public User(String _id, String email, String phone, String password, String firstname, String lastname) {
        this._id = _id;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(String email, String phone, String password, String firstname, String lastname) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
