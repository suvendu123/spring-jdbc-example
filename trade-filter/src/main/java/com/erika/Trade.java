package com.erika;

import java.util.Date;

public class Trade {
	
	private String tradeId;
	private Date tradeDate;
	private String intId;
	private Integer quantity;
	private Integer price;
	private Integer amount;
	private String trader;
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getIntId() {
		return intId;
	}
	public void setIntId(String intId) {
		this.intId = intId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getTrader() {
		return trader;
	}
	public void setTrader(String trader) {
		this.trader = trader;
	}
	
	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", tradeDate=" + tradeDate + ", intId=" + intId + ", quantity=" + quantity
				+ ", price=" + price + ", amount=" + amount + ", trader=" + trader + "]";
	}

	

}
