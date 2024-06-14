package com.example.trackolapassigment.countryLocator;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformanceTest {
    public static void main(String[] args) throws IOException {
        CountryLocator countryLocator = new CountryLocator("src/main/resources/world.geojson");
        int requestCount = 100;
        long startTime = System.nanoTime();

        Runnable task = () -> {
            double latitude = Math.random() * 180 - 90;
            double longitude = Math.random() * 360 - 180;
            countryLocator.getCountryCode(latitude, longitude);
        };

        var executor = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < requestCount; i++) {
            executor.schedule(task, i * 10, TimeUnit.MILLISECONDS);
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Average execution time: " + (duration / (double) requestCount) + " ms");
    }
}
