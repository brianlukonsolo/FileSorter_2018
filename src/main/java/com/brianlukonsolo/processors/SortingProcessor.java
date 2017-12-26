package com.brianlukonsolo.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SortingProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setHeader("fileType", extractFileExtension(exchange.getIn().getHeader("CamelFileName", String.class)));


    }

    private String extractFileExtension(String fileName){
        int indexOfExtension = 0;
        String[] typeString = fileName.split("\\.");
        for(int i=0; i<typeString.length; i++){
            indexOfExtension++;
        }
        indexOfExtension = indexOfExtension - 1;

        return typeString[indexOfExtension];


    }
}
