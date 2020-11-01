package transaction.manager.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static transaction.config.AppConfig.getConfigInstance;

public interface FileUtils {
    static void copyFileAfterRead(Path filePath, boolean isInError) {
        try {
            String transactionMoveFolder = isInError ? getConfigInstance().getTransactionRejectedDirectory() :
                    getConfigInstance().getTransactionProcessedDirectory();
            Path copyFolderPath = Paths.get(transactionMoveFolder);
            if (!Files.exists(copyFolderPath)) {
                Files.createDirectory(copyFolderPath);
            }
            Path copyFilepath = copyFolderPath.resolve(filePath.getFileName());
            Files.copy(filePath, copyFilepath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
