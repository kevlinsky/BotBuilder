package ru.kpfu.kevlinsky.main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public String city;
    public Double temperature;
    public Double humidity;
    public String main;


    public String getWeather(String message) throws IOException{
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=ccda8d17fb4441b3435000bf04b7069c");
        Scanner in = new Scanner((InputStream) url.getContent());
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.nextLine());
        }
        JSONObject object = new JSONObject(sb.toString());
        this.city = object.getString("name");

        JSONObject main = object.getJSONObject("main");
        this.temperature = main.getDouble("temp");
        this.humidity = main.getDouble("humidity");

        JSONArray weather = object.getJSONArray("weather");
        for (int i = 0; i < weather.length(); i++) {
            JSONObject obj = weather.getJSONObject(i);
            this.main = obj.get("main").toString();
        }

        return "City: " + this.city + "\n" +
               "Main: " + this.main + "\n" +
               "Temperature: " + this.temperature + " Celsius" + "\n" +
               "Humidity: " + this.humidity + "%" + "\n";
    }
}
