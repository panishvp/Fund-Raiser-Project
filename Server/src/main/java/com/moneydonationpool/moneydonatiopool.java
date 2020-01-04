package com.moneydonationpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan("com.moneydonationpool")
public class moneydonatiopool 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(moneydonatiopool.class, args);
    }
}
