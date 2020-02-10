package com.shop;

import com.shop.personsManagement.Person;
import com.shop.salesManagement.Sale;

import java.time.LocalDate;
import java.util.Date;

public class SendData {
    private static SendData sendDataSingle=null;
    Sale sale=null;
    Person person = null;
    LocalDate date = null;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static SendData getInstance(){
        if(sendDataSingle==null){
            sendDataSingle = new SendData();
        }
        return sendDataSingle;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
