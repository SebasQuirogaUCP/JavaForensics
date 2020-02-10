package proyectofinal2;

import java.io.File;

public class ProyectoFinal2 {
    public static void main(String[] args) {
        
        Forensics f = new Forensics();
        System.out.println("INICIANDO HILO --> RECOLECCION DE INFORMACION DESDE MAQUINA");
        f.start();
        PassiveInfo pi = new PassiveInfo();
        pi.getListedFiles();
    
    }
    
}
