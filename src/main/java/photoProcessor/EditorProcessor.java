package photoProcessor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EditorProcessor {

    EditorProcessor() {
    }

    public void edit(File file) {

//        creating a new directory for storing files
        String directory = createDir(file) + "\\Processed";

//        Check if the file is directory
        if (file.isDirectory()) {
            List<File> files = Arrays.asList(file.listFiles());
//            Throw the IOException if the directory is empty, if not - call edit()
            try {
                if (files.isEmpty()) {
                    throw new IOException();
                } else {
                    File newDir = new File(directory);
//                    creating directory if needed
                    System.out.println("New directory created " + newDir.mkdir() + "\n");
                    files.forEach((f) -> {
                        Editor.edit(f, directory);
                    });
                }
            } catch (IOException e) {
                System.out.println("Oops... the directory is empty");
            }
//            call edit() if file is a single file
        } else {
            Editor.edit(file, directory);
        }

        System.out.println("\nEditing complete");
    }

    //    Creating new directory
    private String createDir(File file) {

//        checking if the file is a single file or not
        if (file.getName().contains(".")) {
            return file.getParent();
        } else {
            return file.getPath();
        }
    }
}
