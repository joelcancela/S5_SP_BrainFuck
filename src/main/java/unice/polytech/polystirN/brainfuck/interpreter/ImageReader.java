package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.exceptions.BadSquareColorException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Class used to read images and translate them into instructions
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class ImageReader extends Reader {
	private final int pixelSize = 3; //pixels squares' width and height
	private BufferedImage buffer; //buffer used to read pictures
	private int width; //width of the picture
	private int height; //height of the picture
	private int currentX; //current x coordinate of the buffer
	private int currentY; //current y coordinate of the buffer

	/**
	 * ImageReader constructor
	 *
	 * @param filename is the image filename to open
	 * @throws Exception if the filename is incorrect or null
	 */
	public ImageReader(String filename) throws Exception {
		buffer = ImageIO.read(new File(filename));
		width = buffer.getWidth();
		height = buffer.getHeight();
		currentX = 0;
		currentY = 0;
	}

	/**
	 * Checks if there is another square to read on the picture
	 *
	 * @return true if this square is not the last to read, else false
	 */
	@Override
	public boolean hasNext() throws Exception {
		return (!isEnd(currentX, currentY));

	}

	/**
	 * Reads a square and return the corresponding operator and increments the iteration
	 *
	 * @return String a string being the operator symbol
	 * @throws BadSquareColorException if a square doesn't have all its pixels with the same color
	 */
	@Override
	public String next() throws Exception {
		int savedX = currentX;
		int savedY = currentY;
		if (currentX == (width - pixelSize)) {
			if (isPixelConform(currentX, currentY)) {
				currentX = 0;
				currentY += pixelSize;

			} else {
				int a = (currentX / pixelSize) + 1;
				int b = (currentY / pixelSize) + 1;
				int c = a * b;
				throw new BadSquareColorException(c);
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
		return printPixel(buffer.getRGB(savedX, savedY)).trim();

	}

	/**
	 * Checks if a square has its pixels with the same colors
	 *
	 * @param currentX is the x coordinate of the top left pixel in the square
	 * @param currentY is the y coordinate of the top left pixel in the square
	 * @return true if the square is conform else false
	 */
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

	/**
	 * Prints the color of a pixel in hexadecimal notation #rrggbb
	 *
	 * @param pixel is an integer being the rgb color code of a pixel
	 * @return String being the hexadecimal notation of the color
	 */
	private String printPixel(int pixel) {
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		return String.format("#%02X%02X%02X", red, green, blue);

	}

	/**
	 * Checks if the pixel is the last one
	 *
	 * @param x is the x coordinate of a pixel
	 * @param y is the y coordinate of a pixel
	 * @return true if the pixel is the last one or a black one, else false
	 */
	private boolean isEnd(int x, int y) {
		return ((((x >= width) || (y >= height))) || (printPixel(buffer.getRGB(x, y)).equals("#000000")));
	}


}
