import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class BuscaUSB {

	
	//Obtengo lista de vinculados
	public ArrayList<String> conexiones(ArrayList<String> lista) throws IOException
	{
		
		//Lista de vinculados
		ArrayList<String> registro=new ArrayList<String>();
		//Lista de dispositivos
		ArrayList<String> dispos=new ArrayList<String>();
		//Lista de conectados y sincronizables
		ArrayList<String> temp=new ArrayList<String>();
		registro=lista;
		
		//Comandos linux
		String command="lsusb";
		
		//Comando terminal
		Process proc = Runtime.getRuntime().exec(command);
		
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		
		//extraemos id de dispositivos en live
		String s = null;
        while ((s = stdInput.readLine()) != null) {

        	s=s.substring(23, 31); 	
        	dispos.add(s);    	
        	
        }
        
        //Analiza si hay dispositivos vinculados
        for(int i=0;i<registro.size();i++)
        {
        	
        	String id;
        	id=registro.get(i);
        	
        	if(dispos.contains(id)==true)
        	{
        		//Añade id de dispositivos
        		temp.add(id);	
        		
        	}
        	
        }
        
		return temp;

	}
	
}
