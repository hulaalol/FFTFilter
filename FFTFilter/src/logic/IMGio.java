package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.math3.complex.Complex;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class IMGio {
	
	
	public static Complex[][] img2CArray(String path) throws IOException{
		
		BufferedImage img = ImageIO.read(new File(path));
		int n = img.getWidth();
		byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		
		Complex[][] output = new Complex[n][n];

		for(int row=0;row<n;row++){
			for(int col=0; col<n; col++){
				output[row][col]=(new Complex(data[col*n+row],0));
			}
		}
		return output;
	}
	
	
public static Complex[][] img2CArray(BufferedImage img) throws IOException{
		
		int n = img.getWidth();
		byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		
		Complex[][] output = new Complex[n][n];

		for(int row=0;row<n;row++){
			for(int col=0; col<n; col++){
				output[row][col]=(new Complex(data[col*n+row],0));
			}
		}
		return output;
	}
	
	
	public static byte[][] doubleToGrayscale (double[][] input, String type){
		
		int n = input.length;
		byte [][] output = new byte[n][n];
		double c;
		
		if(type.equals("amplitude")){
			c = (255.0/Math.log10(1+Utils.getMax(input)));
		}else{
			c=0;
		}
		
		for(int row=0;row<n;row++){
			for(int col=0;col<n;col++){
				
				if(type.equals("phase")){
					double pixel = 255*((input[row][col]+Math.PI)/Math.PI);
					output[row][col]=(byte)Math.round(pixel);	
				}else if(type.equals("amplitude")){
					double pixel = c*Math.log10(1+input[row][col]);
					output[row][col]=(byte)Math.round(pixel);	
				}else{
					System.err.println("Wrong type - specify \"phase\" or \"amplitude\"!");
				}		
			}
		}
		
		return output;
	}
	
	
	
	public static BufferedImage byteArray2Img(byte[][]input){
		
		int n = input.length;
		byte[] byte1d = new byte[n*n];
		
		for(int x = 0; x < n; x++) {
		    for(int y = 0; y < n; y++) {
		        byte1d[x + y * n] = input[x][y];

		    }
		}
		BufferedImage image = new BufferedImage(n,n,BufferedImage.TYPE_BYTE_GRAY);
		byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(byte1d, 0, data, 0,n*n);
		
		return image;
	}
	

	public static byte[][] complex2byte(Complex[][] input) {

		int n = input.length;
		byte[][] output = new byte[n][n];

		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				double v = Math.round(input[i][k].getReal());
				if (v > 127) {
					v = 127;
				} else if (v < -128) {
					v = -128;
				}
				output[i][k] = (byte) v;
			}
		}

		return output;
	}
	
	
	public static Image img2AmpImg(Image input) throws IOException{
		BufferedImage source = SwingFXUtils.fromFXImage(input,null);
		source = getGrayScale2(source);

		Complex[][] c = logic.IMGio.img2CArray(source);									//utils array cshift
		byte[][] amp = logic.IMGio.doubleToGrayscale(logic.FourierTransform.getSpectrum(logic.FourierTransform.fft2d(c.length, c)),"amplitude");
		BufferedImage destination = logic.IMGio.byteArray2Img(amp);
		return SwingFXUtils.toFXImage(destination, null);
	}
	
	
	public static Image img2AmpImgNOSHIFT(Image input) throws IOException{
		BufferedImage source = SwingFXUtils.fromFXImage(input,null);
		source = getGrayScale2(source);

		Complex[][] c = logic.IMGio.img2CArray(source);
		byte[][] amp = logic.IMGio.doubleToGrayscale(logic.FourierTransform.getSpectrum(logic.FourierTransform.fft2d(c.length, c)),"amplitude");
		BufferedImage destination = logic.IMGio.byteArray2Img(amp);
		return SwingFXUtils.toFXImage(destination, null);
	}
	
	
	public static Image img2PhaseImg(Image input) throws IOException{
		BufferedImage source = SwingFXUtils.fromFXImage(input,null);
		source = getGrayScale2(source);

		Complex[][] c = logic.IMGio.img2CArray(source);
		byte[][] amp = logic.IMGio.doubleToGrayscale(logic.FourierTransform.getPhase(Utils.arrayCShift(logic.FourierTransform.fft2d(c.length, c))),"phase");
		BufferedImage destination = logic.IMGio.byteArray2Img(amp);
		return SwingFXUtils.toFXImage(destination, null);
	}
	
	public static Image img2FFTCompress(Image input,double factor) throws IOException{
		BufferedImage source = SwingFXUtils.fromFXImage(input,null);
		source = getGrayScale2(source);

		Complex[][] c = logic.IMGio.img2CArray(source);

		c = FourierTransform.fft2d(c.length, c);
		
		byte[][] compressed = complex2byte(FourierTransform.ifft2d(Compressor.compressC(c, factor)));
		BufferedImage destination = logic.IMGio.byteArray2Img(compressed);
		return SwingFXUtils.toFXImage(destination, null);
	}
	
	
	public static BufferedImage getGrayScale(BufferedImage inputImage){
	    BufferedImage img = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	    Graphics g = img.getGraphics();
	    g.drawImage(inputImage, 0, 0, null);
	    g.dispose();
	    return img;
	}
	
	
	public static BufferedImage getGrayScale2(BufferedImage inputImage){
		
		byte[][] data = luminosity(inputImage);
		return byteArray2Img(data);
		
		
		
	}
	
	private static byte[][] luminosity(BufferedImage original) {
		 
	    int red, green, blue;

	 
	   byte[][] output = new byte[original.getWidth()][original.getHeight()];
	 
	    for(int i=0; i<original.getWidth(); i++) {
	        for(int j=0; j<original.getHeight(); j++) {
	 
	            // Get pixels by R, G, B
	            red = new Color(original.getRGB(i, j)).getRed();
	            green = new Color(original.getRGB(i, j)).getGreen();
	            blue = new Color(original.getRGB(i, j)).getBlue();
	 
	            red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
	
	            
	            output[i][j] = (byte)red;
	 
	            // Write pixels into image
	            
	 
	        }
	    }
	 
	    return output;
	 
	}
	
	
	

}
	
	
	

	
	


