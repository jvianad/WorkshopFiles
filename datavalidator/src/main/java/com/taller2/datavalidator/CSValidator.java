package com.taller2.datavalidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSValidator {

    public static String validators(String route){
        //Regex
        String regex = "^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,6})?$";
        int validCounter = 0;
        int invalidCounter = 0;
        //La clase pattern se encarga de compilar el regex del string y juntarlo
        Pattern pattern = Pattern.compile(regex);
        String linea;
        //El set almacena los tipos de trabajo validos
        Set<String> validJobTitles = new HashSet<>();
        validJobTitles.add("Haematologist");
        validJobTitles.add("Phytotherapist");
        validJobTitles.add("Building surveyor");
        validJobTitles.add("Insurance account manager");
        validJobTitles.add("Educational psychologist");

        //El try se encarga de leer el archivo de ruta y si no lee nada pues da error
        try (BufferedReader reader = new BufferedReader(new FileReader(route))) {
            //Revisa que la linea que toma del CSV no sea nula
            while ((linea = reader.readLine()) != null) {
                //Separa los datos que incluyen "," haciendo que se separe por persona en este caso.
                String[] values = linea.split(",");

                //Se encarga de revisar si el values[5](Correo) cumple con el pattern que hay en el regex
                Matcher matcher = pattern.matcher(values[5]);

                //En este caso el try es para revisar que las fechas sean válidas
                try {
                    //Hace parse de las fechas para pasarlas de string a Date
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(values[7]);
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-01");

                    //Toma el valor de cada linea en el indice 8 para tomar el trabajo que aparece en el CSV
                    String jobTitle = values[8];

                    //Validaciones (Si encuentra match, si contiene el valor del titulo y se compara la fecha)
                    if (matcher.find() && validJobTitles.contains(jobTitle) && date2.before(date1)) {
                        //Se le añade a listOfData la lista de valores validos.
                        validCounter++;
                    } else {
                        invalidCounter++;
                    }

                } catch (ParseException e) {
                    System.err.println("Error al analizar la fecha en la línea: " + linea);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Retorna la lista
        return "Archivos válidos " + validCounter + " Archivos inválidos " + invalidCounter;
    }
}
