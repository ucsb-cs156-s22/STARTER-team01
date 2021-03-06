package edu.ucsb.cs156.spring.backenddemo.services;



import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
public class TidesQueryService {


    private final RestTemplate restTemplate;

    public TidesQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    // Documentation for endpoint is at: https://api.tidesandcurrents.noaa.gov/api/prod/
    public static final String ENDPOINT = "";

    public String getJSON(String beginDate, String endDate, String station) throws HttpClientErrorException {
        return "";
    }
}