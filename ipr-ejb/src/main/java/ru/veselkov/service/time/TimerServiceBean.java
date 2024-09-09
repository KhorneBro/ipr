package ru.veselkov.service.time;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.ejb.*;

//@Stateless
public class TimerServiceBean {

//    @Resource
//    private SessionContext sessionContext;

    private TimerService timerService;

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
        timerService.createTimer(milisec, "Timer created");
    }
}
