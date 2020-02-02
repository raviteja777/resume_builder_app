package rt.resumeBuilderApp.service;

/**
 * Created by Raviteja  on 11-01-2020.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import rt.resumeBuilderApp.entities.Resume;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 * Created by Raviteja on 06-01-2020.
 * reads a json file from the console
 * parse it create a Resume object
 */
public class ResumeBuilder {

    private String filePath ;

    public ResumeBuilder(String filePath){
        this.filePath = filePath;
    }

    public Resume buildResume() throws Exception{

        Resume resume=null;

        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
            ObjectMapper objectMapper = new ObjectMapper();
            resume = objectMapper.readValue(jsonData,Resume.class);
            System.out.println(resume);

        }catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error while parsing Json, please check if JSON format is correct");
        }

        return resume;

    }


}
