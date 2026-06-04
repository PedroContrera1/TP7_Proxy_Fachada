package ejercicio3;

public class ImageGalery {

    public static void main(String[] args) {

        Image image1 = new ImageFileProxy(
                "src/main/resources/image1.jpeg"
        );

        image1.display(); // Acá carga la imagen desde disco
        image1.display(); // Acá NO la vuelve a cargar
    }
}