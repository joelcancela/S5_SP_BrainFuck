package unice.polytech.polystirN.brainfuck.interpreter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Class used to read translate text into images
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class ImageWriter {
    private BufferedImage img;
    private BufferedReader buffer;
    private String filename;
    private final int pixelSize = 3;


    public ImageWriter(String filename) throws FileNotFoundException {
        this.filename = filename;
        buffer = new BufferedReader(new FileReader(filename));
    }

    public void translate() throws IOException {
        InstructionFactory factory = new InstructionFactory();
        int instructionsNumber = 0;
        while (buffer.read() != -1) {
            instructionsNumber++;
        }
        buffer.close();
        buffer = new BufferedReader(new FileReader(filename));
        int pictureWidthSquares = 0;
        while ((pictureWidthSquares * pictureWidthSquares) < (instructionsNumber)) {
            pictureWidthSquares++;
        }
        int pictureWidth = pictureWidthSquares * pixelSize;
        img = new BufferedImage(pictureWidth,pictureWidth,TYPE_INT_RGB);
        //TODO Long syntax
        int c;
        int x = 0;
        int y = 0;
        while ((c = buffer.read()) != -1) {
            int col = factory.getColor(Character.toString((char) c));
            fillPixelSquare(x, y, col);
            if (x + pixelSize == pictureWidth) {
                x = 0;
                y += pixelSize;
            }
            else{
                x+=pixelSize;
            }

        }

        ImageIO.write(img, "BMP", new File("outputTranslate.bmp"));
    }

    private void fillPixelSquare(int x, int y, int color) {
        for (int yAxis = y; yAxis < y + (pixelSize); yAxis++) {
            for (int xAxis = x; xAxis < x + (pixelSize); xAxis++) {
                img.setRGB(xAxis, yAxis, color);
            }
        }
    }

}
