package com;

import com.bank.orion.operation.AdminOperation;
import com.bank.orion.operation.UserOperation;
import com.bank.orion.service.TransactionService;

public class OperationDemo {

	public static void main(String[] args) {
		UserOperation user = new UserOperation();
		AdminOperation admin = new AdminOperation();

		String accountNum = "987654321";
		String accountType = "Checking";
		String accountNum2 = "987654322";
		String accountType2 = "Saving";
		String email = "hunle@test.com";
		String dob = "11/11/2011";
		String SSN = "1234";
		String name = "Hung Le";
		String balance = "1500";
		String userName = "Dev";
		String password = "12345";

		// Payment variables
		String amount = "500";
		String transactionDate = "05/03/2021";
		String description = "Test";
		String receivingAccountNumber = accountNum;
		String sendingAccountNumber = accountNum2;
		String receivingRounting = TransactionService.BANK_ROUTING_NUMBER;
		boolean isRecur = true;
		int repeatTime = 2;
		long period = 5;

		// Create account
//		 System.out.println(admin.createUser(accountNum, accountType, email, dob, SSN,
//		 name, balance));
//		 System.out.println(admin.createUser(accountNum2, accountType2, email, dob,
//		 SSN, name, balance));

		// Activate account
//		 System.out.println(user.activateUser(email, dob, SSN, name, password,
//		 userName));

		// Login
		// System.out.println(user.login(userName, password));
		// System.out.println(user.login(userName, "wrong"));

		// Get accounts with email
		// System.out.println(user.getAccountsWithEmail(email));

		// Get account with acct num
		// System.out.println(user.getAccountWithAcctNum(accountNum));

		// Schedule payment
//		 System.out.println(user.schedulePayment(amount, transactionDate, description,
//		 receivingAccountNumber,
//		 sendingAccountNumber, receivingRounting, isRecur, repeatTime, period));

		// Get transaction with acct num
		// System.out.println(user.getAccountWithAcctNum(accountNum));
		// System.out.println(user.getAccountWithAcctNum(accountNum2));

		// Get transaction with ID
		// System.out.println(user.getTransactionWithID("uhzx0sXkwCXdXObB"));

		// Cancel transaction
//		 System.out.println(user.cancelTransaction("tu633CsPSqlUgWSQ"));
//		 System.out.println(user.cancelTransaction("tu633CsPSqlUgWSQ"));

		// Search user
		// System.out.println(admin.searchAccountHolder(email));
		// System.out.println(admin.searchAccountHolder(userName));
		// System.out.println(admin.searchAccountHolder(name));

		// Search transaction
		// System.out.println(admin.searchTransactions("uhzx0sXkwCXdXObB"));
		// System.out.println(admin.searchTransactions(accountNum));

		// Suspend user
		// System.out.println(admin.suspendUser(email));

		// Reactivate user
		// System.out.println(admin.reactivateUser(email));

		// Suspend account
		// System.out.println(admin.suspendAccount(accountNum));

		// Reactivate account
		// System.out.println(admin.reactivateAccount(accountNum));

		// Deposit
		// System.out.println(admin.deposit(accountNum, "200"));

		// Deduct
		// System.out.println(admin.deduct(accountNum, "2000"));
		// System.out.println(admin.deduct(accountNum, "1500"));

		// Approve transaction
		// System.out.println(admin.approveTransaction("tu633CsPSqlUgWSQ"));
		// System.out.println(admin.deduct(accountNum2, "1200"));
//		System.out.println(admin.approveTransaction("KQlHNQnEsUn6gNqW"));
//		System.out.println(admin.deposit(accountNum2, "700"));
//		System.out.println(admin.approveTransaction("KQlHNQnEsUn6gNqW"));

	}

}
