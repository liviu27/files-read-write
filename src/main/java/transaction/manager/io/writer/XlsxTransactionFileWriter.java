package transaction.manager.io.writer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import transaction.manager.model.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XlsxTransactionFileWriter extends TransactionAbstractFileWriter{


    @Override
    protected boolean writeTransactionsInFile(List<Transaction> transactions, Path outputFile) {
        Workbook workbook = new XSSFWorkbook();
        Sheet transactionsSheet = workbook.createSheet("Transactions");
        for (int i = 0; i < transactions.size(); i++) {
            Row row = transactionsSheet.createRow(i);
            Transaction transaction = transactions.get(i);
            createRowWithTransactionValues(row, transaction);
        }
        try {
            workbook.write(Files.newOutputStream(outputFile));
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    private void createRowWithTransactionValues(Row row, Transaction transaction) {
        long id = transaction.getId();
        Cell cell = row.createCell(0);
        cell.setCellValue(id);
        String fromIban = transaction.getFromIban();
        Cell cell1 = row.createCell(1);
        cell1.setCellValue(fromIban);
        String toIban = transaction.getToIban();
        Cell cell2 = row.createCell(2);
        cell2.setCellValue(toIban);
        Double amount = transaction.getAmount();
        Cell cell3 = row.createCell(3);
        cell3.setCellValue(amount);
        String fromBank = transaction.getFromBank();
        Cell cell4 = row.createCell(4);
        cell4.setCellValue(fromBank);
        String toBank = transaction.getToBank();
        Cell cell5 = row.createCell(5);
        cell5.setCellValue(toBank);
    }
}
