import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class parseador {
	
	public void parse() throws IOException
	{
		
		//Path ficheros
		String pathdife="RegistroSyncorem/diff";
		String pruebas="RegistroSyncorem/copiado";
	
		//Creanos lista
		ArrayList<String> pars=new ArrayList<String>();
		
		//Cargamos fichero diff
		File fich=new File(pathdife);		
		FileReader fr=new FileReader(fich);		
		BufferedReader br=new BufferedReader(fr);
		
		String linea;
	
        while((linea=br.readLine())!=null)
        {
        	
        	pars.add(linea);
        	
        }
        
		//Extraigo rutas
        String path1=pars.get(0);
        String path2=pars.get(1);
        
        paths(path1,path2);
        
        //Extraemos nombre comun de darpetas
        String[] chop=path1.split("/");
        String raiz=chop[chop.length-1];
        
        //Borramos elementos inutiles
        pars.remove(1);
        pars.remove(0);
        
        //Quitamos posibles paths bidireccionales
        ArrayList<String> parsmid=quitarepe(pars);
        
        //Estructuramos el fichero para el copiado
        ArrayList<String> estucturado=estructurador(parsmid,raiz,path1,path2);
        
       	//novoparseador(parsmid,raiz,path1,path2);        
       	volcado(pruebas,estucturado);
        
	}
	
	
	public ArrayList<String> quitarepe(ArrayList<String> lista) 
	{

		HashSet<String> hs = new HashSet<String>();
		hs.addAll(lista);
		lista.clear();
		lista.addAll(hs);
		return lista;
	
	}
	
	
	public ArrayList<String> estructurador(ArrayList<String> lista,String raiz,String path1,String path2)
	{
		
		//Creamos lista a copiar
		ArrayList<String> struct=new ArrayList<String>();
		
		//Recorremos los elementos para parsearlos
		for(int i=0;i<lista.size();i++)
		{
			//Extraemos elementos informativos
			String[] array;
			
			array=lista.get(i).split(";");
		
			//Si el elementos a copiar esta en home
			if(array[0].equals("home"))
			{
				String[] arrayconv=array[1].split("/");
				int partulti=arrayconv[arrayconv.length-1].length();		
				int partedelan=raiz.length()+1;	
	
				String total=path1.substring(0, path1.length()-raiz.length()-1)+array[1]+";to;"+path2.substring(0, path2.length())+array[1].substring(partedelan, array[1].length()-partulti);
				
				struct.add(total);				
			}
			
			if(array[0].equals("usb"))
			{				
				String[] arrayconv=array[1].split("/");		
				int partulti=arrayconv[arrayconv.length-1].length();							
				int partedelan=raiz.length()+1;	
							
				String total=path2.substring(0, path2.length()-raiz.length()-1)+array[1]+";to;"+path1.substring(0, path1.length())+array[1].substring(partedelan, array[1].length()-partulti);
				struct.add(total);			
			}
					
		}
		
		Collections.sort(struct);		
		Collections.reverse(struct);
		
		return struct;	
		
	}
	
	public void volcado(String path, ArrayList<String> fid) throws IOException
	{
    	
    	File fichdif=new File(path);		
    	FileWriter fichero = new FileWriter(fichdif);
		PrintWriter pw = new PrintWriter(fichero);
    	
		for(int cont=0;cont<fid.size();cont++)
		{
			
			pw.println(fid.get(cont));
			
		}
		
		pw.close();fichero.close();
		
    }
	
	public void paths(String path1,String path2) throws IOException
	{
			
		FileWriter fichero = new FileWriter("RegistroSyncorem/paths");
		PrintWriter pw = new PrintWriter(fichero);
    				
		pw.println(path1);
		pw.println(path2);
		
		pw.close();
		fichero.close();
	
	}
	
}
