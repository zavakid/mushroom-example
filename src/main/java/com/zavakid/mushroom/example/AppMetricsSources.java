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

import com.zavakid.mushroom.DefaultMetricsSystem;
import com.zavakid.mushroom.lib.AbstractMetricsSource;
import com.zavakid.mushroom.lib.MetricMutableCounterLong;
import com.zavakid.mushroom.lib.MetricMutableGaugeInt;
import com.zavakid.mushroom.lib.MetricMutableStat;

/**
 * @author zavakid 2013-4-11 上午8:11:22
 * @since 1.0
 */
public class AppMetricsSources extends AbstractMetricsSource {

    final MetricMutableCounterLong appCalledCounter;
    final MetricMutableGaugeInt    appLoad;
    final MetricMutableStat        appCalledStat;

    public static AppMetricsSources create() {
        AppMetricsSources result = new AppMetricsSources("app");
        DefaultMetricsSystem.registerSource("app", "app source", result);
        return result;
    }

    private AppMetricsSources(String name){
        super(name);
        appCalledCounter = registry().newCounter("appCalled", "count the app called times", 0L);
        appLoad = registry().newGauge("appLoad", "the app load", 0);
        appCalledStat = registry().newStat("appCalledStat", "app called stat", "call method", "time");
    }

    public void addCalledTime() {
        appCalledCounter.incr();
    }

    public void setAppLoad(int load) {
        appLoad.set(load);
    }

    public void calledSpent(long timeSpent) {
        appCalledStat.add(timeSpent);
    }

}
