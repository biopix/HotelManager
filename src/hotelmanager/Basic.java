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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author shahr
 */
public class Basic extends Room 
{
    final private double COST=50.0; 
    public Basic()
    {
        super();
    }
    public Basic(int roomnumber)
    {
        super(roomnumber);
    }
    //@Override
    public static int getAvailableRoom()throws IOException
    {
        File file = new File("basic.txt"); 
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",");
        if(sc.hasNext())
        {
            return sc.nextInt();
        }
        else
        {
            return 0;
        }
    }
    @Override
    public void occupyRoom(int roomnumber)throws IOException
    {
        int i;
        File file = new File("basic.txt"); 
        Scanner sc = new Scanner(file);
        ArrayList<Integer> rooms=new ArrayList<>();
        sc.useDelimiter(",");
        while(sc.hasNext())
        {
            rooms.add(sc.nextInt());
        }
        sc.close();
        for(int j=0;j<rooms.size();j++)
        {
            if(rooms.get(j)==roomnumber)
                rooms.remove(j);
        }
        //Write new ArrayList to the file
        FileWriter fw=new FileWriter("basic.txt",false);
        PrintWriter pw = new PrintWriter(fw);
        for(i=0;i<rooms.size()-1;i++)
        {
            pw.print(rooms.get(i)+",");
        }
        if(i!=0) pw.print(rooms.get(i));
        pw.close();
    }
}
