package rt.resumeBuilderApp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import rt.resumeBuilderApp.entities.Resume;
import rt.resumeBuilderApp.service.DocGenerator;
import rt.resumeBuilderApp.service.ResumeBuilder;
import rt.resumeBuilderApp.service.PropertiesManager;
import rt.resumeBuilderApp.ui.UILoader;

import java.awt.*;
import java.io.*;
import java.util.Map;


/**
 * Created by Raviteja  on 19-01-2020.
 */
public class ResumeBuilderController extends AbstractController {

    @FXML private TextField uploadFilePath ;
    @FXML private TextField templateSelectorPath;
    @FXML private Button submitBtn ;
    @FXML private Label message;
    private Map<String,String> scenes;


    @FXML
    public void browseJSONFile(){
        browseFile(uploadFilePath);
    }

    @FXML
    public void browseTemplateFile(){
        browseFile(templateSelectorPath);
    }

    @FXML
    private void browseFile(TextField tf){
        message.setText("----");
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Browse File");
        File file = chooser.showOpenDialog(tf.getParent().getScene().getWindow());
        if(file != null && !file.isDirectory()){
            //uploadFile = file;
            tf.setText(file.getPath());
        }
        if(uploadFilePath.getText().length()>0 && templateSelectorPath.getText().length()>0){
            submitBtn.setDisable(false);
        }
    }

    @FXML
    public void createResumeDoc()  {
        message.setText("----");
        if(uploadFilePath.getText().trim().length()>0 && templateSelectorPath.getText().trim().length()>0){
            ResumeBuilder builder = new ResumeBuilder(uploadFilePath.getText());
            try {
                Resume resume = builder.buildResume();
                String outPath = fileSaveDialog(new FileChooser.ExtensionFilter("Microsoft Word XML Document (*.docx)", "*.docx"));
                if(outPath!=null && outPath.trim().length()>0) {
                    DocGenerator generator = new DocGenerator(templateSelectorPath.getText(), outPath, resume);
                    if (generator != null) {
                        generator.generateResume();
                        message.setText("Resume generated at " + outPath);
                    } else {
                        message.setText("Unable to generate Resume. One or more file formats may be missing or of incorrect Format .Try again");
                    }
                }
            }catch(Exception ex){
                message.setText(ex.getStackTrace()[0]+" -- Error Occurred while creating document. Check input parameters ");
            }
        }else{
            message.setText("Unable to fetch file .Please check the file path");
        }
    }

    public String fileSaveDialog(FileChooser.ExtensionFilter extension){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(extension);
        File file = chooser.showSaveDialog(submitBtn.getParent().getScene().getWindow());
        if(file!=null){
            return file.getPath();
        }else{
            return "";
        }
    }

    @FXML
    public void downloadJson(){
        String outFilePath = fileSaveDialog(new FileChooser.ExtensionFilter("JSON file(*.json)","*.json"));

        if(outFilePath!=null && outFilePath.trim().length()>0) {
            File inputFile = new File(PropertiesManager.getConfigProperty("input_template"));
            File outputFile = new File(outFilePath);
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

                String line;
                while (br != null && (line = br.readLine()) != null) {
                    bw.write(line + "\n");
                }
                if (outputFile != null) {
                    message.setText("JSON file downloaded to " + outFilePath);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                message.setText("I/O Error occurred while copying JSON. Try again");
            }
        }

    }

    @FXML
    public void openTemplateFolder(){
        Desktop desktop = Desktop.getDesktop();
        File templateFolder = new File(PropertiesManager.getConfigProperty("docx_templates_path"));
        try {
            desktop.open(templateFolder);
        } catch (IOException e) {
            e.printStackTrace();
            message.setText(e.getStackTrace()[0]+" -- Error opening templates folder");
        }

    }
    @FXML
    public void resetForm(){
        message.setText("----");
        uploadFilePath.setText("");
        initialize();
        submitBtn.setDisable(true);
    }


    @FXML
    public void initialize() {
        File file = new File(PropertiesManager.getConfigProperty("docx_templates_path")+"/default.docx");
        templateSelectorPath.setText(file.getAbsolutePath());
        scenes = PropertiesManager.getSceneMap();
    }


    @FXML
    public void viewHelp()  {
        try {
            loadPopUp(scenes.get("help"),"Help");
        } catch (IOException e) {
            e.printStackTrace();
            message.setText(String.valueOf(e.getStackTrace()[0]));
        }
    }

    @FXML
    public void viewAbout(){
        try {
            loadPopUp(scenes.get("about"),"About");
        } catch (IOException e) {
            e.printStackTrace();
            message.setText(String.valueOf(e.getStackTrace()[0]));
        }

    }

    public void loadPopUp(String view,String title) throws IOException {
        System.out.println(view);
        Scene scene =new UILoader().initScene(view);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
