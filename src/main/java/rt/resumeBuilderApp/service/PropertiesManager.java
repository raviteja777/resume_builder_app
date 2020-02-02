package rt.resumeBuilderApp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Raviteja  on 01-02-2020.
 */
public class PropertiesManager {

    public static final String CONFIG_PATH = "src/main/resources/properties/config.json";
    public static final String SCENES_PROPERTIES = "scenes_properties";
    private static  Map<String,String> SCENE_MAP  ;
    private static  Map<String,String> CONFIG_MAP ;

    private PropertiesManager(){
    }

    private static Map<String,String> initSettings(String path,TypeReference<? extends Map<String,String>> ref) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] jsonData= Files.readAllBytes(Paths.get(path));
        return objectMapper.readValue(jsonData,ref);
    }

    public static String getConfigProperty(String propertyName){
        return CONFIG_MAP.get(propertyName);
    }

    public static Map<String,String> getConfigMap(){
        return Collections.unmodifiableMap(CONFIG_MAP);
    }

    public static Map<String,String> getSceneMap(){
        return Collections.unmodifiableMap(SCENE_MAP);
    }

    public static void initSettings() throws IOException{
        //load CONFIG as HashMap as key, val pair
        if(CONFIG_MAP==null || CONFIG_MAP.size()==0) {
            CONFIG_MAP = initSettings(CONFIG_PATH, new TypeReference<HashMap<String, String>>() {
            });
        }
        //load scenes in LinkedHashMap (order of the scenes need to be maintained)
        if(SCENE_MAP==null || SCENE_MAP.size()==0) {
            SCENE_MAP = initSettings(CONFIG_MAP.get(SCENES_PROPERTIES), new TypeReference<LinkedHashMap<String, String>>() {
            });
        }
        CONFIG_MAP.forEach((x,y)->System.out.println(x+" "+y));
        SCENE_MAP.forEach((x,y)->System.out.println(x+" "+y));
    }


}
