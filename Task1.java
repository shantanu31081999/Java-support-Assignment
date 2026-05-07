import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task1 {
	public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {
		
	    //List<LoanAccount> result = null;
		
		//Fix: Initialize to avoid    NullPointerException
	    List<LoanAccount> result = new ArrayList<>(); 
	 
	    for (LoanAccount account : accounts) {

	//Fix: Added null check before account.getDueDate() for due date to avoid  NullPointerException
	        if (account.getDueDate() != null && account.getDueDate().before(new Date())) {

	// FIX: Only include accounts where balance is greater than 0
	            if (account.getOutstandingBalance() > 0.0) {
	                result.add(account);
	            }
	        }
	    }
	    return result;
	}

	}
	 
	// LoanAccount fields:
	// Date dueDate          — may be null for restructured accounts
	// double outstandingBalance
	// String accountId      — always non-null
