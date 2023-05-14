package com.smallworld.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

	Integer mtn;
	Double amount;
	String senderFullName;
	Integer senderAge;
	String beneficiaryFullName;
	Integer beneficiaryAge;
	Integer issueId;
	Boolean issueSolved;
	String issueMessage;
	
}
