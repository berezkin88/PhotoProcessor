package photoProcessor;

public class PhotoProcessor {

    public static void main(String[] args) {
        String path1 = "D:\\app\\Tests\\Background.jpg";
        String path2 = "D:\\app\\Tests\\test.jpg";
        String path3 = "D:\\app\\Tests\\output.jpg";

        Editor editor = new Editor(path1, path2, path3);

        editor.edit();
    }
}
