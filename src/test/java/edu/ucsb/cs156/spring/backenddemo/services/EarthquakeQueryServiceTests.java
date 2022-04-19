package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;

import org.apache.commons.collections.functors.EqualPredicate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

@RestClientTest(EarthquakeQueryService.class)
public class EarthquakeQueryServiceTests {

    private MockWebServer server;

    private EarthquakeQueryService earthquakeQueryService;
    // @Autowired
    // private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    void setUp() throws IOException {
        this.server = new MockWebServer();
        this.server.start();
        String rootUrl = this.server.url("/").toString();
        this.earthquakeQueryService = new EarthquakeQueryService(rootUrl);
    }

    @AfterEach
    void tearDown() throws IOException {
        this.server.shutdown();
    }

    @Test
    public void test_getJSON() {

        String distance = "10";
        String minMag = "1.5";
      
        String fakeJsonResult = "{ \"fake\" : \"result\" }";

        this.server.enqueue(new MockResponse()
                .setBody(fakeJsonResult)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json"));

        String actualResult = earthquakeQueryService.getJSON(distance, minMag);
        assertEquals(fakeJsonResult, actualResult);
    }
}
