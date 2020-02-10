package com.shop.usersManagement;
import com.shop.personsManagement.Person;
public class User {
    private long userCode;
    private String login;
    private String password;
    private Person person;
    private String role;

    public User(long userCode, String login, String password, Person person, String role) {
        this.userCode = userCode;
        this.login = login;
        this.password = password;
        this.person = person;
        this.role = role;
    }

    public User(long userCode, String login, String password, String role) {
        this.userCode = userCode;
        this.login = login;
        this.password = password;
        this.role = role;
    }
    public User(String login, String password, String role,Person person) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.person = person;
    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public long getUserCode() {
        return userCode;
    }

    public void setUserCode(long userCode) {
        this.userCode = userCode;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
