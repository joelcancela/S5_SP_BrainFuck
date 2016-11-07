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
    private BufferedImage img;
    private TextReader buffer;
    private String filename;
    private final int pixelSize = 3;


    public ImageWriter(String filename) throws Exception {
        this.filename = filename;
        buffer = new TextReader(filename);
    }

    public void translate() throws Exception {
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
