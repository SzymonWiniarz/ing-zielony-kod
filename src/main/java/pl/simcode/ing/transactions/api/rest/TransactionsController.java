package pl.simcode.ing.transactions.api.rest;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.simcode.ing.transactions.ITransactionsReportGenerator;
import pl.simcode.ing.transactions.api.dto.AccountDto;
import pl.simcode.ing.transactions.api.dto.TransactionDto;

import java.util.List;

@RestController
@RequestMapping("transactions")
@Validated
public class TransactionsController {

    private final ITransactionsReportGenerator transactionsReportGenerator;

    public TransactionsController(ITransactionsReportGenerator transactionsReportGenerator) {
        this.transactionsReportGenerator = transactionsReportGenerator;
    }

    @PostMapping("/report")
    public List<AccountDto> generateReport(@Valid @RequestBody List<TransactionDto> transactions) {
        return transactionsReportGenerator.generateReport(transactions);
    }

}
