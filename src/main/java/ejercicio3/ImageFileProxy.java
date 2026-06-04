package ejercicio3;

public class ImageFileProxy implements Image {

    private String path;
    private ImageFile imageFile;

    public ImageFileProxy(String path) {
        this.path = path;
    }

    @Override
    public void display() {
        if (imageFile == null) {
            imageFile = new ImageFile(path);
        }

        imageFile.display();
    }
}