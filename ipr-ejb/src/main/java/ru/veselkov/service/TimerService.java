package ru.veselkov.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.ejb.*;

@Stateless
public class TimerService {

    @Resource
    private SessionContext sessionContext;

    @PostConstruct
    public void init() {
        System.out.println("TimerService init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("TimerService destroy");
    }

    @Timeout
    public void timeOut(Timer timer) {
        System.out.println("timeOut " + timer.getInfo());
        timer.cancel();
    }

    public void createTimer(long milisec) {
        sessionContext.getTimerService().createTimer(milisec, "Timer created");
    }
}
