package com.example.trackolapassigment.countryLocator;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class CountryLocatorTest {
    private CountryLocator countryLocator;

    @Before
    public void setUp() throws IOException {
        countryLocator = new CountryLocator("src/main/resources/world.geojson");
    }

    @Test
    public void testGetCountryCode() {
        assertEquals("US", countryLocator.getCountryCode(37.7749, -122.4194)); // San Francisco, USA
        assertEquals("IN", countryLocator.getCountryCode(28.6139, 77.2090));  // New Delhi, India
        assertEquals("Unknown", countryLocator.getCountryCode(0.0, 0.0));     // Ocean, no country
    }

   
}

