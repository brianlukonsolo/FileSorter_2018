package com.brianlukonsolo;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class App {
    public static void main(String[] args) throws Exception {

        if (args.length > 0){
            for (int i=0;i<args.length;i++) {
                System.out.println("\n\nARGUMENTS PROVIDED >>>> " + args[0] + "\n\n");
                //TODO: implement logic such that the program can sort multiple folders
            }
        }

        SpringApplication.run(App.class);

        CamelContext context = new DefaultCamelContext();
        //Spring automatically finds the routes
        context.start();
        Thread.sleep(5000);
        context.stop();
    }
}
