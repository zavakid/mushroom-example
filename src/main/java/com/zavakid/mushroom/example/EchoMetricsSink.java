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

import java.io.PrintStream;

import org.apache.commons.configuration.SubsetConfiguration;

import com.zavakid.mushroom.Metric;
import com.zavakid.mushroom.MetricsRecord;
import com.zavakid.mushroom.MetricsSink;
import com.zavakid.mushroom.MetricsTag;

/**
 * @author zavakid 2013 2013-4-11 上午8:56:55
 * @since 1.0
 */
public class EchoMetricsSink implements MetricsSink {

    private PrintStream writer = System.out;

    @Override
    public void init(SubsetConfiguration conf) {
        // op op
    }

    @Override
    public void putMetrics(MetricsRecord record) {

        writer.print(record.timestamp());
        writer.print(" ");
        writer.print(record.context());
        writer.print(".");
        writer.print(record.name());
        String separator = ": ";
        for (MetricsTag tag : record.tags()) {
            writer.print(separator);
            separator = ", ";
            writer.print(tag.name());
            writer.print("=");
            writer.print(String.valueOf(tag.value()));
        }
        for (Metric metric : record.metrics()) {
            writer.print(separator);
            separator = ", ";
            writer.print(metric.name());
            writer.print("=");
            writer.print(metric.value());
        }
        writer.println();
    }

    @Override
    public void flush() {
        writer.flush();
    }

}
