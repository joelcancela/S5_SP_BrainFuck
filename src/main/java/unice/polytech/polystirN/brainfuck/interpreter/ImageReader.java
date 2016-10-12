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
    private int height;
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
        height = buffer.getHeight();
    }

    public ImageReader() throws Exception {
        operatorsColors = new HashMap<>();
        operatorsColors.put("#255255255","+");
        operatorsColors.put("#00255",">");
        currentX=0;
        currentY=0;
    }
    
    @Override
    public boolean hasNext() throws Exception{
    	if(currentX==width){
    		return (isEnd(0,currentY+1));
    	}else {
    		return (isEnd(currentX+1,currentY));
    	}
    }
    
    @Override
    public String next() throws Exception{
    	if(currentX==width){
    		if (isPixelConform(0,currentY+1)) {
    			return operatorsColors.get(printPixel(buffer.getRGB(0,currentY+1)));
    		}else{
    			throw new BadSquareColorException("Square number : 0 hasn't its pixels with same colors");
    		}
    	}else{
    		if (isPixelConform(currentX+1,currentY)) {
    			return operatorsColors.get(printPixel(buffer.getRGB(currentX+1,currentY)));
    		}else{
    			throw new BadSquareColorException("Square number : "+(currentX+1)+" hasn't its pixels with same colors");
    		}//TODO joel fdp
    	}
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
