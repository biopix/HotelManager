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
public abstract class Service {
    public int customerID;
    Service(int customerID)
    {
        this.customerID=customerID;
    }
}



class Food extends Service 
{
    final public double COST=30;
    Food(int customerID)
    {
        super(customerID);
    }

}

class Massage extends Service
{
    final public double COST=50;
    Massage(int customerID)
    {
        super(customerID);
    }
}


class Clean extends Service
{
    final public double COST=20;
    Clean(int customerID)
    {
        super(customerID);
    }
}

