/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanager;

import java.io.IOException;

/**
 *
 * @author shahr
 */
public abstract class Room {
    private int roomNumber;
    private boolean isOccupied;
    
    protected Room()
    {
        
    }
    protected Room(int roomNumber)
    {
        this.roomNumber=roomNumber;
    }
    //public abstract int getAvailableRoom()throws IOException;
    public abstract void occupyRoom(int roomnumber)throws IOException;
   
}

