package ejercicio3;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ImageFile implements Image {

    private BufferedImage image;

    public ImageFile(String path) {
        this.image = load(path);
    }

    @Override
    public void display() {
        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        JOptionPane.showMessageDialog(null, label);
    }

    private BufferedImage load(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}