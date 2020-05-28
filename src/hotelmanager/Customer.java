/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 *
 * @author shahr
 */
public class Customer {
    
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int ID;
    public Customer()
    {
        
    }
    public Customer(int ID,String fn,String ln,String pn)
    {
        this.ID=ID;
        firstName=fn;
        lastName=ln;
        phoneNumber=pn;
    }
    public void setID(int ID)
    {
        this.ID=ID;
    }
    public int getID()
    {
        return ID;
    }
    public void setFirstName(String fn)
    {
        firstName=fn;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setLastName(String ln)
    {
        lastName=ln;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setPhoneNumber(String pn)
    {
        phoneNumber=pn;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    public static int getNewID() throws IOException
    {
        File file = new File("lastID.txt"); 
        Scanner sc = new Scanner(file);
        return sc.nextInt()+1;
    }
    public static void increaseID() throws IOException
    {
        File file = new File("lastID.txt"); 
        Scanner sc = new Scanner(file);
        int i=sc.nextInt()+1;
        FileWriter fw=new FileWriter("lastID.txt",false);
        PrintWriter pw = new PrintWriter(fw);
        pw.print(i);
        pw.close();
    }
    public void getCustomerInfo(int id)
    {
        String firstname, lastname, phonenumber;
        Scanner kb=new Scanner(System.in);    
        System.out.print("Enter customer's first name and press Enter : ");
        firstname = kb.next();
        while(!firstname.matches("[a-zA-Z]*"))
        {
            System.out.print("Invalid name! please enter a valid name (Like John) : ");
            firstname=kb.next();
        }
        System.out.print("Enter Customer's last name and press Enter : ");
        lastname = kb.next();
        while(!lastname.matches("[a-zA-z]+([ '-][a-zA-Z]+)*"))
        {
            System.out.print("Invalid last name! please enter a valid name (Like Smith) : ");
            lastname=kb.next();
        }
        System.out.print("Enter Customer's phone number and press Enter : ");
        
        phonenumber=kb.next();
        while(!phonenumber.matches("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}"))
        {
            System.out.print("Invalid phone number! Please enter a valid phone number (Like 1234567890 or 123-456-7890 or (123)456-7890 or (123)4567890 ): ");
            phonenumber=kb.next();
        }
        this.firstName=firstname;
        this.lastName=lastname;
        this.phoneNumber=phonenumber;
        this.ID=id;
    }
}
