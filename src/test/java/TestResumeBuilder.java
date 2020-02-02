/**
 * Created by Raviteja  on 11-01-2020.
 */

import rt.resumeBuilderApp.entities.Resume;
import rt.resumeBuilderApp.service.ResumeBuilder;

public class TestResumeBuilder {

    static String filePath = "src\\main\\resources\\files\\input_resume.json";

    public static Resume build(){
        try {
            ResumeBuilder res = new ResumeBuilder(filePath);
            return res.buildResume();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]){
        build();
    }
}
