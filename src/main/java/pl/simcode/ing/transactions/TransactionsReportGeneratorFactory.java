package pl.simcode.ing.transactions;

class TransactionsReportGeneratorFactory implements ITransactionsReportGeneratorFactory {


    private final AccountsComparator accountsComparator;

    TransactionsReportGeneratorFactory(AccountsComparator accountsComparator) {
        this.accountsComparator = accountsComparator;
    }

    @Override
    public ITransactionsReportGenerator create() {
        return new TransactionsReportGenerator(accountsComparator);
    }

}
