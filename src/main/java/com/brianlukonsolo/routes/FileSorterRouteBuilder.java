package com.brianlukonsolo.routes;

import com.brianlukonsolo.configuration.ConfigurationProcessor;
import com.brianlukonsolo.processors.SortingProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.brianlukonsolo.constants.CodeConstants.CamelHeaders.HEADER_FILE_TYPE;
import static com.brianlukonsolo.constants.CodeConstants.StringRelatedConstants.DOUBLE_NEWLINE;

@Component
public class FileSorterRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("file:INPUT?noop=true")
                .log("\n\n\nStarting route!!!\n\n\n")
                .process(new ConfigurationProcessor())
                .process(new SortingProcessor())
                .log(DOUBLE_NEWLINE + "HEADER ==> ${in.headers}" + DOUBLE_NEWLINE)
                .recipientList(simple("file:${headers.storageFolderRoot}" + "\\" + "${headers.fileType}"));
                //.to("file:" + "${headers.storageFolderRoot}" + "?noop=true");
    }
}
