package photoProcessor;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Editor {

    private static BufferedImage imgIn;
    private static Graphics2D graph;
    private static AffineTransform tx = new AffineTransform();

    Editor() {
    }

    public static void edit(File f) {

        try {

//            reading files
            imgIn = ImageIO.read(f);
            System.out.println("Reading complete.");

//            set patter
            int pattern = imgIn.getWidth()>imgIn.getHeight()?imgIn.getWidth():imgIn.getHeight();//resolve pattern

//            drawing
            BufferedImage imgOut = new BufferedImage(pattern, pattern, BufferedImage.TYPE_3BYTE_BGR); //create background
            graph = imgOut.createGraphics();
            graph.setPaint(new Color(255, 255, 255));//background color (here - white)
            graph.fillRect(0, 0, pattern, pattern);//fill background with color^^
            graph.drawImage(imgIn, null, findPoint(pattern, imgIn.getWidth()), findPoint(pattern, imgIn.getHeight()));
            imgOut = toRotate(imgOut, f);// check for rotation

//            Saving file
            ImageIO.write(imgOut, "jpg", new File(f.getParent()+"\\new_"+f.getName()));
            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } catch (JpegProcessingException | MetadataException e) {
            e.printStackTrace();
        }

    }

//      Check rotating need
    private static BufferedImage toRotate(BufferedImage img, File file) throws IOException, JpegProcessingException, MetadataException {
        Metadata metadata = JpegMetadataReader.readMetadata(file);
        int or = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class).getInt(ExifIFD0Directory.TAG_ORIENTATION);
        if (or != 8) {
            return img;
        }
        return rotateImage(img, tx);
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

    public static BufferedImage getImgIn() {
        return imgIn;
    }

    public static void setImgIn(BufferedImage imgIn) {
        Editor.imgIn = imgIn;
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
}
