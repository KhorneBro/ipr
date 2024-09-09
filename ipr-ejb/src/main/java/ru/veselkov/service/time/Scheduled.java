package ru.veselkov.service.time;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.*;

@Startup
@Singleton
public class Scheduled {

    @PostConstruct
    public void constr() {
        System.out.println("Scheduled");
//        execute();
    }

    @Schedule(second = "*/10", minute = "*", hour = "*")
    public void execute() {
        System.out.println("Scheduled execute");
    }
}
