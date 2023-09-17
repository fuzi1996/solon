package org.noear.solon.cloud.metrics.integration;

import io.micrometer.core.instrument.*;
import org.noear.solon.annotation.Mapping;

import java.util.*;

/**
 * 度量基础接口
 *
 * @author noear
 * @since 2.4
 */
public class MetricsController {
    /**
     * 获取所有注册器（类名）
     * */
    @Mapping("/metrics/registrys")
    public Map<String, List<?>> registrys() {
        Map<String, List<?>> data = new HashMap<>();
        List<String> registrys = new ArrayList<>();

        for (MeterRegistry meterRegistry : Metrics.globalRegistry.getRegistries()) {
            registrys.add(meterRegistry.getClass().getName());
        }

        data.put("_registrys", registrys);
        return data;
    }

    /**
     * 获取所有度量器（名字）
     * */
    @Mapping("/metrics/meters")
    public Map<String, List<?>> meters() {
        Map<String, List<?>> data = new HashMap<>();
        List<String> meters = new ArrayList<>();

        Metrics.globalRegistry.getMeters().forEach(meter -> {
            meters.add(meter.getId().getName());
        });

        data.put("_meters", meters);
        return data;
    }

    /**
     * 获取度量详情
     * */
    @Mapping("/metrics/meter/{meterName}")
    public Map<String, Object> meter(String meterName) {
        Map<String, Object> meterData = new LinkedHashMap<>();
        meterData.put("name", meterName);

        for (Meter meter : Metrics.globalRegistry.getMeters()) {
            if (meterName.equals(meter.getId().getName())) {
                meterData.put("description", meter.getId().getDescription());
                meterData.put("baseUnit", meter.getId().getBaseUnit());

                Map<String, Object> measurements = new LinkedHashMap<>();
                for (Measurement measure : meter.measure()) {
                    measurements.put(measure.getStatistic().name(), measure.getValue());
                }
                meterData.put("measurements", measurements);

                Map<String, Object> tags = new LinkedHashMap<>();
                for (Tag tag : meter.getId().getTags()) {
                    tags.put(tag.getKey(), tag.getValue());
                }
                meterData.put("tags", tags);

                break;
            }
        }

        return meterData;
    }
}
