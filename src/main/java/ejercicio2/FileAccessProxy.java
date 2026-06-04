package ejercicio2;

import java.io.IOException;

public class FileAccessProxy implements Archivo {

    private Usuario usuario;
    private FileAccess archivoReal;
    private String nombreArchivo;

    public FileAccessProxy(Usuario usuario, String ruta, String nombreArchivo) {
        this.usuario = usuario;
        this.nombreArchivo = nombreArchivo;
        this.archivoReal = new FileAccess(ruta, nombreArchivo);
    }

    @Override
    public String readFile() throws IOException {
        validarPermiso();
        return archivoReal.readFile();
    }

    private void validarPermiso() {
        if (nombreArchivo.startsWith("i")) {
            if (!usuario.poseePermiso(Permiso.ADMIN)) {
                throw new RuntimeException(
                        "Acceso denegado. Solo ADMIN puede leer archivos importantes."
                );
            }
        }

        if (nombreArchivo.startsWith("m")) {
            boolean puedeLeer =
                    usuario.poseePermiso(Permiso.ADMIN)
                            || usuario.poseePermiso(Permiso.INTERMEDIO);

            if (!puedeLeer) {
                throw new RuntimeException(
                        "Acceso denegado. Solo ADMIN o INTERMEDIO pueden leer este archivo."
                );
            }
        }
    }
}