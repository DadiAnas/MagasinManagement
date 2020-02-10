package com.shop.checkOutManagement;

import java.util.Collection;

import com.shop.personsManagement.Person;

public class CheckOut {
	private long codeCheckOut;
	private Person person;
	private String description;
	public CheckOut(long codeCheckOut,String description, Person person){
		this.codeCheckOut=codeCheckOut;
		this.person=person;
		this.description=description;
	}
	public CheckOut(String description, Person person){
		this.codeCheckOut=codeCheckOut;
		this.person=person;
		this.description=description;
	}
	private Collection<Operation> operations;
	public long getCodeCheckOut() {
		return codeCheckOut;
	}
	public void setCodeCheckOut(long codeCheckOut) {
		this.codeCheckOut = codeCheckOut;
	}
	public Collection<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
