/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.oap.server.telemetry.api;

import org.apache.skywalking.oap.server.library.module.Service;

/**
 * Open API to telemetry module, allow to create metrics instance with different type. Types inherits from prometheus
 * project, and plan to move to openmetrics APIs after it is ready.
 *
 * @author wusheng
 */
public interface MetricsCreator extends Service {
    /**
     * Create a counter type metrics instance.
     *
     * @param name
     * @param tips
     * @param tagKeys
     * @return
     */
    CounterMetrics createCounter(String name, String tips, MetricsTag.Keys tagKeys, MetricsTag.Values tagValues);

    /**
     * Create a gauge type metrics instance.
     *
     * @param name
     * @param tips
     * @param tagKeys
     * @return
     */
    GaugeMetrics createGauge(String name, String tips, MetricsTag.Keys tagKeys, MetricsTag.Values tagValues);

    /**
     * Create a Histogram type metrics instance.
     *
     * @param name
     * @param tips
     * @param tagKeys
     * @param buckets Time bucket for duration.
     * @return
     */
    HistogramMetrics createHistogramMetric(String name, String tips, MetricsTag.Keys tagKeys, MetricsTag.Values tagValues, double... buckets);
}
