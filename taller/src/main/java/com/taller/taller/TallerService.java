package com.taller.taller;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;



public class TallerService {

    public String identifyDocument(String route) throws FileNotFoundException {
        String files = "";
        if (route.contains(".csv") || route.contains(".xlsx")) {
            Route newRoute = new Route(route);
            String dataTest = newRoute.getRoute();

            ResponseEntity<String> response =
                    new RestTemplate().postForEntity("http://localhost:8090/validate-data", dataTest,
                            String.class);

            return response.getBody();
        }else{
            files = "Type of data not supported";
        }
        return files;
    }

}




