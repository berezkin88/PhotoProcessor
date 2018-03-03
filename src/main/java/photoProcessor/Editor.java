package photoProcessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Editor {

    private static int wh = 960;
    private static BufferedImage im1, im3;
    private static File f, f1, f2;
    private static Graphics2D graph;
    private static AffineTransform tx = new AffineTransform();
    private String pathCanvas;
    private String pathObject;
    private String pathCreator;

    Editor(String pathCanvas, String pathObject, String pathCreator) {
        this.pathCanvas = pathCanvas;
        this.pathObject = pathObject;
        this.pathCreator = pathCreator;
    }

    public void edit() {
        f = new File(pathCanvas);
        f1 = new File(pathCreator);
        f2 = new File(pathObject);

        try {

//            reading files
            im1 = ImageIO.read(f);
            im3 = ImageIO.read(f2);
            System.out.println("Reading complete.");

//            drawing
            graph = im1.createGraphics();
            graph.drawImage(im3, null, findPoint(wh, im3.getWidth()), findPoint(wh, im3.getHeight()));
            im1 = rotateImage(im1, tx);

//            Saving file
            ImageIO.write(im1, "jpg", f1);
            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

    }

    //    Rotating image
    private static BufferedImage rotateImage(BufferedImage bufferedImage, AffineTransform tx) {
        tx.rotate(Math.toRadians(-90), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx,
                null);
        bufferedImage = op.filter(bufferedImage, null);
        return bufferedImage;
    }

    //    Finding start drawing point
    private static int findPoint(int a, int b) {
        return (a - b) / 2;
    }

//    getters and setters

    public static BufferedImage getIm1() {
        return im1;
    }

    public static void setIm1(BufferedImage im1) {
        Editor.im1 = im1;
    }

    public static BufferedImage getIm3() {
        return im3;
    }

    public static void setIm3(BufferedImage im3) {
        Editor.im3 = im3;
    }

    public static File getF() {
        return f;
    }

    public static void setF(File f) {
        Editor.f = f;
    }

    public static File getF1() {
        return f1;
    }

    public static void setF1(File f1) {
        Editor.f1 = f1;
    }

    public static File getF2() {
        return f2;
    }

    public static void setF2(File f2) {
        Editor.f2 = f2;
    }

    public static Graphics2D getGraph() {
        return graph;
    }

    public static void setGraph(Graphics2D graph) {
        Editor.graph = graph;
    }

    public static AffineTransform getTx() {
        return tx;
    }

    public static void setTx(AffineTransform tx) {
        Editor.tx = tx;
    }

    public String getPathCanvas() {
        return pathCanvas;
    }

    public void setPathCanvas(String pathCanvas) {
        this.pathCanvas = pathCanvas;
    }

    public String getPathObject() {
        return pathObject;
    }

    public void setPathObject(String pathObject) {
        this.pathObject = pathObject;
    }

    public String getPathCreator() {
        return pathCreator;
    }

    public void setPathCreator(String pathCreator) {
        this.pathCreator = pathCreator;
    }
}
