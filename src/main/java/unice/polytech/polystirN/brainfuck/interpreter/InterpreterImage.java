package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Joel CANCELA VAZ on 05/10/2016.
 */
public class InterpreterImage extends Interpreter {
    private BufferedImage buffer;
    private int width;
    private int height;

    /**
     * This method resets the memory and the pointer and
     * sets the correct operatorsKeywords to interpret as operators
     */

    public InterpreterImage(String filename) throws Exception {
        super();
        if (!filename.matches("(.*).bmp")) {
            throw new IncorrectFileTypeException(filename + " must have .bmp extension");
        }
        buffer = ImageIO.read(new File(filename));
        width = buffer.getWidth();
        height = buffer.getHeight();
    }

    @Override
    public boolean rewriteFile() throws Exception {
        return false;
    }

    @Override
    public boolean executeFile() throws Exception {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int colour = buffer.getRGB(x, y);
//                int  red   = (colour & 0x00ff0000) >> 16;
//                int  green = (colour & 0x0000ff00) >> 8;
//                int  blue  =  colour & 0x000000ff;
            }
        }
        return true;
    }

    @Override
    public Object getBuffer() {
        return buffer;
    }
}
