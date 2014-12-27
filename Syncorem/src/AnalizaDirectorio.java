import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class AnalizaDirectorio {

	int spc_count=-1;
 
	ArrayList<String> listado=new ArrayList<String>();
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public void Process(File aFile,String Ref, String tipodir) throws IOException {

		String dirType=tipodir;
		String pathint="RegistroSyncorem/ListadoINT";
		String pathext="RegistroSyncorem/ListadoEXT";
		
		spc_count++;
	    String k="1";
	    int t=0;
	    String r;
	    
	    //Evalua si el elemento es un fichero
	    if(aFile.isFile())
	    {
	    	
	     listado.add(Ref+"[FILE]"+";"+ aFile.getName()+";"+aFile.getAbsolutePath()+";"+sdf.format(aFile.lastModified()));

	      t=Integer.parseInt(k);
  		  t++;
  		  k=Integer.toString(t);
  		  
	    }//Evalua si el elemento es un directorio
	    else if (aFile.isDirectory()) {
		      
	      listado.add(Ref+"[DIR]"+";"+ aFile.getName()+";"+aFile.getAbsolutePath()+";"+sdf.format(aFile.lastModified()));

	      t=Integer.parseInt(k);
  		  t++;
  		  k=Integer.toString(t);

	      File[] listOfFiles = aFile.listFiles();
	      Arrays.sort(listOfFiles);
	      if(listOfFiles!=null) {
	        for (int i = 0; i < listOfFiles.length; i++)
	        {
	  		  r=Integer.toString(i+1);
	          Process(listOfFiles[i],Ref+r, dirType);
	        }
	      } else {
	        System.out.println(" [ACCESS DENIED]");
	      }

	    }
	    spc_count--;
	    
	    if(spc_count==-1)
	    {

	    	File fich;
	    	
	    	if(dirType.equals("internal"))
	    	{
	    	
	    		fich=new File(pathint);
	    	}else
	    	{
	    		fich=new File(pathext);
	    		
	    	}
	    	
			volcado(fich);
				
	    }
	    
	  }
	
	public void volcado(File fich) throws IOException
	{
    		
    	FileWriter fichero = new FileWriter(fich);
		PrintWriter pw = new PrintWriter(fichero);
    	
		for(int cont=0;cont<listado.size();cont++)
		{
			
			pw.println(listado.get(cont));
			
		}
		
		pw.close();
		fichero.close();
		listado.clear();
		
    }
		
}
	
	

	
