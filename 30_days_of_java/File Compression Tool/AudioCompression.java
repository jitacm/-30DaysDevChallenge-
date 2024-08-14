import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AudioCompression {

    public static void compressWavFile(File inputFile) {
        File compressedAudioFile = new File(inputFile.getParent(), "compressed_audio.zip");
        ZipCompression.compressToZip(compressedAudioFile, inputFile);
    }

    public static void copyMp3File(File inputFile) {
        try {
            File compressedMp3File = new File(inputFile.getParent(), "compressed_mp3.zip");
            Files.copy(inputFile.toPath(), compressedMp3File.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
