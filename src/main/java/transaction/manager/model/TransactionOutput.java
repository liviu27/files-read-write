package transaction.manager.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionOutput {

    private long id;
    private double amount;
    private Bank from;
    private Bank to;

}
