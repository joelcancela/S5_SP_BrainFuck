package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.exceptions.BadSquareColorException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * Class used to read images and translate them into instructions
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
class ImageReader extends Reader {
    private BufferedImage buffer;
    private int width; //width of the picture
    private int height; //height of the picture
    private int currentX; //current x coordinate of the buffer
    private int currentY; //current y coordinate of the buffer
    private HashMap<String, String> operatorsColors; //binds colors and operations
    private final int pixelSize = 3; //pixel width and height


    ImageReader(String filename) throws Exception {
        buffer = ImageIO.read(new File(filename));
        width = buffer.getWidth();
        height = buffer.getHeight();
        operatorsColors = new HashMap<>();
        operatorsColors.put("#FFFFFF", "+");
        operatorsColors.put("#4B0082", "-");
        operatorsColors.put("#9400D3", "<");
        operatorsColors.put("#0000FF", ">");
        operatorsColors.put("#00FF00", ".");
        operatorsColors.put("#FFFF00", ",");
        operatorsColors.put("#FF7F00", "[");
        operatorsColors.put("#FF0000", "]");
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
            if (isPixelConform(currentX, currentY)) {
                currentX = 0;
                currentY += pixelSize;

            } else {
                int b = (currentX / pixelSize) + 1;
                throw new BadSquareColorException(b);
            }
        } else {
            if (isPixelConform(currentX, currentY)) {
                currentX += pixelSize;
            } else {
                int a = width / pixelSize;
                int b = (currentY / pixelSize) * a;
                int c = (currentX / pixelSize) + 1;
                throw new BadSquareColorException(b + c);
            }
        }
        return operatorsColors.get(printPixel(buffer.getRGB(savedX, savedY)));

    }

    @Override
    public Object getBuffer() {
        return buffer;
    }

    private boolean isPixelConform(int currentX, int currentY) {
        int color = buffer.getRGB(currentX, currentY);
        for (int y = currentY; y < currentY + (pixelSize); y++) {
            for (int x = currentX; x < currentX + (pixelSize); x++) {
                if (buffer.getRGB(x, y) != color) {
                    return false;
                }

            }
        }
        return true;
    }


    private String printPixel(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        return String.format("#%02X%02X%02X", red, green, blue);

    }

    private boolean isEnd(int x, int y) {
        return ((((x >= width) || (y >= height))) || (printPixel(buffer.getRGB(x, y)).equals("#000000")));
    }


}
