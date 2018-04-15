package photoProcessor;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class GUI extends Application {
    private Label text;

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Set title for the GUI
        primaryStage.setTitle("Photo Processor");

//        Create FlowRoot with gaps of 10px
        FlowPane nodeRoot = new FlowPane(10, 10);

//        Set the alignment
        nodeRoot.setAlignment(Pos.CENTER);

//        Set FlowRoot on the scene
        Scene myScene = new Scene(nodeRoot, 600, 600);

//        Set scene on the stage
        primaryStage.setScene(myScene);

//        Set some text for now
        text = new Label("This will be the GUI for Photo processor");

//        add graphs to the node
        nodeRoot.getChildren().addAll(text);

//        Show the stage
        primaryStage.show();
    }
}
