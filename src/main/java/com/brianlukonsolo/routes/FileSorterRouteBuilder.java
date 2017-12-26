package com.brianlukonsolo.routes;

import com.brianlukonsolo.processors.SortingProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileSorterRouteBuilder extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("file:INPUT?noop=true")
                .log("\n\n\nStarting route!!!\n\n\n")
                .process(new SortingProcessor())
                .to("file:OUTPUT?noop=true");
    }
}
