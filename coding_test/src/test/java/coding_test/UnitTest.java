package coding_test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.bean.Transaction;

import org.junit.Assert;

public class UnitTest {
	static TransactionDataFetcher  transactionDataFetcher;
	@BeforeClass
	public static void initialize(){
		 transactionDataFetcher = new TransactionDataFetcher();
		
	}
	@Test
	public void getTotalTransactionAmount() {
		double amount= transactionDataFetcher.getTotalTransactionAmount();

		Assert.assertEquals(2889.17, amount,0);
	}

	/**
	 * Returns the sum of the amounts of all transactions sent by the specified
	 * client
	 */
	@Test
	public void getTotalTransactionAmountSentBy() {
		double amount = transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby");
		
		Assert.assertEquals(678.06, amount,0);
	}

	/**
	 * Returns the highest transaction amount
	 */
	@Test
	public void getMaxTransactionAmount() {
		transactionDataFetcher.getMaxTransactionAmount();
	}

	/**
	 * Counts the number of unique clients that sent or received a transaction
	 */
	@Test
	public void countUniqueClients() {
		transactionDataFetcher.countUniqueClients();
	}

	/**
	 * Returns whether a client (sender or beneficiary) has at least one
	 * transaction with a compliance issue that has not been solved
	 */
	@Test
	public void hasOpenComplianceIssues() {
		transactionDataFetcher.hasOpenComplianceIssues("Tom Shelby");
	}

	/**
	 * Returns all transactions indexed by beneficiary name
	 */
	@Test
	public void getTransactionsByBeneficiaryName() {
		transactionDataFetcher.getTransactionsByBeneficiaryName();
	}

	/**
	 * Returns the identifiers of all open compliance issues
	 */
	@Test
	public void getUnsolvedIssueIds() {
		transactionDataFetcher.getUnsolvedIssueIds();
	}

	/**
	 * Returns a list of all solved issue messages
	 */
	@Test
	public void getAllSolvedIssueMessages() {
		transactionDataFetcher.getAllSolvedIssueMessages();
	}

	/**
	 * Returns the 3 transactions with highest amount sorted by amount
	 * descending
	 */
	@Test
	public void getTop3TransactionsByAmount() {
		transactionDataFetcher.getTop3TransactionsByAmount();
	}

	/**
	 * Returns the sender with the most total sent amount
	 */
	@Test
	public void getTopSender() {
		transactionDataFetcher.getTopSender();
		
	}
	@AfterClass
	public static void cleanUp(){
		transactionDataFetcher = null;
	}	

}
