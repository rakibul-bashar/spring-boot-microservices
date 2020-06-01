/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rakibl45.microservices.currencyConversionService;

import com.rakibl45.microservices.currencyConversionService.bean.CurrencyConversionBean;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rakibul
 */

@RestController
public class CurrencyConversionController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CurrencyExchangeServiceProxy proxy;
    
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean ConvertCurrency(@PathVariable String from, 
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        System.out.println(uriVariables + "ddddddddddddddddddd");
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
                CurrencyConversionBean.class, 
                uriVariables);
        
        CurrencyConversionBean response = responseEntity.getBody();
        System.out.println(response+"check response");
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
       
    
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean ConvertCurrencyFeign(@PathVariable String from, 
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("from", from);
//        uriVariables.put("to", to);
//        System.out.println(uriVariables + "ddddddddddddddddddd");
//        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
//                CurrencyConversionBean.class, 
//                uriVariables);
//        
        CurrencyConversionBean response = proxy.retriveExchangeValue(from,to);
        logger.info("{}",response);
        System.out.println(response+"check response");
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
