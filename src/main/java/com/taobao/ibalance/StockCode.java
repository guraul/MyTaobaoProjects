package com.taobao.ibalance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class StockCode {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36, nullable = false)
	private String uuid;
	private String code;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "stockCode")
	private List<StockHistory> stockHistoryList = new ArrayList<>();

	protected StockCode() {
	}

	public StockCode(String code) {
		super();
		this.code = code;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the stockHistoryList
	 */
	public List<StockHistory> getStockHistoryList() {
		return stockHistoryList;
	}

	/**
	 * @param stockHistoryList
	 *            the stockHistoryList to set
	 */
	public void setStockHistoryList(List<StockHistory> stockHistoryList) {
		this.stockHistoryList = stockHistoryList;
	}

}