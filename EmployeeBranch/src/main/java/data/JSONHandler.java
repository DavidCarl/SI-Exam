package data;

import entities.Car;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

public class JSONHandler {

    public void readJson(List<Car> cars) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(
                    System.getProperty("user.dir") + "/src/main/resources/cars.json"));
            JSONArray jsonArray = (JSONArray) obj;
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject next = iterator.next();
                System.out.println(next);
                cars.add(new Car((String)next.get("brand"),(String)next.get("model"),(String)next.get("description")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}