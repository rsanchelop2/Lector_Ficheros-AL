package psep.ut1.lector;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class LectorPadre {
    public static void main(String[] args) throws IOException, InterruptedException {
        String ruta = args.length == 0 ? "datos/datos de prueba.txt" : args[0];
        String java = ejecutableJava();
        String classpath = System.getProperty("java.class.path");
        String claseHijo = LectorHijo.class.getName();

        // TODO 1. Crea un ProcessBuilder. Pasa el ejecutable, "-cp", el classpath,
        // la clase del hijo y la ruta como elementos separados del comando.
        // Haz que el hijo herede stderr y arráncalo. Muestra su PID.
        ProcessBuilder lanzador = new ProcessBuilder(java, "-cp", classpath, claseHijo, ruta);
        lanzador.redirectError(ProcessBuilder.Redirect.INHERIT);
        Process hijo = lanzador.start();

        System.out.println("PID del hijo: " + hijo.pid());

        // TODO 2. Lee todas las líneas del stdout del hijo con un BufferedReader
        // conectado a hijo.getInputStream(). Usa UTF-8 y guarda el texto.
        Scanner consola = new Scanner(System.in);

        try (BufferedReader entradaHijo = new BufferedReader(
                new InputStreamReader(hijo.getInputStream(), StandardCharsets.UTF_8))) {
            if (consola.hasNext()) {

            }
        }



        // TODO 3. Llama a waitFor() para recoger el código de salida del hijo.
        // Si devuelve 0, muestra el texto leído. Si devuelve otro código,
        // informa de que ha fallado la lectura. Muestra el código en ambos casos.
        System.out.println("Codigo salida hijo: " + hijo.waitFor());
        if (hijo.waitFor() == 0){
            System.out.printf("Contenido hijo" + hijo);
        }

    }

    private static String ejecutableJava() {
        boolean windows = System.getProperty("os.name").startsWith("Windows");
        return Path.of(System.getProperty("java.home"), "bin", windows ? "java.exe" : "java").toString();
    }
}
