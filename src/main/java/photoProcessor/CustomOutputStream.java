package photoProcessor;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;

public class CustomOutputStream extends OutputStream {

    private TextArea textArea;

    public CustomOutputStream(TextArea textArea) {
        this.textArea = textArea;
    }

//    redirecting output thread to the text area
    public void appendText(String valueOf) {
        Platform.runLater(() -> textArea.appendText(valueOf));
    }

    public void write(int b) throws IOException {
        appendText(String.valueOf((char)b));
    }
}
