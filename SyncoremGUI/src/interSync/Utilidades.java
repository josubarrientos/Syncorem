/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interSync;

import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.util.List;
import java.io.*;
import java.util.ArrayList;


/**
 *
 * @author alumno
 */
public class Utilidades {
    private static Asociacion intermedio=new Asociacion();
    
    public static void SeleccionDirectorios(String dev)
    {
        String IdDev=dev.substring(23, 31);
        JOptionPane.showMessageDialog(null, "Elegir un directorio para Sincronizar:");
          String DirName = FileChooser.pickADirectory();
          FileChooser.setMediaPath(DirName);
          try
          {

          JOptionPane.showMessageDialog(null, "Elegir el directorio con el que deseas que se mantenga Sincronizado:");
          String DirNameD = FileChooser.pickADirectory();
          FileChooser.setMediaPath(DirNameD);
          
          System.out.println(dev);
          System.out.println(IdDev);
          AñadirPath(DirName,DirNameD,IdDev);
          GuardarFichero();
          }
          catch(Exception e)
          {
             e.printStackTrace(); 
          }
    }

    public static List Busqueda(List<Asociacion> a,String ele)
    {
        List<Asociacion> ListaQuery=null;
        Asociacion intermedia;
        for(int i=0;i<a.size();i++)
        {
            if (a.get(i).getDirPc().equals(ele)==true)
            {
                intermedia=a.get(i);
                ListaQuery.add(intermedia);
            }
        }
        return ListaQuery;
    }

    private static boolean AñadirPath(String Origen,String destino,String IdDev)
    {
        
            intermedio.setDirPc(Origen);
            intermedio.setDirExt(destino);
            intermedio.setId(IdDev);

        
            
        
        return true;
    }

    private static void GuardarFichero() throws IOException
    {
    	
    	String pathguardado="RegistroSyncorem/Vinculados";
    	
        FileWriter fichero = new FileWriter(pathguardado,true);
          PrintWriter pw = new PrintWriter(fichero);

                pw.print(intermedio.getDirPc());
                    pw.print(";"+intermedio.getDirExt()+";"+intermedio.getId());
                pw.println();


          pw.close();
          fichero.close();
        
    }
}
