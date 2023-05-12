package pl.simcode.ing.transactions.api.rest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import pl.simcode.ing.transactions.api.dto.TransactionDto;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

class TransactionsJsonParser {

    private final JsonFactory jsonFactory;
    private final int transactionsBatchSize;

    TransactionsJsonParser(JsonFactory jsonFactory, int transactionsBatchSize) {
        this.jsonFactory = jsonFactory;
        this.transactionsBatchSize = transactionsBatchSize;
    }

    void readTransactionsFromJson(InputStream inputStream, Consumer<List<TransactionDto>> batchCallback) {
        try (var jsonParser = jsonFactory.createParser(inputStream)) {
            readTransactionsArray(jsonParser, batchCallback);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void readTransactionsArray(JsonParser jsonParser, Consumer<List<TransactionDto>> batchCallback) throws IOException {
        if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
            throw new IllegalArgumentException("Expecting array of transactions but %s encountered".formatted(jsonParser.getCurrentToken()));
        }

        List<TransactionDto> transactionsBatch = new LinkedList<>();

        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            var transactionDto = readTransaction(jsonParser);
            addTransactionToBatch(batchCallback, transactionsBatch, transactionDto);
        }

        batchCallback.accept(transactionsBatch);
    }

    private TransactionDto readTransaction(JsonParser jsonParser) throws IOException {
        if (jsonParser.currentToken() != JsonToken.START_OBJECT) {
            throw new IllegalArgumentException("Expecting transaction object but %s encountered".formatted(jsonParser.getCurrentToken()));
        }

        String debitAccount = null;
        String creditAccount = null;
        BigDecimal amount = null;

        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            var fieldName = jsonParser.getCurrentName();

            if ("debitAccount".equals(fieldName)) {
                jsonParser.nextToken();
                debitAccount = jsonParser.getText();
            } else if ("creditAccount".equals(fieldName)) {
                jsonParser.nextToken();
                creditAccount = jsonParser.getText();
            } else if ("amount".equals(fieldName)) {
                jsonParser.nextToken();
                amount = new BigDecimal(jsonParser.getText());
            }
        }

        return new TransactionDto(debitAccount, creditAccount, amount);
    }

    private void addTransactionToBatch(Consumer<List<TransactionDto>> batchCallback, List<TransactionDto> transactionsBatch, TransactionDto transactionDto) {
        transactionsBatch.add(transactionDto);

        if (transactionsBatch.size() == transactionsBatchSize) {
            batchCallback.accept(transactionsBatch);
            transactionsBatch.clear();
        }
    }

}
