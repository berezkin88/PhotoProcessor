package photoProcessor;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintStream;

public class GUI extends Application {
    private Label text, directory;
    private TextField textField;
    private TextArea textArea;
    private EditorProcessor ep = new EditorProcessor();

    private Button process = new Button("Process");

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Set title for the GUI
        primaryStage.setTitle("Photo Processor");

//        Create FlowRoot with gaps of 10px
        FlowPane nodeRoot = new FlowPane(20, 20);

//        Set the alignment
        nodeRoot.setAlignment(Pos.CENTER);

//        Set FlowRoot on the scene
        Scene myScene = new Scene(nodeRoot, 300, 350);

//        Set scene on the stage
        primaryStage.setScene(myScene);

//        Set some text for now
        text = new Label("This will be the GUI for Photo processor");
        directory = new Label("Point the directory with photos:");

//        Create text area
        textArea = new TextArea();
        textArea.setPrefSize(250.0, 200.0);

//        Set the output console stream to the text area
        PrintStream ps = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(ps);
        System.setErr(ps);

//        Creating text input field
        textField = new TextField ();
        textField.setPromptText("Select the directory");
        textField.setMinWidth(100.0);

//        Setting button
        process.setOnAction((ae) -> {
//            start editing photos
            ep.edit(new File(textField.getText()));
//            response.setText();
        });

//        add graphs to the node
        nodeRoot.getChildren().addAll(text, directory, textField, process, textArea);

//        Show the stage
        primaryStage.show();
    }
}
