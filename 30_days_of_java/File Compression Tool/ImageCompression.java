import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCompression {

    public static void compressImage(File inputFile) {
        try {
            BufferedImage image = ImageIO.read(inputFile);
            File compressedImageFile = new File(inputFile.getParent(), "compressed_image.zip");
            ZipCompression.compressToZip(compressedImageFile, inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
