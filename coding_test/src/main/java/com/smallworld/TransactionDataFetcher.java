package com.smallworld;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.smallworld.bean.Transaction;

public class TransactionDataFetcher{
	private static final Logger logger = LogManager.getLogger(TransactionDataFetcher.class);  
	
	List<Transaction> transectionList;
	List<Transaction> transectionListUnique;
	public TransactionDataFetcher() {
		BasicConfigurator.configure();  
		populateObjectFromJson();
	}

	/**
	 * Returns the sum of the amounts of all transactions
	 */

	public double getTotalTransactionAmount() {
		double sum = 0;
		try {
			sum = transectionListUnique.stream().mapToDouble(Transaction::getAmount).sum();
			logger.info("getTotalTransactionAmount " + sum);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return sum;
	}

	/**
	 * Returns the sum of the amounts of all transactions sent by the specified
	 * client
	 */
	public double getTotalTransactionAmountSentBy(String senderFullName) {
		double sum = 0;
		try {
			sum = transectionListUnique.stream()
					.filter(t -> t.getSenderFullName().trim().equalsIgnoreCase(senderFullName.trim()))
					.mapToDouble(a -> a.getAmount()).sum();
			logger.info("getTotalTransactionAmountSentBy " + sum);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return sum;
	}

	/**
	 * Returns the highest transaction amount
	 */
	public double getMaxTransactionAmount() {
		double sum = 0;
		try {
			sum = transectionList.stream().mapToDouble(a -> a.getAmount()).max().getAsDouble();
			System.out.println("getMaxTransactionAmount " + sum);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return sum;
	}

	/**
	 * Counts the number of unique clients that sent or received a transaction
	 */
	public long countUniqueClients() {
		long count = 0;
		try {
			List<String> senderNames = transectionListUnique.stream().map(n -> n.getBeneficiaryFullName()).distinct()
					.collect(Collectors.toList());
			count = senderNames.size();
			logger.info("countUniqueClients " + count);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return count;
	}

	/**
	 * Returns whether a client (sender or beneficiary) has at least one
	 * transaction with a compliance issue that has not been solved
	 */
	public boolean hasOpenComplianceIssues(String clientFullName) {
		long count = 0;
		try {
			count = transectionList.stream().filter(n -> !n.getIssueSolved()).count();
			logger.info("hasOpenComplianceIssues " + (count > 0));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return count > 0;
	}

	/**
	 * Returns all transactions indexed by beneficiary name
	 */
	public Map<String, Object> getTransactionsByBeneficiaryName() {
		Map<String, Object> allTransections = new HashMap<>();
		try {
			transectionList.forEach(t -> allTransections.put(t.getBeneficiaryFullName(), t));

			logger.info("getTransactionsByBeneficiaryName " + allTransections);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return allTransections;
	}

	/**
	 * Returns the identifiers of all open compliance issues
	 */
	public Set<Integer> getUnsolvedIssueIds() {
		Set<Integer> unsolvedIssueIds = new HashSet<>();
		try {
			unsolvedIssueIds = transectionList.stream().filter(n -> !n.getIssueSolved()).map(t -> t.getIssueId())
					.collect(Collectors.toSet());
			logger.info("getUnsolvedIssueIds " + (unsolvedIssueIds));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return unsolvedIssueIds;
	}

	/**
	 * Returns a list of all solved issue messages
	 */
	public List<String> getAllSolvedIssueMessages() {
		List<String> allSolvedIssueMessages = new ArrayList<>();
		try {
			allSolvedIssueMessages = transectionList.stream().filter(n -> n.getIssueSolved())
					.map(t -> t.getIssueMessage()).collect(Collectors.toList());
			logger.info("getAllSolvedIssueMessages " + (allSolvedIssueMessages));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return allSolvedIssueMessages;
	}

	/**
	 * Returns the 3 transactions with highest amount sorted by amount
	 * descending
	 */
	public List<Transaction> getTop3TransactionsByAmount() {
		List<Transaction> top3TransactionsByAmount = new ArrayList<>();
		try {
			top3TransactionsByAmount = transectionListUnique.stream()
					.sorted(Comparator.comparing(Transaction::getAmount).reversed()).limit(3)
					.collect(Collectors.toList());

			logger.info("------------------------------");
			logger.info("getTop3TransactionsByAmount ");
			
			top3TransactionsByAmount.forEach(e-> System.out.println(e.getAmount()));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return top3TransactionsByAmount;
	}

	/**
	 * Returns the sender with the most total sent amount
	 */
	public Optional<Object> getTopSender() {
		
		Optional topSender= null;
		try {
			//Map<String, Long> counting = transectionList.stream().collect(Collectors.groupingBy
				//	(Transaction::getSenderFullName, Collectors.counting()));
					
			Map<String, Double> senders = transectionListUnique.stream().collect(Collectors.groupingBy
				(Transaction::getSenderFullName, Collectors.summingDouble(Transaction::getAmount)));
					
			//senders.entrySet().stream()
		      //.forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
	            
			topSender= senders.entrySet().stream().max((e1,e2) -> e1.getValue()> e2.getValue()?1:-1);
			logger.info("------------------------------");
			logger.info("getTopSender "+topSender);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new UnsupportedOperationException();
		}
		return topSender;
	}

	public void populateObjectFromJson() {
		String pathFile = "transactions.json";
		Gson gson = new Gson();
		Set<Integer> isAdded = new HashSet<>();
		transectionListUnique = new ArrayList<>();
		try {
			Transaction[] transactions =  gson.fromJson(new FileReader(pathFile), Transaction[].class);
			transectionList = Arrays.asList(transactions);
			transectionList.forEach(t->
			{
				if(!isAdded.contains(t.getMtn())){
					transectionListUnique.add(t);
					isAdded.add(t.getMtn());
				}
			});
			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

	}
	
}
