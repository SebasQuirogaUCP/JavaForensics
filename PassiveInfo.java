package proyectofinal2;

import java.io.File;

public class PassiveInfo extends Forensics {
    
    public void PassiveInfo(){
    }
    
    public File getListedFiles(){
        
        System.out.println("GETTING LISTED FILES: ");
        //File f = new File(super.myDoc);
        File f = new File(myDoc2);
        
        System.out.println(myDoc2);

        File[] ficheros = f.listFiles();
        
        try{
            for (int i = 0; i < f.length(); i++) {
                System.out.println(ficheros[i]);
            }

        }
        catch(ArrayIndexOutOfBoundsException Error){
                System.out.println(Error);
        }

        
        
    return f;
    }
    
}
