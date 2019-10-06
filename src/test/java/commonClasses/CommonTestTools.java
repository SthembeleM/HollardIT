/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonClasses;

/**
 *
 * @author f3307530
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonTestTools {

    public CommonTestTools(){};
    

    public static Properties getPropInstance(String propertiesFilePath)throws IOException {

        InputStream input = null;
        Properties prop = new Properties();

        try {
            input = new FileInputStream(new File(propertiesFilePath));
            prop.load(input);
        } catch (Exception e){
            throw new IOException();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new IOException();
                }
            }
        }
        return prop;
    }

    //HHmmss
    public static String time(String timeFormat){

        DateFormat df = new SimpleDateFormat(timeFormat);
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MINUTE, 5);
        return df.format(today.getTime());
    }

    public static String randDouble(Double min, Double max) {
        Random rand = new Random();
        Double range = max - min;
        Double randomNum = (rand.nextDouble()*range)+min;
        String randomDouble = Double.toString(randomNum);
        randomDouble = randomDouble.replace(".",",");
        int position = randomDouble.lastIndexOf(",");
        randomDouble = randomDouble.substring(0,position+3);
        return randomDouble;
    }

    public static Date changeDate(int days){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        date.setTime(cal.getTimeInMillis());
        return date;
    }

     public static String formatDate(Date date, String format){

        DateFormat dateFormat = new SimpleDateFormat(format);
        return  dateFormat.format(date);
    }

    public static String getValueDate(String myFormat){

        String date = null;
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        date = sdf.format(dt);
        return date;
    }


    public char randomCharacter()throws Exception {
        char randChar='A';
        int num = randInt(65, 90);
        randChar = (char)num;
        return randChar;
    }



    public static String GenerateRandomNumber(int intNumber, String tag, String dateFormat){

        String RandomNumber,hold = null;
        //"MMddHHmmss"
        if (intNumber <10){
            hold = "00"+ String.valueOf(intNumber);
        }else if (intNumber > 9 && intNumber < 100)
            hold = "0"+ String.valueOf(intNumber);
        else if (intNumber > 99 && intNumber < 1000)
            hold = String.valueOf(intNumber);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        RandomNumber = sdf.format(date);
        RandomNumber = String.valueOf(RandomNumber) + hold;
        RandomNumber = tag + RandomNumber;

        if (RandomNumber.length() > 16)
            RandomNumber = RandomNumber.substring(0,16);
        return RandomNumber;
    }

    public static int randInt(int min, int max)throws Exception{
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static String getCellValue(String[][] data,int row, String columnName){
        try{
            int columnIndex =  Arrays.asList(data[0]).indexOf(columnName);
            return data[row][columnIndex].trim();
        }catch(Exception outOfBounds){
            return "";
        }
    }

    public static String getCellValue(String[] headers, String[] data, String columnName){
        try{
            return data[Arrays.asList(headers).indexOf(columnName)].trim();
        }catch(Exception outOfBounds){
            return "";
        }
    }

    public static int getColumnIndex(String[] headers, String columnName){
        try{
            return Arrays.asList(headers).indexOf(columnName);
        }catch(Exception outOfBounds){
            return -1;
        }
    }

}
