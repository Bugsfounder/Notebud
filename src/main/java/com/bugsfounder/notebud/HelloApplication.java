package com.bugsfounder.notebud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(loader.load(), 350, 300);

        NoteController controller = loader.getController();
        controller.setStage(stage); // pass stage

        stage.setTitle("NoteBud");
        stage.setScene(scene);
        stage.show();
    }


}

