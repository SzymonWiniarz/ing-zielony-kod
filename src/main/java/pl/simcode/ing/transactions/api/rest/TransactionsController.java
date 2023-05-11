package pl.simcode.ing.transactions.api.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.simcode.ing.transactions.ITransactionsReportGenerator;
import pl.simcode.ing.transactions.api.dto.AccountDto;
import pl.simcode.ing.transactions.api.dto.TransactionDto;

@RestController
@RequestMapping("transactions")
public class TransactionsController {

    private final ITransactionsReportGenerator transactionsReportGenerator;

    public TransactionsController(ITransactionsReportGenerator transactionsReportGenerator) {
        this.transactionsReportGenerator = transactionsReportGenerator;
    }

    @PostMapping("/report")
    public AccountDto[] generateReport(@RequestBody TransactionDto[] transactions) {
        return transactionsReportGenerator.generateReport(transactions);
    }

}
