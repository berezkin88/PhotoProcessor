package photoProcessor;

import java.io.File;

public class PhotoProcessor {

    public static void main(String[] args) {

        File path2 = new File("D:\\app\\Tests\\TestDirectory");

        EditorProcessor ep = new EditorProcessor();

        ep.edit(path2);
    }
}
