import rt.resumeBuilderApp.entities.Resume;
import rt.resumeBuilderApp.service.DocGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raviteja  on 12-01-2020.
 */
public class TestDocGenerator {

    public static void main(String args[]){

        final String CURRENT_DIR = System.getProperty("user.dir");

        String templatePath = CURRENT_DIR+"\\templates\\default.docx";
        String outPath =CURRENT_DIR+"\\output\\resume_out.docx";

        try {
            Resume res = TestResumeBuilder.build();
            DocGenerator generator = new DocGenerator(templatePath,outPath,res);
            generator.generateResume();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
