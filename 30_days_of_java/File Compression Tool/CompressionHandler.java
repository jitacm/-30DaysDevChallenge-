import java.io.File;

public class CompressionHandler {

    public static void compressFile(File inputFile) {
        String fileName = inputFile.getName();
        if (fileName.endsWith(".txt")) {
            HuffmanCompression.compressFile(inputFile);
        } else if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".bmp")) {
            ImageCompression.compressImage(inputFile);
        } else if (fileName.endsWith(".wav")) {
            AudioCompression.compressWavFile(inputFile);
        } else if (fileName.endsWith(".mp3")) {
            AudioCompression.copyMp3File(inputFile);
        } else if (fileName.endsWith(".pdf") || fileName.endsWith(".docx")) {
            DocumentCompression.compressDocument(inputFile);
        } else {
            throw new UnsupportedOperationException("Unsupported file type");
        }
    }

    public static void decompressFile(File inputFile) {
        String fileName = inputFile.getName();
        if (fileName.endsWith(".huff")) {
            HuffmanCompression.compressFile(inputFile);
        } else if (fileName.endsWith(".zip")) {
            ZipCompression.decompressZipFile(inputFile);
        } else {
            throw new UnsupportedOperationException("Unsupported compressed file type");
        }
    }
}
