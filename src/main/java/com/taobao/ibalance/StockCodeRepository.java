package com.taobao.ibalance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StockCodeRepository extends CrudRepository<StockCode, String> {

	List<StockCode> findByCode(String stockCode);
}