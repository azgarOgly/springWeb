package az.test.spring.persister;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.InvalidConfigDataPropertyException;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;

@Slf4j
@Component
public class FileDataPersister implements DataPersister {

    @Value("${file.storage.path:#{null}}")
    private String fileStoragePath;

    @Override
    public void store(String streamName, String body) {
        log.debug("Stream: {}, Strings: {}", streamName, body);
        if (fileStoragePath == null) {
            fileStoragePath = ".";
        }
        Writer writer = prepareWriter(streamName);
        try {
            writer.append(body);
            if (!body.endsWith("\n")) { // XXX does not work in case of CRLF
                writer.append("\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to write data.", e);
        }
    }

    private Writer prepareWriter(String streamName) {
        File output = prepareOutputFile(streamName);
        Writer result;
        try {
            result = new BufferedWriter(new FileWriter(output, true));
        } catch (Exception e) {
            throw new RuntimeException("Failed to open writer.", e);
        }
        return result;
    }

    private File prepareOutputFile(String streamName) {
        File storageDir = prepareStorageDirectory();
        File outputFile = new File(storageDir, streamName);
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open output file.", e);
        }
        return outputFile;
    }

    private File prepareStorageDirectory() {
        File storageDir = new File(fileStoragePath);
        try {
            if (!storageDir.exists()) {
                storageDir.mkdirs();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open storage directory.", e);
        }
        return storageDir;
    }
}
