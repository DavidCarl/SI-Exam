package data;

import com.opencsv.CSVReader;
import entities.Car;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVHandler {

    public void readCsv(List<Car> cars) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(System.getProperty("user.dir") + "/src/main/resources/cars.csv"));
        List<String[]> myEntries = reader.readAll();
        boolean firstline = true;
        for(String[] s : myEntries){
            if(firstline){
                firstline = false;
                continue;
            }
            cars.add(new Car(s[1],s[2],s[3]));
        }
    }
}
