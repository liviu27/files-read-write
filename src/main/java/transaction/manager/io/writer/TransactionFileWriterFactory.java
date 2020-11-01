package transaction.manager.io.writer;

import transaction.manager.io.reader.FileType;

public interface TransactionFileWriterFactory {
    static TransactionFileWriter createTransactionFileWriter(String fileName) {
        FileType fileType = getFileType(fileName);
        switch (fileType) {
            case CSV:
                return new CsvTransactionFileWriter();
            case XLSX:
                return new XlsxTransactionFileWriter();
            default:
                throw new IllegalArgumentException("Nu avem acest tip de Writer");
        }
    }

    static FileType getFileType(String fileType) {
        return FileType.valueOf(fileType.toUpperCase());
    }
}
