package com.brianlukonsolo.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SortingProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("BODY IS OF TYPE ======> " + exchange.getIn().getHeader("CamelFileName"));
    }
}
