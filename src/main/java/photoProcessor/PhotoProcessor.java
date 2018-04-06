package photoProcessor;

public class PhotoProcessor {

    public static void main(String[] args) {

        String path2 = "D:\\app\\Tests\\test.jpg";

        Editor editor = new Editor(path2);

        editor.edit();
    }
}
