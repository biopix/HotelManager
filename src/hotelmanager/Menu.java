/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author shahr
 */
public class Menu 
{
    public static String mainMenu()
    {
        System.out.println("***********************************");
        System.out.println("* 1- Booking                      *");
        System.out.println("* 2- Room Services                *");
        System.out.println("* 3- Bill                         *");
        System.out.println("* 4- Exit                         *");
        System.out.println("***********************************");
        Scanner kb = new Scanner(System.in);
        String selectedmenu=kb.next();
        while(!selectedmenu.matches("1|2|3|4"))
        {
            System.out.print("Please enter 1 or 2 or 3 or 4: ");
            selectedmenu=kb.next();
        }
        return selectedmenu;
    }
    
    public static String bookingMenu()
    {
        System.out.println("***********************************");
        System.out.println("* 1- New Customer                 *");
        System.out.println("* 2- Returning Customer           *");
        System.out.println("* 3- Main Menu                    *");
        System.out.println("***********************************");

        Scanner kb = new Scanner(System.in);
        String selectedmenu=kb.next();
        while(!selectedmenu.matches("1|2|3"))
        {
            System.out.print("Please enter 1 or 2 or 3: ");
            selectedmenu=kb.next();
        }
        return selectedmenu;
    }
    
    
     public static void serviceMenu(ArrayList<Service> services) throws IOException
     {
        Scanner kb = new Scanner(System.in);
        ArrayList<String> record = new ArrayList<>();
        System.out.print("Welcome to Service Menu. To continue enter a valid resident customer ID : ");
        String customerID=kb.next();
        while(!customerID.matches("\\d+"))
        {
            System.out.print("Please provide a valid customer ID (ID's start from 10001) : ");
            customerID=kb.next();
        }
        File file = new File("bookings.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",|\n");
        int i = 0;
        boolean isCustomer=false;
        while (sc.hasNext()) {
            String m = sc.next();
            //System.out.println(m+"   "+customerID);
            if (i % 10 == 0) {
                if (m.equals(customerID)) {
                    isCustomer=true;
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
        if(isCustomer)
        {
        System.out.println("***********************************");
        System.out.println("* 1- Food                         *");
        System.out.println("* 2- Cleaning                     *");
        System.out.println("* 3- Massage                      *");
        System.out.println("***********************************");

        String selectedmenu=kb.next();
        while(!selectedmenu.matches("1|2|3"))
        {
            System.out.print("Please enter 1 or 2 or 3: ");
            selectedmenu=kb.next();
        }
        switch (selectedmenu)
        {
            case "1":
                System.out.println("Food order placed for Customer ID : "+customerID+"  First Name : "+record.get(1)+"  Last Name : "+record.get(2)+"  Room Number :"+record.get(5));
                services.add(new Food(Integer.valueOf(customerID)));
                break;
            case "2":
                System.out.println("Cleaning order placed for Customer ID : "+customerID+"  First Name : "+record.get(1)+"  Last Name : "+record.get(2)+"  Room Number :"+record.get(5));
                services.add(new Clean(Integer.valueOf(customerID)));
                break;
            case "3":
                System.out.println("Massage order placed for Customer ID : "+customerID+"  First Name : "+record.get(1)+"  Last Name : "+record.get(2)+"  Room Number :"+record.get(5));
                services.add(new Massage(Integer.valueOf(customerID)));
                break;
        }
        }
        else
        {
            System.out.println("No customer with provided ID found! Returning to main Menu ...");
        }
        //return selectedmenu;
    }
    
    
}
