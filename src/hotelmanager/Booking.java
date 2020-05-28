/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanager;
import java.io.*;
//import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 *
 * @author shahr
 */
public class Booking {
    
    public static void returnedBooking() throws IOException
    {
        boolean hasDiscount = false;
        boolean isReturning = false;
        ArrayList<String> record = new ArrayList<>();
        Scanner kb = new Scanner(System.in);
        System.out.print("Please enter the returning customer's unique ID : ");
        String customerID=kb.next();
        while(!customerID.matches("\\d+"))
        {
            System.out.print("Please provide a valid customer ID (ID's start from 10001) : ");
            customerID=kb.next();
        }
        //int bookingDuration=Integer.valueOf(bookingDurationString);
        File file = new File("bookings.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",|\r\n");
        int i = 0;
        String m;
        while (sc.hasNext()) {
            m = sc.next();
            //System.out.println(m+"   "+customerID);
            if (i % 10 == 0) {
                if (m.equals(customerID)) {
                    isReturning = true;
                    hasDiscount = true;
                    for (int l = 1; l <= 9; l++) {
                        record.add(m);
                        m = sc.next();
                    }
                    record.add(m);
                    break;
                }
            }
            i++;
        }
        if (isReturning) {
            for (String el : record) {
                System.out.println(el);
            }
            Customer customer;
            Calendar bookingDate = new GregorianCalendar();
            Calendar startDate;
            //int bookingDuration,roomType;
            int startingYear, startingMonth, startingDay;
            customer = new Customer(Integer.valueOf(customerID), record.get(1), record.get(2), record.get(3));
            System.out.print("Enter Starting Year (yyyy) : ");
        String startingYearString;
        startingYearString=kb.next();
        while(!startingYearString.matches("\\d{4}"))
        {
            System.out.print("Please provide a valid year (YYYY) : ");
            startingYearString=kb.next();
        }
        startingYear=Integer.valueOf(startingYearString);
        
        System.out.print("Enter Starting Month (MM) : ");
        String startingMonthString;
        startingMonthString=kb.next();
        while(!startingMonthString.matches("\\d{2}"))
        {
            System.out.print("Please provide a valid month (MM) : ");
            startingMonthString=kb.next();
        }
        startingMonth=Integer.valueOf(startingMonthString);
        System.out.print("Enter Starting Day (DD) : ");
        String startingDayString;
        startingDayString=kb.next();
        while(!startingDayString.matches("\\d{2}"))
        {
            System.out.print("Please provide a valid day (DD) : ");
            startingDayString=kb.next();
        }
        startingDay=Integer.valueOf(startingDayString);
        startDate = new GregorianCalendar(startingYear, startingMonth, startingDay);
        System.out.print("Enter booking duration in days (Like 4) : ");
        String bookingDurationString=kb.next();
        while(!bookingDurationString.matches("\\d+"))
        {
            System.out.print("Please provide valid number of days to stay (Like 4) : ");
            bookingDurationString=kb.next();
        }
        int bookingDuration=Integer.valueOf(bookingDurationString);
        System.out.println();
        System.out.print("Room Type \t\t\tAvailaibility\n");
        System.out.println();
        System.out.print("1- Basic     \t\t\t");
        if (Basic.getAvailableRoom()!= 0) {
            System.out.print("Yes\n");
        } else {
            System.out.print("No\n");
        }

        System.out.print("2- Junior    \t\t\t");

        if (Junior.getAvailableRoom()!= 0) {
            System.out.print("Yes\n");
        } else {
            System.out.print("No\n");
        }

        System.out.print("3- Full Suite\t\t\t");
        if (FullSuite.getAvailableRoom()!= 0) {
            System.out.print("Yes\n");
        } else {
            System.out.print("No\n");
        }
        System.out.println();
        System.out.print("Please select room type ( 1 or 2 or 3) : ");
        String roomTypeString=kb.next();
        while(!roomTypeString.matches("1|2|3"))
        {
            System.out.print("Please enter 1 or 2 or 3 : ");
            roomTypeString=kb.next();
        }
        int roomType=Integer.valueOf(roomTypeString);
        int roomNumber;
        Room room;
        switch (roomType) 
        {
            case 1:
                roomNumber = Basic.getAvailableRoom();
                room=new Basic(roomNumber);
                room.occupyRoom(roomNumber);
                break;
            case 2:
                roomNumber = Junior.getAvailableRoom();
                room=new Junior(roomNumber);
                room.occupyRoom(roomNumber);
                break;
            default:
                roomNumber = FullSuite.getAvailableRoom();
                room=new FullSuite(roomNumber);
                room.occupyRoom(roomNumber);
                break;
        }
            FileWriter fw = new FileWriter("bookings.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(customerID + "," + customer.getFirstName() + "," + customer.getLastName() + "," + customer.getPhoneNumber() + ","
                    + roomType + "," + roomNumber + "," + bookingDate.getTime() + "," + startDate.getTime() + "," + bookingDuration + "," + isReturning);
            pw.close();
            System.out.println();
            System.out.println("Booking Confirmed! The room number is : " + roomNumber);
            System.out.println("Customer ID is : "+ customerID);
            System.out.println("Enter 1 to return to main menu : ");
            while (kb.next().charAt(0) != '1') {
                System.out.println();
                System.out.println("Booking Confirmed! The room number is : " + roomNumber);
                System.out.println("Customer ID is : "+ customerID);
                System.out.println("Enter 1 to return to main menu : ");
            }
        } else {
            System.out.println("Customer ID does not exist!");
        }

    }
    
    
    public static void newBooking() throws IOException
    {
        Scanner kb = new Scanner(System.in);
        int customerID = Customer.getNewID();
        Customer customer=new Customer();
        Calendar bookingDate = new GregorianCalendar();
        Calendar startDate;
        int startingYear, startingMonth, startingDay;
        customer.getCustomerInfo(customerID);
        System.out.print("Enter Starting Year (yyyy) : ");
        String startingYearString;
        startingYearString=kb.next();
        while(!startingYearString.matches("\\d{4}"))
        {
            System.out.print("Please provide a valid year (YYYY) : ");
            startingYearString=kb.next();
        }
        startingYear=Integer.valueOf(startingYearString);
        
        System.out.print("Enter Starting Month (MM) : ");
        String startingMonthString;
        startingMonthString=kb.next();
        while(!startingMonthString.matches("\\d{2}"))
        {
            System.out.print("Please provide a valid month (MM) : ");
            startingMonthString=kb.next();
        }
        startingMonth=Integer.valueOf(startingMonthString);
        System.out.print("Enter Starting Day (DD) : ");
        String startingDayString;
        startingDayString=kb.next();
        while(!startingDayString.matches("\\d{2}"))
        {
            System.out.print("Please provide a valid day (DD) : ");
            startingDayString=kb.next();
        }
        startingDay=Integer.valueOf(startingDayString);
        startDate = new GregorianCalendar(startingYear, startingMonth, startingDay);
        System.out.print("Enter booking duration in days (Like 4) : ");
        String bookingDurationString=kb.next();
        while(!bookingDurationString.matches("\\d+"))
        {
            System.out.print("Please provide valid number of days to stay (Like 4) : ");
            bookingDurationString=kb.next();
        }
        int bookingDuration=Integer.valueOf(bookingDurationString);
        System.out.println();
        System.out.print("Room Type \t\t\tAvailaibility\n");
        System.out.println();
        System.out.print("1- Basic     \t\t\t");
        if (Basic.getAvailableRoom()!= 0) {
            System.out.print("Yes\n");
        } else {
            System.out.print("No\n");
        }

        System.out.print("2- Junior    \t\t\t");

        if (Junior.getAvailableRoom()!= 0) {
            System.out.print("Yes\n");
        } else {
            System.out.print("No\n");
        }

        System.out.print("3- Full Suite\t\t\t");
        if (FullSuite.getAvailableRoom()!= 0) {
            System.out.print("Yes\n");
        } else {
            System.out.print("No\n");
        }
        System.out.println();
        System.out.print("Please select room type ( 1 or 2 or 3) : ");
        String roomTypeString=kb.next();
        while(!roomTypeString.matches("1|2|3"))
        {
            System.out.print("Please enter 1 or 2 or 3 : ");
            roomTypeString=kb.next();
        }
        int roomType=Integer.valueOf(roomTypeString);
        int roomNumber;
        Room room;
        switch (roomType) {
            case 1:
                roomNumber = Basic.getAvailableRoom();
                room=new Basic(roomNumber);
                room.occupyRoom(roomNumber);
                break;
            case 2:
                roomNumber = Junior.getAvailableRoom();
                room=new Junior(roomNumber);
                room.occupyRoom(roomNumber);
                break;
            default:
                roomNumber = FullSuite.getAvailableRoom();
                room=new FullSuite(roomNumber);
                room.occupyRoom(roomNumber);
                break;
        }
        Customer.increaseID();
        FileWriter fw = new FileWriter("bookings.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(customerID + "," + customer.getFirstName() + "," + customer.getLastName() + "," + customer.getPhoneNumber() + ","
                + roomType + "," + roomNumber + "," + bookingDate.getTime() + "," + startDate.getTime() + "," + bookingDuration + "," + "false");
        pw.close();
        System.out.println();
        System.out.println("Booking Confirmed! The room number is : " + roomNumber);
        System.out.println("Customer ID is : "+customerID);
        System.out.println("Please remember the booking ID for later reference!");
        System.out.println("Enter 1 to return to main menu : ");
        while (kb.next().charAt(0) != '1') {
            System.out.println();
            System.out.println("Booking Confirmed! The room number is : " + roomNumber);
            System.out.println("Customer ID is : "+customerID);
            System.out.println("Enter 1 to return to main menu : ");
        }
    }
}
