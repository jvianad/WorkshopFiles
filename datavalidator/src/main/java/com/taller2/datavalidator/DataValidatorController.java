package com.taller2.datavalidator;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class DataValidatorController {
    DataValidatorService service = new DataValidatorService();

    @PostMapping("/validate-data")
    public String almacenarRuta(@RequestBody() String newRoute) {
        return service.validateData(newRoute);
    }
}
