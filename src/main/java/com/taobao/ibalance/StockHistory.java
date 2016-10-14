package com.taobao.ibalance;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class StockHistory {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36, nullable = false)
	private String uuid;
	private String date;
	private String openingPrice;
	private String highestPrice;
	private String lowestPrice;
	private String closingPrice;
	private String changeAmount;
	private String changeRate;
	private String tradingVolume;
	private String changingOver;
	private String amplitude;
	private String turnOver;

	@ManyToOne(cascade = { CascadeType.PERSIST }, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
	private StockCode stockCode = null;

	protected StockHistory() {
	}

	public StockHistory(String date, String openingPrice, String highestPrice, String lowestPrice, String closingPrice,
			String changeAmount, String changeRate, String tradingVolume, String changingOver, String amplitude,
			String turnOver) {
		super();
		this.date = date;
		this.openingPrice = openingPrice;
		this.highestPrice = highestPrice;
		this.lowestPrice = lowestPrice;
		this.closingPrice = closingPrice;
		this.changeAmount = changeAmount;
		this.changeRate = changeRate;
		this.tradingVolume = tradingVolume;
		this.changingOver = changingOver;
		this.amplitude = amplitude;
		this.turnOver = turnOver;
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the openingPrice
	 */
	public String getOpeningPrice() {
		return openingPrice;
	}

	/**
	 * @param openingPrice
	 *            the openingPrice to set
	 */
	public void setOpeningPrice(String openingPrice) {
		this.openingPrice = openingPrice;
	}

	/**
	 * @return the highestPrice
	 */
	public String getHighestPrice() {
		return highestPrice;
	}

	/**
	 * @param highestPrice
	 *            the highestPrice to set
	 */
	public void setHighestPrice(String highestPrice) {
		this.highestPrice = highestPrice;
	}

	/**
	 * @return the lowestPrice
	 */
	public String getLowestPrice() {
		return lowestPrice;
	}

	/**
	 * @param lowestPrice
	 *            the lowestPrice to set
	 */
	public void setLowestPrice(String lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	/**
	 * @return the closingPrice
	 */
	public String getClosingPrice() {
		return closingPrice;
	}

	/**
	 * @param closingPrice
	 *            the closingPrice to set
	 */
	public void setClosingPrice(String closingPrice) {
		this.closingPrice = closingPrice;
	}

	/**
	 * @return the changeAmount
	 */
	public String getChangeAmount() {
		return changeAmount;
	}

	/**
	 * @param changeAmount
	 *            the changeAmount to set
	 */
	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}

	/**
	 * @return the changeRate
	 */
	public String getChangeRate() {
		return changeRate;
	}

	/**
	 * @param changeRate
	 *            the changeRate to set
	 */
	public void setChangeRate(String changeRate) {
		this.changeRate = changeRate;
	}

	/**
	 * @return the tradingVolume
	 */
	public String getTradingVolume() {
		return tradingVolume;
	}

	/**
	 * @param tradingVolume
	 *            the tradingVolume to set
	 */
	public void setTradingVolume(String tradingVolume) {
		this.tradingVolume = tradingVolume;
	}

	/**
	 * @return the changingOver
	 */
	public String getChangingOver() {
		return changingOver;
	}

	/**
	 * @param changingOver
	 *            the changingOver to set
	 */
	public void setChangingOver(String changingOver) {
		this.changingOver = changingOver;
	}

	/**
	 * @return the amplitude
	 */
	public String getAmplitude() {
		return amplitude;
	}

	/**
	 * @param amplitude
	 *            the amplitude to set
	 */
	public void setAmplitude(String amplitude) {
		this.amplitude = amplitude;
	}

	/**
	 * @return the turnOver
	 */
	public String getTurnOver() {
		return turnOver;
	}

	/**
	 * @param turnOver
	 *            the turnOver to set
	 */
	public void setTurnOver(String turnOver) {
		this.turnOver = turnOver;
	}

	/**
	 * @return the stockCode
	 */
	public StockCode getStockCode() {
		return stockCode;
	}

	/**
	 * @param stockCode
	 *            the stockCode to set
	 */
	public void setStockCode(StockCode stockCode) {
		this.stockCode = stockCode;
	}

}