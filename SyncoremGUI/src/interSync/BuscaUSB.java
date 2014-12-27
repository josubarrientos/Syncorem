/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interSync;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class BuscaUSB {

	
	
	public ArrayList<String> conexiones() 
	{
		
		
		ArrayList<String> dispos=new ArrayList<String>();
		
		try
                {
		String command="lsusb";
		
		Process proc = Runtime.getRuntime().exec(command);
		
		
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		
		String s = null;
        while ((s = stdInput.readLine()) != null) {
        	
        	dispos.add(s);    	
        	
        }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
        
        
        

		
		return dispos;

	}
	
}