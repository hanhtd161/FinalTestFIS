package com.fis.bank.ulti;

public class Constant {
	private Constant() {};
	public static final int CUSTOMER_STATUS_INACTIVE = 0;
	public static final int CUSTOMER_STATUS_ACTIVE = 1;
	public static final int ACCOUNT_STATUS_INACTIVE = 0;
	public static final int ACCOUNT_STATUS_ACTIVE = 1;
	public static final int ACCOUNT_STATUS_LOCKED = 2;
	public static final int ACCOUNT_STATUS_WAITING = 3; 
	public static final int TRANSACTION_STATUS_SUCCESS = 0;
	public static final int TRANSACTION_STATUS_FAIL_FROMACCOUNT_INVALID = 1; 
	public static final int TRANSACTION_STATUS_FAIL_TOACCOUNT_INVALID = 2; 
	public static final int TRANSACTION_STATUS_FAIL_AMOUNT_NOT_ENOUGH = 3;
	public static final int TRANSACTION_STATUS_FAIL_APP_ERROR = 4; 
}
