package com.smallworld.com.smallworld;

import com.smallworld.TransactionDataFetcher;

public class MyClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
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

		}catch(Exception e){e.printStackTrace();}
	}

}
