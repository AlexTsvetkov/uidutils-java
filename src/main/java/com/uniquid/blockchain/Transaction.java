package com.uniquid.blockchain;

public class Transaction {
	
	private String txid;
	private long version;
	private long confirmations;
	private long time;
	private String spentTxId;
	
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public long getConfirmations() {
		return confirmations;
	}
	public void setConfirmations(long confirmations) {
		this.confirmations = confirmations;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getSpentTxId() {
		return spentTxId;
	}
	public void setSpentTxId(String spentTxId) {
		this.spentTxId = spentTxId;
	}

}