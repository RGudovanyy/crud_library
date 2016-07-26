package services;

import java.io.File;

/**
 *
 */
public interface FileService {

    void download (File file);

    void upload (File file);

    void delete (File file);
}
