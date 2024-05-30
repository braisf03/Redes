package es.udc.redes.tutorial.info;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Info {
    /*
     Tamaño, fecha de última modificación, nombre, extensión, tipo
     de fichero (image, text, directory, unknown), ruta absoluta.

     java es.udc.redes.tutorial.info.Info <ruta relativa>
    */
    public static void main(String[] args) throws IOException {
        // Se crea un array que vaya a almacenar el formato del archivo.

        File archivo = new File(args[0]);
        if (archivo.exists()) {

            System.out.println("Nombre de Archivo: " + archivo.getName());
            System.out.println("Absolute path: " + archivo.getAbsolutePath());
            System.out.println("Tamaño del archivo: " + archivo.length());

            String[] extension =args[0].split("\\.",0);
            System.out.println("Extension: " + extension[1]);

            long ms = archivo.lastModified();
            Date d = new Date(ms);
            Calendar c = new GregorianCalendar();
            c.setTime(d);
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString((c.get(Calendar.MONTH) + 1));
            String year = Integer.toString(c.get(Calendar.YEAR));
            String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
            String minuto = Integer.toString(c.get(Calendar.MINUTE));
            String segundo = Integer.toString(c.get(Calendar.SECOND));

            System.out.println("Ultima fecha de modificación: "+ dia+"/"+mes+"/"+year+"  "+hora+":"+minuto+":"+segundo);

            System.out.println("Tipo de archivo: "+Files.probeContentType(Path.of(archivo.getAbsolutePath())));
        
        } else {
            throw new FileNotFoundException("Archivo no encontrado");
        }
    }
}
    

