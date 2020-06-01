/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rakibl45.microservices.currencyExchangeService.Repository;

import com.rakibl45.microservices.currencyExchangeService.bean.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rakibul
 */
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}
