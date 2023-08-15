package com.taller2.datavalidator;

public class DataReaders {

    public static String readCSV(String route)  {
      return CSValidator.validators(route);
    }

    //XSLX Validator
    public static  String readXSLX(String route) {
        return XLSXValidator.validateXSLX(route);
    }


}
