package photoProcessor;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EditorProcessor {

    EditorProcessor() {
    }

    public void edit(File file) {

        String directory = createDir(file) + "\\Processed";

        if (file.isDirectory()) {
        List<File> files = Arrays.asList(file.listFiles());
        files.forEach(Editor::edit);
        } else {
            Editor.edit(file);
        }
    }

    private String createDir(File file) {

        if (file.getName().contains(".")) {
            return file.getParent();
        } else {
            return file.getPath();
        }
    }
}
