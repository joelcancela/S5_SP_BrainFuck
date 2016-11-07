package unice.polytech.polystirN.brainfuck.interpreter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Class used to read translate text into images
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class ImageWriter {
    private BufferedImage img;//Buffer used to write the image
    private TextReader buffer;//Buffer used to read the program
    private String filename;//Filename of the program
    private final int pixelSize = 3;//pixels squares' width and height


    /**
     * ImageWriter constructor
     *
     * @param filename is the filename of the program to translate
     * @throws Exception if the filename is incorrect
     */
    public ImageWriter(String filename) throws Exception {
        this.filename = filename;
        buffer = new TextReader(filename);
    }

    /**
     * Translate the program read by the buffer and writes the corresponding image
     *
     * @throws Exception if the program is bad formed or if the image writing went wrong
     */
    private void translate(String outputFilename) throws Exception {
        InstructionFactory factory = new InstructionFactory();
        int instructionsNumber = 0;
        while (buffer.hasNext()) {
            instructionsNumber++;
        }
        buffer = new TextReader(filename);
        int pictureWidthSquares = 0;
        while ((pictureWidthSquares * pictureWidthSquares) < (instructionsNumber)) {
            pictureWidthSquares++;
        }
        int pictureWidth = pictureWidthSquares * pixelSize;
        img = new BufferedImage(pictureWidth, pictureWidth, TYPE_INT_RGB);
        int x = 0;
        int y = 0;
        while (buffer.hasNext()) {
            String s = buffer.next();
            if (!s.equals("\n")) {
                int col = factory.getColor(s);
                fillPixelSquare(x, y, col);
                if (x + pixelSize == pictureWidth) {
                    x = 0;
                    y += pixelSize;
                } else {
                    x += pixelSize;
                }
            }

        }

        ImageIO.write(img, "BMP", new File(outputFilename));
    }

    /**
     * Default call for translate
     *
     * @throws Exception if the output filename is incorrect
     */
    public void translate() throws Exception {
        translate("outputTranslate.bmp");
    }

    /**
     * Fills pixels squares with the same color
     *
     * @param x     is the x-coordinate where the fill starts
     * @param y     is the y-coordinate where the fill starts
     * @param color is the color to fill
     */
    private void fillPixelSquare(int x, int y, int color) {
        for (int yAxis = y; yAxis < y + (pixelSize); yAxis++) {
            for (int xAxis = x; xAxis < x + (pixelSize); xAxis++) {
                img.setRGB(xAxis, yAxis, color);
            }
        }
    }

}
