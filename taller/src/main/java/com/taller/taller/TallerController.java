package com.taller.taller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class TallerController {

    RestTemplate template = new RestTemplate();
    TallerService service = new TallerService();

    @PostMapping("/leer-data")
    //En este caso se debe hacer así ya que al enviarlo en este caso específico ya que a diferencia de el ejemplo
    //Pasado este no tiene forma de encontrar el KeyValue automaticamente como si el RequestBody fuese un objeto
    //(Por ejemplo) public Optional<Persona> ponerPersona(@RequestBody() Persona persona)
    //Por esto mismo debemos hacer el Map aquí, ya que al hacer la petición se envía como key
    //Route y se recibe como value el URL (La petición es esta: "route": "C:/Users/jupag/Desktop/taller/people.csv").

    public String sendFileURL(@RequestBody Map<String, String> body) throws IOException {
        String data = body.get("route");
        return service.identifyDocument(data);
    }



}
