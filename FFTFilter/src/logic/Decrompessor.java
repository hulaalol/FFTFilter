package logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import org.apache.commons.math3.complex.Complex;

public class Decrompessor {

	
	public static BufferedImage ftc2Image(String path) throws IOException, ClassNotFoundException{

		 FileInputStream fis = new FileInputStream(path);
	     ObjectInputStream ois = new ObjectInputStream(fis);
	     HashMap<Integer,Complex> map = (HashMap<Integer, Complex>) ois.readObject();
	     ois.close();
		
	    Complex[][] img = FourierTransform.ifft2d(restoreArray(map));
	    byte[][] ifftRestore = IMGio.complex2byte(img);

		return IMGio.byteArray2Img(ifftRestore);
	}
	

	
	public static Complex[][] restoreArray(HashMap<Integer,Complex> map){

		int n = (int)Math.round(map.get(new Integer(-1)).getReal());
		Complex[][] output = new Complex[n][n];
		
		Complex c = new Complex(0,0);
		for(int row=0;row<n;row++){
			for(int col=0;col<n;col++){
				output[row][col] = c;
			}
		}
		
		for(Integer s : map.keySet()){
			if(!s.equals(new Integer(-1))){
				output[s/n][s%n] = map.get(s);
			}
		}
		
		return Compressor.restoreMirrors(output);
		
	}
	
	
	
	
	
	
	
	
	
}
