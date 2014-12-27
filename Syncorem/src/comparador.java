import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class comparador {

	public void compara(String path1,String path2) throws IOException, ParseException
	{
		//Path directorio con registros
		String pathint="RegistroSyncorem/ListadoINT";
		String pathext="RegistroSyncorem/ListadoEXT";
		String pathdife="RegistroSyncorem/diff";
		
		//Nobre de carpetas padre a sincronizar(tienen que ser iguales)
		String nomcaparpeta1;
		String nomcaparpeta2;

		//Listas de contenido de ficheros y de diferencia
		ArrayList<String> home=new ArrayList<String>();
		ArrayList<String> media=new ArrayList<String>();
		ArrayList<String> diferencias=new ArrayList<String>(); 
		
		//Carga ficherois en arrayLoist
		File fichhome=new File(pathint);
		File fichmedia=new File(pathext);
		
		FileReader frhome=new FileReader(fichhome);
		FileReader frmedia=new FileReader(fichmedia);
		
		BufferedReader brhome=new BufferedReader(frhome);
		BufferedReader brmedia=new BufferedReader(frmedia);
	
		diferencias.add(path1);
		diferencias.add(path2);
		
		String linea;
		
        while((linea=brhome.readLine())!=null)
        {
        	
        	home.add(linea); 
        	
        }
        
        while((linea=brmedia.readLine())!=null)
        {
        	
        	media.add(linea); 
        	
        }
        
        //Obtenemos el nombre deldirectorio padre
        String[] name1;
        String[] name2;

        name1=home.get(0).split(";");
        nomcaparpeta1=name1[1];
        
        name2=media.get(0).split(";");
        nomcaparpeta2=name2[1];
        
        //Buscamos la existencia en ambos
        if(nomcaparpeta1.equals(nomcaparpeta2)!=true)
        {
        	
        	System.out.println("No existe un sistema de archivos paralelo valido");
        	
        }else
        {

        int supercontador;
        
        //Contador que recorre el directoio home
        for(supercontador=0;supercontador<home.size();supercontador++)
        {
        	
        	String temp2;
        	String cadenahome,pathhome = null;
        	cadenahome=home.get(supercontador);
        	int conext=0;
        	
        	//Extrae nombre de elemento
        	pathhome=cadenahome.substring(cadenahome.indexOf(nomcaparpeta1), cadenahome.length()-19);
        	//Obtiene fecha
        	temp2=cadenahome.substring(cadenahome.length()-19);
        	
        	for(int conto=0;conto<media.size();conto++)
        	{
        		//Busca que exista mismo elemento de home en media  		
        		
        		//Evalua que conetga la linea el mismo path
        		if(media.get(conto).contains(pathhome))
        		{       		
        			//Extrae primera linea con index conto
        			String cadenausb=media.get(conto);
        			
        			//Extrae path de elemento
        			String pathusb=cadenausb.substring(cadenausb.indexOf(nomcaparpeta1), cadenausb.length()-19);
        			
        			//Fecha y hora de ambos elementos
        			String fechorahome=temp2;
        			String fechorausb=cadenausb.substring(cadenausb.length()-19);
        			
        			//Evalua si el elemento existe
        			if(pathhome.equals(pathusb))
        			{	
        				
        				//Obtiene cual de los elementos es mas nuevo
        				int indice=comparafecha(fechorahome,fechorausb);
        				
            			if(indice==1)
            			{
            				
            				//Es mas nuevo el externo
            				String dife="usb;"+pathusb+"tohome";
            				
            				diferencias.add(dife);
            				
            			}
            			if(indice==2)
            			{
            				//es mas nuevo el interno	
            				String dife="home;"+pathhome+"tousb";
            				diferencias.add(dife);
            				
            			}
        				
        			}      			
        			
        		}else//Entra si no se contienene el elemento en la cadena
        		{	
        			
        			conext++;
        			
        			if(conext==media.size())//No hay elemento en media
        			{    				
        				
        			//Se añade elemento
    				String dife="home;"+pathhome+"tousb";
    				diferencias.add(dife);
        			}
        		}
        	}  	
        	
        }
        	//Añade espacio para separar lista
        	diferencias.add("espacio");
                    
           int supercontador2;
           
        //Contador que recorre el directoio media
        for(supercontador2=0;supercontador2<media.size();supercontador2++)
        {
        	
        	String temp2;
        	String cadenausb,pathusb = null;
        	cadenausb=media.get(supercontador2);
    		int conext=0;
        	//Extrae nombre de elemento
        	pathusb=cadenausb.substring(cadenausb.indexOf(nomcaparpeta1), cadenausb.length()-19);
        	//Obtiene fecha
        	temp2=cadenausb.substring(cadenausb.length()-19);

        	for(int conto=0;conto<home.size();conto++)
        	{
        		//Busca que exista mismo elemento de media en home       		
        		//Evalua que conetga la linea el mismo path
        		if(home.get(conto).contains(pathusb))
        		{
        			//Extrae primera linea con index conto
        			String cadenahome=home.get(conto);
        			
        			//Extrae path de elemento
        			String pathhome=cadenahome.substring(cadenahome.indexOf(nomcaparpeta1), cadenahome.length()-19);
        			
        			//Fecha y hora de ambos elementos
        			String fechorausb=temp2;
        			String fechorahome=cadenahome.substring(cadenahome.length()-19);
        			
        			//Evalua si el elemento existe
        			if(pathusb.equals(pathhome))
        			{
        				
        				//Obtiene cual de los elementos es mas nuevo
        				int indice=comparafecha(fechorausb,fechorahome);
        				
            			if(indice==1)
            			{
            				
            				//Es mas nuevo el home		
            				String dife="home;"+pathhome+"tousb";
            				diferencias.add(dife);
            				
            			}
            			if(indice==2)
            			{
            				//es mas nuevo el usb
            				String dife="usb;"+pathusb+"tohome";
            				diferencias.add(dife);
            				
            			}
        				
        			}      			
        			
        		}else//Entra si no se contienene el elemento el la cadena
        		{			
        				
        			conext++;

        			if(conext==home.size())//No hay elemento en home
        			{
        				
        			//Se añade elemento
    				String dife="usb;"+pathusb+"tohome";
    				
    				diferencias.add(dife);
        			}
        		}
        	}   	
        	
        	
        }
               
        volcado(pathdife,diferencias);
              
        }
    }

	//Formatea fechas y horas y las compara
	public int comparafecha(String fecha1,String fecha2) throws ParseException
	{

		String fechora1=fecha1;
		String fechora2=fecha2;
		
		int anterioridad=0;
		
		DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
		Date d2 = df2.parse(fechora1);
		Date d3 = df2.parse(fechora2);
		 
		if(d2.before(d3))
		{
			 
			anterioridad=1;
			 
		}else
		{
			 
			anterioridad=2;
			 
		}
		 
		if(d2.equals(d3))
		{
			 			 
			anterioridad=3;
			 
		}		
		
		return anterioridad;

	}

	//Vuelca a fichero
	public void volcado(String path, ArrayList<String> fid) throws IOException
	{
		
		fid.remove(2);
		int index=fid.indexOf("espacio");
		fid.remove(index+1);
		fid.remove(index);
		   	
    	File fichdif=new File(path);
		
    	FileWriter fichero = new FileWriter(fichdif);
		PrintWriter pw = new PrintWriter(fichero);
    	
		for(int cont=0;cont<fid.size();cont++)
		{
			
			pw.println(fid.get(cont));
			
		}
		
		pw.close();fichero.close();
		
    }
	
}
