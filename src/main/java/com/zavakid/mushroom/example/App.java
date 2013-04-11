package com.zavakid.mushroom.example;

/**
 * @author zavakid 2013 2013-4-11 上午8:29:45
 * @since 1.0
 */
public class App {

    final AppMetricsSources appMetircs;
    private int             load;

    public App(){
        appMetircs = AppMetricsSources.create();
    }

    public void call(long sleepMillis) throws InterruptedException {
        long start = System.currentTimeMillis();
        load++;
        appMetircs.addCalledTime();
        appMetircs.setAppLoad(load);

        Thread.sleep(sleepMillis);

        long end = System.currentTimeMillis();

        appMetircs.calledSpent(end - start);
        load--;
    }

    public void load() {

    }
}
