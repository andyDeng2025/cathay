package com.cathay.coindesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cathay.coindesk.entity.Currency;

/**
 * Currency 實體的 JPA 數據庫操作接口。
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
