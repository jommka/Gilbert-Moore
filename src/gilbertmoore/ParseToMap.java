package gilbertmoore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParseToMap {
    private static final BufferedReader reader = getReader();
    private static Map<String, Double> map = new LinkedHashMap<>();

    public static Map<String, Double> getMap(){
        parseMap();
        return map;
    }

    public void setMap(Map<String, Double> map) {
        ParseToMap.map = map;
    }

    private static BufferedReader getReader(){
        try {
            return new BufferedReader(new FileReader("D:\\Java\\Теория информации\\task_6\\src\\file\\input_2.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void parseMap(){
        try {
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(":", 2);
                if (parts.length >= 2)
                {
                    String key = parts[0];
                    String value = parts[1];
                    map.put(key, Double.valueOf(value));
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print(){
        for (String s: map.keySet()){
            System.out.println(s + ": " + map.get(s));
        }
    }

}
