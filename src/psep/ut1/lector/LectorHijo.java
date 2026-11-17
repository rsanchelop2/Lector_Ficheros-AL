package psep.ut1.lector;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class LectorHijo {
    private static final int ERROR_LECTURA = 2;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Indica la ruta de un fichero.");
            System.exit(ERROR_LECTURA);
            return;
        }

        try {
            String contenido = Files.readString(Path.of(args[0]), StandardCharsets.UTF_8);
            PrintWriter salida = new PrintWriter(System.out, false, StandardCharsets.UTF_8);
            salida.print(contenido);
            salida.flush();
        } catch (IOException | InvalidPathException e) {
            System.err.println("No se pudo leer el fichero: " + args[0]);
            System.exit(ERROR_LECTURA);
        }
    }
}
