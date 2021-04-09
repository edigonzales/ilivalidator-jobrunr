package ch.so.agi.ilivalidator;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();

    void store(MultipartFile file, String filename) throws IOException;
    
    Path load(String filename);

    void delete(String filename);
}
