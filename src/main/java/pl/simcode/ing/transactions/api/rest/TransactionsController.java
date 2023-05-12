package pl.simcode.ing.transactions.api.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.simcode.ing.transactions.ITransactionsReportGeneratorFactory;
import pl.simcode.ing.transactions.api.dto.AccountDto;

import java.io.IOException;

@RestController
@RequestMapping("transactions")
public class TransactionsController {

    private final TransactionsJsonParser transactionsJsonParser;
    private final ITransactionsReportGeneratorFactory transactionsReportGeneratorFactory;

    public TransactionsController(TransactionsJsonParser transactionsJsonParser, ITransactionsReportGeneratorFactory transactionsReportGeneratorFactory) {
        this.transactionsJsonParser = transactionsJsonParser;
        this.transactionsReportGeneratorFactory = transactionsReportGeneratorFactory;
    }

    @PostMapping("/report")
    public AccountDto[] generateReport(HttpServletRequest request) throws IOException {
        var transactionsReportGenerator = transactionsReportGeneratorFactory.create();

        var requestInputStream = request.getInputStream();
        transactionsJsonParser.readTransactionsFromJson(requestInputStream, transactionsReportGenerator::processTransactionsBatch);

        return transactionsReportGenerator.generateReport();
    }

}
