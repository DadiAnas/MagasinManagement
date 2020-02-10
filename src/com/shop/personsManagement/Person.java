package com.shop.personsManagement;

public class Person {
private long personCode;
private String firstName;
private String lastName;
private String telephone;
private String email;
private String type;



public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public long getPersonCode() {
	return personCode;
}
public void setPersonCode(long personCode) {
	this.personCode = personCode;
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
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Person(String firstName, String lastName, String telephone, String email,String type) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.telephone = telephone;
	this.email = email;
	this.type = type;
}
public Person(long personCode, String firstName, String lastName, String telephone, String email,String type) {
	this.personCode = personCode;
	this.firstName = firstName;
	this.lastName = lastName;
	this.telephone = telephone;
	this.email = email;
	this.type=type;
}
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return firstName+ " "+lastName;
	}
}
