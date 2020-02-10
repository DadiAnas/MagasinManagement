package com.shop.checkOutManagement;

import java.util.Date;

public class Operation {
private CheckOut checkOut;
private long codeOperation;
private double montant;
private String type;
private Date dateOperation;
private long code;

private String descriptionAuto;


public Operation(long codeOperation, double montant, String type,Date date, String descriptionAuto,CheckOut checkOut) {
	this.codeOperation = codeOperation;
	this.checkOut=checkOut;
	this.dateOperation=date;
	this.montant = montant;
	this.type = type;
	System.out.println("hani:"+type);
	this.descriptionAuto = descriptionAuto;
}
public long getCodeOperation() {
	return codeOperation;
}
public void setCodeOperation(long codeOperation) {
	this.codeOperation = codeOperation;
}
public double getMontant() {
	return montant;
}
public void setMontant(double montant) {
	this.montant = montant;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getDescriptionAuto() {
	return descriptionAuto;
}
public void setDescriptionAuto(String descriptionAuto) {
	this.descriptionAuto = descriptionAuto;
}
public Date getDateOperation() {
	return dateOperation;
}
public void setDateOperation(Date dateOperation) {
	this.dateOperation = dateOperation;
}
public long getCode() {
	return code;
}
public void setCode(long code) {
	this.code = code;
}
public CheckOut getCheckOut() {
	return checkOut;
}
public void setCheckOut(CheckOut checkOut) {
	this.checkOut = checkOut;
}

}
