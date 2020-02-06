/*******************************************************************************
 * Copyright 2020 Raviteja Chowdari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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
