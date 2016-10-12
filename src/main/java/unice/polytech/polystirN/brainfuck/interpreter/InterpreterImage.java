package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.language.Operator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * Created by Joel CANCELA VAZ on 05/10/2016.
 */
public class InterpreterImage extends Interpreter {
    private BufferedImage buffer;
    private int width;
    private int height;
    private HashMap<String, String> operatorsColors;
    private final int pixelSize = 3;

    /**
     * This method resets the memory and the pointer and
     * sets the correct operatorsKeywords to interpret as operators
     */

    public InterpreterImage(String filename) throws Exception {
        this();
        if (!filename.matches("(.*).bmp")) {
            throw new IncorrectFileTypeException(filename + " must have .bmp extension");
        }
        buffer = ImageIO.read(new File(filename));
        width = buffer.getWidth();
        height = buffer.getHeight();
    }

    public InterpreterImage() throws Exception {
        super();
        operatorsColors = new HashMap<>();
        operatorsColors.put("#255255255","+");
        operatorsColors.put("#00255",">");

    }

    @Override
    public boolean rewriteFile() throws Exception {
        return false;
    }

    @Override
    public boolean executeFile() throws Exception {
        for (int y = 0; y < height; ) {
            for (int x = 0; x < width; x += pixelSize) {
                if (!isPixelConform(x, y)) {
                    return false;
                }
                if(isEnd(x,y)){
                    return true;
                }
                String operator = operatorsColors.get(printPixel(buffer.getRGB(x,y)));
                getOperatorsKeywords().get(operator).doOperation(this);
            }
            y += pixelSize;
        }
        return true;
    }

    @Override
    public Object getBuffer() {
        return buffer;
    }

    public boolean isPixelConform(int currentX, int currentY) {
        int color = buffer.getRGB(currentX, currentY);
        for (int y = currentY; y < currentY + 3; y++) {
            for (int x = currentX; x < currentX + 3; x++) {
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
        return "#"+red+""+green+""+blue;

    }

    public boolean isEnd(int x,int y){
        if(printPixel(buffer.getRGB(x,y)).equals("#255255255"))
        return true;

        return false;
    }


}
