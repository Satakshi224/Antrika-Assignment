# Country Locator

## Objective
This project determines the country code based on latitude and longitude without using external API services.

## Structure
- `CountryLocator.java`: Contains the logic to map coordinates to country codes.
- `CountryLocatorTest.java`: Contains JUnit tests for `CountryLocator`.
- `PerformanceTest.java`: Tests the performance of `CountryLocator`.
- `world.geojson`: Offline geographical data of country boundaries.

## How to Run
1. Ensure `world.geojson` is in the root directory.
2. Compile and run `CountryLocator.java`.
3. Run `CountryLocatorTest.java` for unit testing.
4. Run `PerformanceTest.java` for performance testing.

