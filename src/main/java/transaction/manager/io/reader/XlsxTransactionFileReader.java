package transaction.manager.io.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import transaction.manager.model.Transaction;
import transaction.manager.model.TransactionReadResults;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static transaction.config.AppConfig.getConfigInstance;
import static transaction.manager.util.TransactionMapper.mapTransactionStringToObject;

public class XlsxTransactionFileReader extends TransactionAbstractFileReader {

    protected XlsxTransactionFileReader(String fileName) {
        super(fileName);
    }

    @Override
    protected TransactionReadResults readTransactions(Path filePath) {
        System.out.println("I am reading from .xlsx files");
        TransactionReadResults transactionReadResults = new TransactionReadResults();
        try (Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath.toString())))) {
            List<String> txLines = new ArrayList<>();
            Sheet sheetAt = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheetAt.iterator();
            while (iterator.hasNext()) {
                String txLine = "";
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            txLine += cell.getStringCellValue()+ "--";
                            break;
                        case NUMERIC, BLANK:
                            txLine += cell.getNumericCellValue() + "--";
                            break;
                        default:
                            System.out.println("Ceva nu e bine!");
                    }
                }
                txLines.add(txLine.substring(0, txLine.length() - 2));
            }
            for (String tx : txLines) {
                readTransactions(transactionReadResults, tx);
            }
            return transactionReadResults;
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void readTransactions(TransactionReadResults transactionReadResults, String txLine) {
        Transaction transaction = mapTransactionStringToObject(txLine, getConfigInstance().getTransactionSeparatorTxt());
        transactionReadResults.getTransactions().add(transaction);
    }
}
