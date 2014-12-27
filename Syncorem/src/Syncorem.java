import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
public class Syncorem {

	public static void main(String[]arg) throws IOException, InterruptedException, ParseException
	{
		//Listas de elementos de trabajo
		ArrayList<String> listavinc=new ArrayList<String>();
		ArrayList<String> listaids=new ArrayList<String>();
		ArrayList<String> conectados=new ArrayList<String>();
		
		//Tipo de directorio a analizar
		String dirType1="internal";
		String dirType2="external";
		
		File fichero=new File("RegistroSyncorem/Vinculados");
		
		//Recupera info de fichero de vinculados
		do{
			
			FileReader fr=new FileReader(fichero);
			
			BufferedReader br=new BufferedReader(fr);
			
			String linea;
	        while((linea=br.readLine())!=null)
	        {
	        	
	        	listavinc.add(linea); 
	        	
	        }
	        
	        br.close();
	        fr.close();			 
	        
	        //procesa vilculados en busca de coenxiones entrantes
	        for(int conta=0;conta<listavinc.size();conta++)
	        {
	        	
	        	//Separa componentes
	        	String tem=listavinc.get(conta);
	        	
	        	String[] chop=tem.split(";");
	        	
	        	listaids.add(chop[2]);
    	
	        }
	            
	        //Llama a buscausb
	        BuscaUSB dispositivo=new BuscaUSB();
	        //Recupera lista de coenctados
	        
	        conectados=dispositivo.conexiones(listaids);	        
			
			AnalizaDirectorio dir=new AnalizaDirectorio();
			
	        int conta=0,conta2=0;
	        
	        String conect="No connected devices";
	        if(conectados.size()>0)
	        {
	        	
	        	 conect="Connected devices";
	        	
	        }
	        
	        System.out.println("Dispositivos conectados: "+conect);
	        
	        //Ejecuta el proceso de sincronizacion en caso de haber vinculaciones
			for( conta=0;conta<conectados.size();conta++)
			{

				String ids=conectados.get(conta);
				
				for( conta2=0;conta2<listavinc.size();conta2++)
				{
					
					String cadena=listavinc.get(conta2);
					
					if(cadena.contains(ids))
					{
						String[] chop=cadena.split(";");
			        	
			        	String path1=chop[0];
			        	String path2=chop[1];
			        	
			        	//Analiza directorio 		        	
						dir.Process(new File(path1),"1",dirType1);
						dir.Process(new File(path2),"1",dirType2);		        	
						
						comparador comp=new comparador();
						
						comp.compara(path1,path2);
						
						parseador parsea=new parseador();
						parsea.parse();
						
						supercopy copia=new supercopy();
						
						copia.carga();
										
					}
					
				}	
				
			}
			
			System.out.println();
			System.out.println("-----------------------------------");
			System.out.println("------SYNCOREMUSB SYNCHRONIZING----");
			System.out.println("-----------------------------------");
			System.out.println();
			
			Thread.sleep(15000);
		}while(true);
	
      } 
		
	}
	

