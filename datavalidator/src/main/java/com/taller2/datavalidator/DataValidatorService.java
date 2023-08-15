package com.taller2.datavalidator;

public class DataValidatorService {

    public String validateData(String route){
        String file = "";
        if(route.contains(".csv")){
           file = DataReaders.readCSV(route);
        } else if (route.contains(".xlsx")) {
            file = DataReaders.readXSLX(route);
        }
        return file;
    }

}
