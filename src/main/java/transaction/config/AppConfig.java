package transaction.config;

import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfig {
    public static final AppConfig APP_CONFIG = new AppConfig();
    private final Properties properties = new Properties();

    private AppConfig() {
        URI uri = null;
        try {
            uri = ClassLoader.getSystemClassLoader().getResource("application.properties").toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try (Reader reader = Files.newBufferedReader(Paths.get(uri))) {
            properties.load(reader);
//            System.out.println(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static AppConfig getConfigInstance() {
        return APP_CONFIG;
    }

    public String getTransactionInputDirectory() {
        return properties.getProperty("transaction.input.dir");
    }

    public String getTransactionProcessedDirectory() {
        return properties.getProperty("transaction.processed.dir");
    }

    public String getTransactionRejectedDirectory() {
        return properties.getProperty("transaction.rejected.dir");
    }

    public String getTransactionOutputDirectory() {
        return properties.getProperty("transaction.output.dir");
    }

    public String getTransactionSeparatorTxt() {
        return properties.getProperty("transaction.separator.txt");
    }
    public String getTransactionSeparatorCSV() {
        return properties.getProperty("transaction.separator.txt");
    }

    public String getTransactionOutputType() {
        return properties.getProperty("transaction.output.type");
    }
}
