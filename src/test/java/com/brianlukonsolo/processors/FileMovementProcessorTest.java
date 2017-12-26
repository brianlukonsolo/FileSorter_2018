package com.brianlukonsolo.processors;

import com.brianlukonsolo.utility.CamelExchangeFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.spring.SpringCamelContext;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static com.brianlukonsolo.constants.CodeConstants.CamelHeaders.HEADER_CAMEL_FILE_NAME;
import static com.brianlukonsolo.constants.CodeConstants.CamelHeaders.HEADER_FILE_TYPE;
import static com.brianlukonsolo.constants.CodeConstants.CamelHeaders.HEADER_STORAGE_FOLDER_ROOT;
import static com.brianlukonsolo.constants.CodeConstants.StringRelatedConstants.TXT_FILE_EXTENSION;
import static org.junit.Assert.assertEquals;

public class FileMovementProcessorTest {
    private FileMovementProcessor fileMovementProcessor;
    private CamelContext context;
    private Exchange exchange;
    private File file;
    private static final String SORTING_FOLDER_PATH = "src\\test\\FileSorter2018_SORTING_FOLDER\\";

    @Before
    public void before(){
        file = new File(SORTING_FOLDER_PATH);
        context = new SpringCamelContext();
        fileMovementProcessor = new FileMovementProcessor();
    }

    @Test
    public void itShouldCreateAStorageRootFolderIfItDoesNotExist() throws Exception {
        exchange = CamelExchangeFactory.createExchange(context);
        exchange.getIn().setBody(file);
        exchange.getIn().setHeader(HEADER_STORAGE_FOLDER_ROOT, SORTING_FOLDER_PATH);

        fileMovementProcessor.process(exchange);

        file = new File(SORTING_FOLDER_PATH);
        boolean result = false;

        if(!file.exists()){

        }else {
            result = true;
        }

        assertEquals(true, result);


    }

    @Test
    public void itShouldCreateAFolderWithTheSameNameAsTheExtensionTheFileWithingTheRootSortingFolder() throws Exception {
        exchange = CamelExchangeFactory.createExchange(context);
        exchange.getIn().setBody(file);
        exchange.getIn().setHeader(HEADER_STORAGE_FOLDER_ROOT, SORTING_FOLDER_PATH);
        exchange.getIn().setHeader(HEADER_CAMEL_FILE_NAME,"testFile.txt");
        exchange.getIn().setHeader(HEADER_FILE_TYPE,TXT_FILE_EXTENSION);
        fileMovementProcessor.process(exchange);

        file = new File(SORTING_FOLDER_PATH + TXT_FILE_EXTENSION);
        boolean result = false;

        if(!file.exists()){

        }else {
            result = true;
        }

        assertEquals(true, result);


    }


    //Methods
    private String addTrailingSlashIfNotAdded(String folderPath){
        String path = folderPath;
        if(path.charAt(folderPath.length()-1) != '\\'){
            path = path + "\\";
        }

        return path;


    }
}
