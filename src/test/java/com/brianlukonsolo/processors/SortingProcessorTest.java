package com.brianlukonsolo.processors;

import com.brianlukonsolo.utility.CamelExchangeFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.spring.SpringCamelContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static com.brianlukonsolo.constants.CodeConstants.CamelHeaders.HEADER_CAMEL_FILE_NAME;
import static com.brianlukonsolo.constants.CodeConstants.CamelHeaders.HEADER_FILE_TYPE;
import static com.brianlukonsolo.constants.CodeConstants.StringRelatedConstants.TXT_FILE_EXTENSION;
import static org.junit.Assert.assertEquals;

public class SortingProcessorTest {
    private SortingProcessor sortingProcessor;
    private CamelContext context;
    private Exchange exchange;
    private File file;

    @Before
    public void before(){
        file = new File("INPUT/file.txt");
        context = new SpringCamelContext();
        sortingProcessor = new SortingProcessor();
    }

    @Test
    public void whenGivenAFileItShouldSetTheFileTypeAsAHeaderInTheExchange() throws Exception {
        exchange = CamelExchangeFactory.createExchange(context);
        exchange.getIn().setBody(file);
        exchange.getIn().setHeader(HEADER_CAMEL_FILE_NAME,"testFile.txt");
        sortingProcessor.process(exchange);
        String expected = TXT_FILE_EXTENSION;
        String actualHeader = (String) exchange.getIn().getHeader(HEADER_FILE_TYPE);

        assertEquals(expected, actualHeader);


    }

    @Test
    public void whenPassedAFilenameWithMultipleFullStopsItShouldGetTheLastString() throws Exception {
        exchange = CamelExchangeFactory.createExchange(context);
        exchange.getIn().setBody(file);
        exchange.getIn().setHeader(HEADER_CAMEL_FILE_NAME,"testFile.myFile.txt");
        sortingProcessor.process(exchange);
        String expected = TXT_FILE_EXTENSION;
        String actualHeader = (String) exchange.getIn().getHeader(HEADER_FILE_TYPE);

        assertEquals(expected, actualHeader);


    }
}
