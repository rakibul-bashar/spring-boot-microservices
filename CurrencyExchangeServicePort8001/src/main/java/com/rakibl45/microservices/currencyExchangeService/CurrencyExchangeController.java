/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rakibl45.microservices.currencyExchangeService;

import com.rakibl45.microservices.currencyExchangeService.Repository.ExchangeValueRepository;
import com.rakibl45.microservices.currencyExchangeService.bean.ExchangeValue;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rakibul
 */

@RestController
public class CurrencyExchangeController {
    
    @Autowired
    private Environment environment;
    
    @Autowired
    private ExchangeValueRepository repository;
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public  ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to){
         ExchangeValue exchangeValue =  
            repository.findByFromAndTo(from, to);
         exchangeValue.setPort(
                 Integer.parseInt(environment.getProperty("local.server.port")));
         return exchangeValue;
    }
}
