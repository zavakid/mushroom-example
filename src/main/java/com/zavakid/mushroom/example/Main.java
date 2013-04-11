/*
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.zavakid.mushroom.example;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zavakid.mushroom.DefaultMetricsSystem;

/**
 * @author zavakid 2013 2013-4-11 上午8:35:27
 * @since 1.0
 */
public class Main {

    private static final int THREAD_NUM = 5;

    public static void main(String[] args) throws InterruptedException {
        DefaultMetricsSystem.initialize("test");
        App app = new App();
        concurrentRun(app);
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void concurrentRun(final App app) {
        ExecutorService es = Executors.newFixedThreadPool(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            es.submit(new Runnable() {

                private Random random = new Random();

                @Override
                public void run() {
                    try {
                        do {
                            app.call(random.nextInt(10) * 1000);
                        } while (true);
                    } catch (InterruptedException e) {
                        // just print the stack trace
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
