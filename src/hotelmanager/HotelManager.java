/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanager;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author shahr
 */
public class HotelManager{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws IOException
    {
        // TODO code application logic here
        String mainmenu,bookingmenu;
        ArrayList<Service> services=new ArrayList<>();
        while(true)
        {
        mainmenu=Menu.mainMenu();
        switch (mainmenu)
        {
            case "1":
                if(Basic.getAvailableRoom()==0&&Junior.getAvailableRoom()==0&&FullSuite.getAvailableRoom()==0)
                {
                    System.out.println("No Rooms Available!");
                }
                else
                {
                    
                    bookingmenu=Menu.bookingMenu();
                    switch (bookingmenu)
                    {
                        case "1":
                            Booking.newBooking();
                            break;
                        case "2":
                            Booking.returnedBooking();
                            break;
                        case "3":
                            break;
                    }
                    
                }
                break;
            case "2":
                Menu.serviceMenu(services);
                break;
            case "3":
                Bill.getBill(services);
                break;
            case "4":
                System.exit(0);
                break;
        }
        }
    }
    
    
}
