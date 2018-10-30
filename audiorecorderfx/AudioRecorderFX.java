/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiorecorderfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author edwar
 */
public class AudioRecorderFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        AudioRecorder ar = new AudioRecorder();
        
        Button recBtn = new Button();
        recBtn.setText("RECORD");
        
        Button stopBtn = new Button();
        stopBtn.setText("STOP");
        
        recBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                ar.record();
                System.out.println("Recording Started!");
            }
        });
        
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                ar.stop();
                System.out.println("Recording Stopped!");
            }
        });
        
        FlowPane root = new FlowPane();
        root.getChildren().add(recBtn);
        root.getChildren().add(stopBtn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("AudioRecorder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
