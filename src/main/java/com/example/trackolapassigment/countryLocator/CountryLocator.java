package com.example.trackolapassigment.countryLocator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.wololo.jts2geojson.GeoJSONReader;

public class CountryLocator {
    private List<Country> countries;

    public CountryLocator(String geoJsonFilePath) throws IOException {
        String geoJson = new String(Files.readAllBytes(Paths.get(geoJsonFilePath)));
        JSONObject json = new JSONObject(geoJson);
        JSONArray features = json.getJSONArray("features");
        countries = new ArrayList<>();

        for (int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);
            String countryCode = feature.getJSONObject("properties").getString("ISO_A2");
            String geometry = feature.getJSONObject("geometry").toString();
            countries.add(new Country(countryCode, geometry));
        }
    }

    public String getCountryCode(double latitude, double longitude) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

        for (Country country : countries) {
            if (country.contains(point)) {
                return country.getCode();
            }
        }
        return "Unknown";
    }

    private class Country {
        private String code;
        private Geometry boundary;

        public Country(String code, String geoJson) throws IOException {
            this.code = code;
            GeoJSONReader reader = new GeoJSONReader();
            this.boundary = reader.read(geoJson);
        }

        public String getCode() {
            return code;
        }

        public boolean contains(Point point) {
            return boundary.contains(point);
        }
    }
}

