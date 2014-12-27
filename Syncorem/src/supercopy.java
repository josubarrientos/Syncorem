import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;


public class supercopy {

	ArrayList<String> listado=new ArrayList<String>();
	String pathcopy="RegistroSyncorem/copiado";
	//Scripts copiado
	String cpfile="sudo RegistroSyncorem/copiafile.sh";
	String cpdir="sudo RegistroSyncorem/copiadir.sh";
	
	public void carga() throws IOException, InterruptedException
	{

		//Cargamos fichero de diferencias
		File fich=new File(pathcopy);				
		FileReader fr=new FileReader(fich);
		BufferedReader br=new BufferedReader(fr);	
		String linea;
			
		while((linea=br.readLine())!=null)
		{
		        	
		      listado.add(linea);
		        	
		 }

		 dirofich(listado);
		
	}
	
	//Evalua si es directorio o fichero y copia
	public void dirofich(ArrayList<String> listado) throws IOException, InterruptedException
	{

		String type;
		
		for(int i=0;i<listado.size();i++)
		{
			
			String[] path;
			
			path=listado.get(i).split(";");
			
			File fRuta = new File(path[0]);
			
			if (fRuta.isDirectory())
			{

				type="dir";
					
				copiador(path[0],path[2],type);			
				
			}else
				{
					
					type="file";
					
					copiador(path[0],path[2],type);
					
				}
		}		
				
	}
	
	//Copia los elementos
	public void copiador(String origen,String destino,String type) throws IOException, InterruptedException
	{
		
		if(type.equals("dir"))
		{	
		
			String[] cade=origen.split("/");
			
			String dir=cade[cade.length-1];
			
	        destino=destino+dir;
			
			File folder = new File(destino);
			
			folder.mkdir();
			
		}else
		{
			
			copiafile(origen,destino);
					
		}

	}
	
	//Metodo de copiado de ficheros
	public void copiafile(String path1,String path2) throws IOException
	{
		
		File sourceFile = new File(path1);
        String name = sourceFile.getName();
        File targetFile = new File(path2+name);
        FileUtils.copyFile(sourceFile, targetFile);

    }

}
	

	

