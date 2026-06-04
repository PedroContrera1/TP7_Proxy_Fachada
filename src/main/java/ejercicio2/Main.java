package ejercicio2;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Usuario admin = new Usuario(
                "Juan",
                List.of(Permiso.ADMIN)
        );

        Usuario intermedio = new Usuario(
                "Ana",
                List.of(Permiso.INTERMEDIO)
        );

        Usuario basico = new Usuario(
                "Pedro",
                List.of(Permiso.BASICO)
        );

        String ruta = "C:/archivos";

        try {
            Archivo archivoImportante =
                    new FileAccessProxy(admin, ruta, "informe.txt");

            System.out.println(archivoImportante.readFile());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Archivo archivoMedio =
                    new FileAccessProxy(intermedio, ruta, "memo.txt");

            System.out.println(archivoMedio.readFile());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Archivo archivoComun =
                    new FileAccessProxy(basico, ruta, "documento.txt");

            System.out.println(archivoComun.readFile());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Archivo archivoSinPermiso =
                    new FileAccessProxy(basico, ruta, "importante.txt");

            System.out.println(archivoSinPermiso.readFile());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
