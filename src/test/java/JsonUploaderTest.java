import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Raviteja  on 21-01-2020.
 */
public class JsonUploaderTest extends Application {

    public static void main(String args[]){
        launch(args);
    }
    public void start(Stage stage) throws IOException {

        String path = "src/main/resources/fxml/resumeBuilder.fxml";
        URL url = getClass().getClassLoader().getResource(path);
        System.out.println(path);
        Parent root =  FXMLLoader.load(url);
        //ResumeBuilderController controller = root.getController();
        Scene scene = new Scene(root);
        stage.setTitle("RT resume maker");
        stage.setScene(scene);
        stage.show();

    }
}
