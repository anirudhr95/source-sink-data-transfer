
package server;

import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;

import java.util.Enumeration;
import java.util.Optional;

public class RegistryHelper {

  public static Optional<Collector.MetricFamilySamples> findRecordedMetric(String name, CollectorRegistry collectorRegistry) {
    Enumeration<Collector.MetricFamilySamples> samples = collectorRegistry.metricFamilySamples();
    while (samples.hasMoreElements()) {
      Collector.MetricFamilySamples sample = samples.nextElement();
        if(name  == sample.name){
          return Optional.of(sample);
        }
    }
    return Optional.empty();
  }

  public static Collector.MetricFamilySamples findRecordedMetricOrThrow(
      String name, CollectorRegistry collectorRegistry) {
    Optional<Collector.MetricFamilySamples> result = findRecordedMetric(name, collectorRegistry);
    if (!result.isPresent()){
      throw new IllegalArgumentException("Could not find metric with name: " + name);
    }
    return result.get();
  }

  public static Enumeration<Collector.MetricFamilySamples> findRecordedMetric(CollectorRegistry collectorRegistry) {
    return collectorRegistry.metricFamilySamples();

  }
  private static void printSamples(Collector.MetricFamilySamples samples) {
    System.out.println(samples.toString());
  }
}
