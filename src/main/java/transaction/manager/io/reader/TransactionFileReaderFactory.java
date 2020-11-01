package transaction.manager.io.reader;

public interface TransactionFileReaderFactory {

    static TransactionFileReader createTransactionFileReader(String fileName) {
        FileType fileType = getFileType(fileName);
        switch (fileType) {
            case TXT:
                return new TxtTransactionFileReader(fileName);
            case CSV:
                return new CsvTransactionFileReader(fileName);
            case XLSX:
                return new XlsxTransactionFileReader(fileName);
            case JSON:
                return new JsonTransactionFileReader(fileName);
            default:
                throw new IllegalArgumentException("Unknown file type!");
        }
    }

    static FileType getFileType(String file) {
        String fileExtension = file.split("\\.")[1];
        return FileType.valueOf(fileExtension.toUpperCase());
    }
}
