package transaction.manager.util;

import transaction.manager.model.Transaction;

import java.math.BigDecimal;

public class TransactionMapper {
    public static Transaction mapTransactionStringToObject(String txLine, String separator) {
        String[] elements = txLine.split(separator);
        return Transaction.newBuild()
                .id(new BigDecimal(elements[0]).longValue())
                .fromIban(elements[1])
                .toIban(elements[2])
                .amount(Double.parseDouble(elements[3].replace(",", ".")))
                .fromBank(elements[4])
                .toBank(elements[5])
                .build();

    }
}
