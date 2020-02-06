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

package rt.resumeBuilderApp.controller;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import rt.resumeBuilderApp.service.PropertiesManager;
import rt.resumeBuilderApp.ui.UILoader;

/**
 * Created by Raviteja  on 02-02-2020.
 */
public class AboutController extends AbstractController {

    @FXML
    public void openGithubLink(){
        String path = "https://github.com/raviteja777/resume_builder_app";
        HostServicesDelegate hostServices = HostServicesFactory.getInstance(new UILoader());
        hostServices.showDocument(path);
    }
}
