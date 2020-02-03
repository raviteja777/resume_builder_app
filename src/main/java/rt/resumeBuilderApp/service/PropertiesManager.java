package rt.resumeBuilderApp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
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

    public static final String CONFIG_PATH = "/properties/config.json";
    public static final String SCENES_PROPERTIES = "scenes_properties";
    private Map<String,String> SCENE_MAP  ;
    private Map<String,String> CONFIG_MAP ;
    private static PropertiesManager manager;

    private PropertiesManager(){
    }

    private static Map<String,String> initSettings(String path,TypeReference<? extends Map<String,String>> ref) throws IOException, URISyntaxException{
        path = Paths.get(manager.getClass().getResource(path).toURI()).toString();
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] jsonData= Files.readAllBytes(Paths.get(path));
        return objectMapper.readValue(jsonData,ref);
    }

    public static String getConfigProperty(String propertyName){
        return  manager.getClass().getResource(manager.CONFIG_MAP.get(propertyName)).getPath();
    }

    public static Map<String,String> getConfigMap(){
        return Collections.unmodifiableMap(manager.CONFIG_MAP);
    }

    public static Map<String,String> getSceneMap(){
        return Collections.unmodifiableMap(manager.SCENE_MAP);
    }

    public static void initSettings() throws Exception{

        if(manager==null){
            manager = new PropertiesManager();
            if(manager.CONFIG_MAP==null || manager.CONFIG_MAP.size()==0) {
                manager.CONFIG_MAP = initSettings(CONFIG_PATH, new TypeReference<HashMap<String, String>>() {
                });
            }
            //load scenes in LinkedHashMap (order of the scenes need to be maintained)
            if(manager.SCENE_MAP==null || manager.SCENE_MAP.size()==0) {
                manager.SCENE_MAP = initSettings(manager.CONFIG_MAP.get(SCENES_PROPERTIES), new TypeReference<LinkedHashMap<String, String>>() {
                });
            }
        }

        //load CONFIG as HashMap as key, val pair
        manager.CONFIG_MAP.forEach((x,y)->System.out.println(x+" "+y));
        manager.SCENE_MAP.forEach((x,y)->System.out.println(x+" "+y));


    }


}
