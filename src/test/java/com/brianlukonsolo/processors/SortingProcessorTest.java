package com.brianlukonsolo.processors;

import com.brianlukonsolo.utility.CamelExchangeFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.spring.SpringCamelContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class SortingProcessorTest {
    private SortingProcessor sortingProcessor;
    private CamelContext context;
    private File file;

    @Before
    public void before(){
        file = new File("INPUT/file.txt");
        context = new SpringCamelContext();
        sortingProcessor = new SortingProcessor();
    }

    @Test
    public void whenGivenAFileItShouldSetTheFileTypeAsAHeaderInTheExchange() throws Exception {
        Exchange exchange = CamelExchangeFactory.createExchange(context);
        exchange.getIn().setBody(file);
        sortingProcessor.process(exchange);
        String expected = "txt";
        String actualHeader = (String) exchange.getIn().getHeader("fileType");

        assertEquals(expected, actualHeader);
    }
}
