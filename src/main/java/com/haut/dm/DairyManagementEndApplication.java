package com.haut.dm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DairyManagementEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(DairyManagementEndApplication.class, args);
    }

}
