package rpc;

import Filtered.ExchangeSender;
import entities.Car;
import publishSubscribe.EmitLog;

import java.util.List;

public class MessageHandler {
    static EmitLog el = new EmitLog();
    static ExchangeSender es = new ExchangeSender();

    private static String categories(List<Car> cars) {
        el.sendLog("Getting car brands");
        String message = "";
        for (Car car : cars) {
            if (!message.contains(car.getBrand())) {
                message += car.getBrand() + "\n";
            }

        }
        return message;
    }

    private static String pickCarBrand(String model, List<Car> cars) {
        el.sendLog("Picking car brand " + model);
        String carList = "";
        for (Car car : cars) {
            if (car.getBrand().equals(model)) {
                carList += car.getModel() + "\n";
            }
        }
        if (!carList.isEmpty()) {
            carList += "please pick one of the cars";
        }
        return carList;
    }

    private static String chooseModel(String carChoice, List<Car> cars) {
        es.message("employ_app", "A customer looked on " + carChoice);
        el.sendLog("Choosing brand model " + carChoice);
        String carMessage = "";
        for (Car car : cars) {
            if (car.getModel().equals(carChoice)) {
                carMessage += car + "\nPlease pick a brand or model again!";
            }
        }
        return carMessage;
    }

    public String handleMessages(String message, List<Car> cars) {
        System.out.println("handler: " + message);
        String response = "";
        if(message.toLowerCase().contains("getbrands")){
            response = categories(cars);
        }else if(message.toLowerCase().contains("brand:")){
            response = pickCarBrand(message.split(":")[1].replace(" ", ""), cars);
        }else if(message.toLowerCase().contains("model:")){
            response = chooseModel(message.split(":")[1].replace(" ", ""), cars);
        }else{
            el.sendLog("Help message sent!");
            response = "Im sorry, I didnt understand your request. Please type help";
        }
        return response;
    }
}