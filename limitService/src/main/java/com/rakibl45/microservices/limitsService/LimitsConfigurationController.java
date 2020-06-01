/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rakibl45.microservices.limitsService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rakibl45.microservices.limitsService.bean.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rakibul
 */

@RestController
public class LimitsConfigurationController {
    
    @Autowired
    private Configuration configuration;
    
    @GetMapping("/limits")
    public LimitsConfiguration retriveLimitsFromConfig(){
        return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
    
    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod="fallbackRetreiveConfiguration")
    public LimitsConfiguration retrieveConfiguration() {
        throw new RuntimeException("Not available man");
    }
    
    public LimitsConfiguration fallbackRetreiveConfiguration() {
        return new LimitsConfiguration(99,9);
    }
}
