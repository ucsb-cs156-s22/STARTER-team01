package edu.ucsb.cs156.spring.backenddemo.services;

import java.util.Map;


import org.springframework.web.reactive.function.client.WebClient;


import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
public class EarthquakeQueryService {

    public static final String ENDPOINT = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    public static final String PARAMS = "?format=geojson&minmagnitude={minMag}&maxradiuskm={distance}&latitude={latitude}&longitude={longitude}";

    private String url;

    public EarthquakeQueryService() {
        this(ENDPOINT);
    }

    public EarthquakeQueryService(String url) {
        this.url = url + PARAMS;
    }

    public String getJSON(String distance, String minMag) throws HttpClientErrorException {
        log.info("distance={}, minMag={}", distance, minMag);

      
        String ucsbLat = "34.4140"; // hard coded params for Storke Tower
        String ucsbLong = "-119.8489";
        Map<String, String> uriVariables = Map.of("minMag", minMag, "distance", distance, "latitude", ucsbLat,
                "longitude", ucsbLong);


        final WebClient webClient = WebClient.builder()
                .baseUrl(this.url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(uriVariables)
                .build();
    
        WebClient.ResponseSpec rs =  webClient.get().retrieve();
        String body = rs.bodyToMono(String.class).block();
        return body;
    }

}