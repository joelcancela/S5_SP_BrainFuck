package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.exceptions.BadSquareColorException;
import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.language.Operator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * Created by Joel CANCELA VAZ on 05/10/2016.
 */
public class ImageReader extends Reader {
    private BufferedImage buffer;
    private int width;
    private int currentX;
    private int currentY;
    private HashMap<String, String> operatorsColors;
    private final int pixelSize = 3;

    /**
     * This method resets the memory and the pointer and
     * sets the correct operatorsKeywords to interpret as operators
     */

    public ImageReader(String filename) throws Exception {
        this();
        buffer = ImageIO.read(new File(filename));
        width = buffer.getWidth();
    }

    public ImageReader() throws Exception {
        operatorsColors = new HashMap<>();
        operatorsColors.put("#255255255", "+");
        operatorsColors.put("#750130","-");
        operatorsColors.put("#1480211","<");
        operatorsColors.put("#00255", ">");
        operatorsColors.put("#02550",".");
        operatorsColors.put("#2552550",",");
        operatorsColors.put("#2551270","[");
        operatorsColors.put("#25500","]");
        currentX = 0;
        currentY = 0;
    }

    @Override
    public boolean hasNext() throws Exception {
            return (!isEnd(currentX, currentY));

    }

    @Override
    public String next() throws Exception {
        int savedX = currentX;
        int savedY = currentY;
        if (currentX == (width - pixelSize)) {
            if (isPixelConform(0, currentY + pixelSize)) {
                currentX = 0;
                currentY += pixelSize;

            } else {
                throw new BadSquareColorException("Square number : 0 hasn't its pixels with same colors");//TODO
            }
        } else {
            if (isPixelConform(currentX + pixelSize, currentY)) {
                currentX += pixelSize;
            } else {
                throw new BadSquareColorException("Square number : " + (currentX + 1) + " hasn't its pixels with same colors");//TODO
            }
        }
        return operatorsColors.get(printPixel(buffer.getRGB(savedX, savedY)));

    }

    @Override
    public Object getBuffer() {
        return buffer;
    }

    public boolean isPixelConform(int currentX, int currentY) {
        int color = buffer.getRGB(currentX, currentY);
        for (int y = currentY; y < currentY + (pixelSize - 1); y++) {
            for (int x = currentX; x < currentX + (pixelSize - 1); x++) {
                if (buffer.getRGB(x, y) != color) {
                    return false;
                }

            }
        }
        return true;
    }


    public String printPixel(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        return "#" + red + "" + green + "" + blue;

    }

    public boolean isEnd(int x, int y) {
        return (printPixel(buffer.getRGB(x, y)).equals("#000"));
    }


}
