package coding_test;

import org.junit.Test;

import com.smallworld.TransactionDataFetcher;

public class UnitTest {

	@Test
	public void checkTotal() {
		TransactionDataFetcher ts = new TransactionDataFetcher();
		ts.getTotalTransactionAmount();

	}

	@Test
	public void checkAll() {

		TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();
		transactionDataFetcher.getTotalTransactionAmount();
		transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby");
		transactionDataFetcher.getMaxTransactionAmount();
		transactionDataFetcher.countUniqueClients();
		transactionDataFetcher.hasOpenComplianceIssues("Tom Shelby");
		transactionDataFetcher.getTransactionsByBeneficiaryName();

		transactionDataFetcher.getUnsolvedIssueIds();
		transactionDataFetcher.getAllSolvedIssueMessages();
		transactionDataFetcher.getTop3TransactionsByAmount();
		transactionDataFetcher.getTopSender();

	}
}
