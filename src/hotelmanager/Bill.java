/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author shahr
 */
public class Bill {
    
    private int customerId;
    private int firstName;
    private int lastName;
    private int roomNumber;
    private double cost;
    private boolean hasDiscount;

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFirstName() {
        return firstName;
    }

    public void setFirstName(int firstName) {
        this.firstName = firstName;
    }

    public int getLastName() {
        return lastName;
    }

    public void setLastName(int lastName) {
        this.lastName = lastName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    
    
    public static void getBill (ArrayList<Service> services) throws FileNotFoundException
    {
        int foodcost=0;
        int cleaningcost=0;
        int massagecost=0;
        boolean hasDiscount=false;
        String firstname;
        String lastname;
        String bookingduration;
        String roomtype;
        String roomnumber;
        double roomcost;
        Integer customerID=getID();
        ArrayList<String> record = new ArrayList<>();
        //for(Service s:services)
        for(int m=services.size()-1;m>=0;m--)
        {
            if(services.get(m).customerID==customerID)
            {
                if(services.get(m) instanceof Food)
                {
                    foodcost+=((Food)services.get(m)).COST;
                }
                else if (services.get(m) instanceof Clean)
                {
                    cleaningcost+=((Clean)services.get(m)).COST;
                }
                else
                {
                    massagecost+=((Massage)services.get(m)).COST;
                }
                services.remove(m);
            }
        }
        
        File file = new File("bookings.txt");
        Scanner sc = new Scanner(file);
        //sc.useDelimiter(",|\r\n");
        int idcount=0;
        int i = 0;
        String str2="";
        String m="";
        while (sc.hasNextLine()) 
        {
            m = sc.nextLine();
            //System.out.println(m+"   "+customerID);
            if(m.contains(String.valueOf(customerID)))
            {
                
                idcount++;
                str2=m;
                //System.out.println(idcount);
            }
                i++;
        }
        
        System.out.println(str2);
        String s="";
        Scanner input=new Scanner(str2);
        
        input.useDelimiter(",");
        while(input.hasNext())
        {
            s=input.next();
            record.add(s);
        }
        
        if(idcount>=2)
        {
            //System.out.println(idcount);
            hasDiscount=true;
        }
        else if(idcount==1)
        {
            hasDiscount=false;
        }
        else
        {
            System.out.println("Customer does not exist!");
            return;
        }
        roomnumber=record.get(record.size()-5);
        roomtype=record.get(record.size()-6);
        //customerID
        bookingduration=record.get(record.size()-2);
        firstname=record.get(record.size()-9);
        lastname=record.get(record.size()-8);
        //foodcost
        //massagecost
        //cleaningcost
        Room room;
        switch(roomtype)
        {
            case "1":
                roomtype="Basic";
                room=new Basic();
                roomcost=Integer.valueOf(bookingduration)* 50;
                break;
            case "2":
                roomtype="Junior";
                room=new Junior();
                roomcost=Integer.valueOf(bookingduration)* 125;
                break;
            default:
                roomtype="Full Suite";
                room=new FullSuite();
                roomcost=Integer.valueOf(bookingduration)* 200;
                break;
            
        }
        if(hasDiscount)
            roomcost=roomcost-(.1*roomcost);
        System.out.println("Biiling for :");
        System.out.print("Customer ID : "+customerID +"\tFirst Name : "+firstname+"\tLast Name : "+lastname+"\tRoom Number : "+roomnumber+"\n");
        System.out.print("Booking duration was "+bookingduration+" Days\t"+"Room type was "+roomtype+"\n");
        if(hasDiscount)
        {
            System.out.println("Amount including 10% discount for room :");
        }
        else
        {
            System.out.println("Amount is :");
        }
        System.out.printf("Food services     :\t%5s",foodcost+"$\n");
        System.out.printf("Cleaning Services :\t%5s",cleaningcost+"$\n");
        System.out.printf("Massage Services  :\t%5s",massagecost+"$\n");
        System.out.printf("Room cost         :\t%5s",roomcost+"$\n" );
        System.out.println("------------------------------------------");
        System.out.printf("Total cost       :\t%5s",foodcost+cleaningcost+massagecost+roomcost+"$\n");
        
        
    }
    public static void displayBill(int customerID)
    {
        
    }
    
    private static int getID()
    {
        Scanner kb = new Scanner(System.in);
        System.out.print("Please enter the  customer ID (IDs start from 10001) : ");
        String customerID=kb.next();
        while(!customerID.matches("\\d+"))
        {
            System.out.print("Please provide a valid customer ID (IDs start from 10001) : ");
            customerID=kb.next();
        }
        return(Integer.valueOf(customerID));
    }
}
