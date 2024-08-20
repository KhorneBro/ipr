package ru.veselkov.service;

import jakarta.annotation.Resource;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;

@Stateless
public class TimerService {

    @Resource
    private SessionContext sessionContext;

    @Timeout
    public void timeOut(Timer timer) {
        System.out.println("timeOut " + timer.getInfo());
        timer.cancel();
    }

    public void createTimer(long milisec) {
        sessionContext.getTimerService().createTimer(milisec, "Timer created");
    }
}
