package com.guigarage.dolphin;

import com.canoo.dolphin.server.spring.DolphinPlatformApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@DolphinPlatformApplication
public class ServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[]{ServerApplication.class}, args);
    }
}

