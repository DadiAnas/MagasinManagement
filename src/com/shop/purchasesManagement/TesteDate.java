package com.shop.purchasesManagement;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
public class TesteDate {
	public static void teste1(){
		 Date today = new Date();
		    Locale[] locales = { java.util.Locale.US, java.util.Locale.UK, java.util.Locale.GERMANY, java.util.Locale.FRANCE };
		    int[] styles = { java.text.DateFormat.FULL, java.text.DateFormat.LONG, java.text.DateFormat.MEDIUM, java.text.DateFormat.SHORT };
		    String[] styleNames = { "FULL", "LONG", "MEDIUM", "SHORT" };
		    DateFormat fmt = null;
		    for (Locale locale : locales) {
		      System.out.println("\nThe Date for " + locale.getDisplayCountry() + ":");
		      for (int i = 0; i < styles.length; i++) {
		        fmt = DateFormat.getDateInstance(styles[i], locale);
		        System.out.println("\tIn " + styleNames[i] + " is " + fmt.format(today));
		      }
		    }
	}


	public static void teste2(){
		LocalDate datel=LocalDate.now();
		 Date today = new Date();
		    Locale locale = java.util.Locale.FRANCE;
		    int[] styles = { java.text.DateFormat.FULL, java.text.DateFormat.LONG, java.text.DateFormat.MEDIUM, java.text.DateFormat.SHORT };
		    String[] styleNames = { "FULL", "LONG", "MEDIUM", "SHORT" };
		    DateFormat fmt = null;
		      for (int i = 0; i < styles.length; i++) {
		        fmt = DateFormat.getDateInstance(styles[i], locale);
		        System.out.println("\tIn " + styleNames[i] + " is " + fmt.format(today));
		     
		    }
	}

	public static void test4(){
		
		 //Converting String in format 'dd-MMM-yyyy' to LocalDate
		 
		   //Converting String in format 'dd/MM/YY' to LocalDate
		   String dateStr_3="28/09/16";
		   DateTimeFormatter formatter_3=DateTimeFormatter.ofPattern("dd/MM/yy");
		   LocalDate localDate_3= LocalDate.parse(dateStr_3,formatter_3);
		   System.out.println("Input String with value: "+dateStr_3);
		   System.out.println("Converted Date in default ISO format: "+localDate_3);
		   java.sql.Date dd=java.sql.Date.valueOf(localDate_3);
	}
	public static void teste3(){
		LocalDate datel=LocalDate.of(2015, 12, 31);
		java.sql.Date datesql=java.sql.Date.valueOf(datel);
		System.out.println(datesql);
		
		
	
	}

	
	public static void main(String[] args) {
		   test4();
		  }
		
}
