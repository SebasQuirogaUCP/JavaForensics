package proyectofinal2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Forensics extends Thread {
protected String hostN="";
protected String myDoc="";
public String myDoc2="";

    public void Forensic(){
    }
    
    //OVERLOADING
    public void run(){
    
        FileInputStream fin=null;
        try {
            System.out.println("OVERLOADING RUN METHOD");
            System.out.println("BUSCANDO DIRECCION DE MIS DOCUMENTOS EN MAQUINA");
            try {
                Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
                p.waitFor();
                //System.out.println("PROCESO "+p);
                
                InputStream in = p.getInputStream();
                byte[] b = new byte[in.available()];
                in.read(b);
                in.close();
                myDoc = new String(b);
                
                //System.out.println(myDoc);
                myDoc= myDoc.split("\\s\\s\\s")[4];
                
            } catch(Throwable t) {
                t.printStackTrace();
            }
            System.out.println("Listando Documentos: --> "+myDoc);
            //EXTRAER LA CADENA SIN ESPACIOS
            String h=myDoc.trim();
            
//StringBuilder builder = new StringBuilder(myDoc);
//myDoc2=builder.replace(3, 3, "\\").replace(10, 10,"\\").replace(18, 18, "\\").toString();
//System.out.println(myDoc);


File carpeta=new File(h);
ArrayList<String> listaArchivos=new ArrayList<String>();
for(File archivo:carpeta.listFiles()){
    if(archivo.isFile())
        listaArchivos.add(archivo.getName());
}
//IMPRIMIR EL ARRAYLIST
for (int i = 0; i <listaArchivos.size(); i++) {
    System.out.println(listaArchivos.get(i).toString());
}

//GUARDAR ARRAYLIST EN UN ARCHIVO DENTRO DE LA CARPETA DEL PROYECTO

    FileOutputStream fout=new FileOutputStream("salida.txt");
    ObjectOutputStream out= new ObjectOutputStream(fout);
    out.writeObject(listaArchivos);
    out.close();

    //ABRIR EL ARCHIVO Y GUARDAR LISTA DE ARCHIVOS EN ARRAYLIST listaArchivos
    fin = new FileInputStream("salida.txt");
    ObjectInputStream ois = new ObjectInputStream(fin);
    listaArchivos= (ArrayList)ois.readObject();
        
            
    
            
    System.out.println("THREAD DONE!\n");        
    System.out.println("**************************************************\n");
    System.out.println("OBTENIENDO HOSTNAME...\n");
        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostN = addr.getHostName();
            System.out.println("HOSTNAME --> "+hostN);
        }
        catch (UnknownHostException ex)
        {
            System.out.println("Hostname can not be resolved :( ");
        }
        this.stop();
            
        } catch(FileNotFoundException ex) {
            Logger.getLogger(Forensics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(Forensics.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Forensics.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            fin.close();
        } catch (IOException ex) {
            Logger.getLogger(Forensics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

}
    

