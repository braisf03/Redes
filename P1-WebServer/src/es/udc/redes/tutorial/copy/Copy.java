package es.udc.redes.tutorial.copy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Copy {
    // java es.udc.redes.tutorial.copy.Copy <fichero origen> <fichero destino>
    public static void main(String[] args) throws IOException {
        // Se comprueba que la cadena tenga unas características.
        if (args != null && args.length == 2){
            // Se crea un array que vaya a almacenar el formato del archivo.
            String[] formato;
            formato = args[0].split("\\.");

            // Se comprueba que el array tenga 2 elementos, el nombre y su extensión.
            if(formato.length != 2){
                throw new IOException("El archivo no tiene una extensión.");
            }

            // Se construye el nuevo path donde se va a copiar el archivo.
            Path original = Paths.get(args[0]);
            Path nuevo = Paths.get(args[1] + "." + formato[1]);

            // Por último se copia el archivo, si ya existe se reemplaza.
            Files.copy(original, nuevo, StandardCopyOption.REPLACE_EXISTING);

        }else{
            // Si la cadena no cumple los requisitos se lanza un IOException.
            throw new IOException("<fichero origen> <fichero destino>");
        }
    }
}
