import java.io.File;

public class DocumentCompression {

    public static void compressDocument(File inputFile) {
        File compressedDocumentFile = new File(inputFile.getParent(), "compressed_document.zip");
        ZipCompression.compressToZip(compressedDocumentFile, inputFile);
    }
}
