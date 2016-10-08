package unice.polytech.polystirN.brainfuck.interpreter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Joel CANCELA VAZ on 05/10/2016.
 */
public class InterpreterImage extends Interpreter {
    private BufferedImage buffer;
    /**
     * This method resets the memory and the pointer and
     * sets the correct operatorsKeywords to interpret as operators
     */
    public InterpreterImage(String filename) throws Exception {
        super();
        File img = new File(filename);
        buffer = ImageIO.read(img);
    }

    @Override
    public boolean rewriteFile() throws Exception {
        return false;
    }

    @Override
    public boolean executeFile() throws Exception {
        return false;
    }

    @Override
    public Object getBuffer() {
        return buffer;
    }
}
