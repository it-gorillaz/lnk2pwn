package com.itgorillaz.lnk2pwn;

import java.io.IOException;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Lnk2Pwn {
        
    public static void main(String[] args) throws IOException {    	    	
        new SpringApplicationBuilder(Lnk2Pwn.class)
            .web(WebApplicationType.NONE)
            .bannerMode(Mode.OFF)
            .headless(false)
            .build()
            .run(args);        	
    }
}
