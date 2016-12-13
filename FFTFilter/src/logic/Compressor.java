package logic;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.math3.complex.Complex;

public class Compressor {
	
	
	public static HashMap<Integer,Complex> img2fc(String path,double factor) throws IOException{
		Complex[][] image= IMGio.img2CArray(path);
		int n = image.length;
		return compress(removeMirrors(FourierTransform.fft2d(n, image)),factor);
	}


	public static HashMap<Integer,Complex> compress(Complex[][] fft,double factor){
		
		
		double[][] amplitude = FourierTransform.getSpectrum(fft);
		amplitude[0][0] = 0.0;
		
		double threshold = Utils.mean(amplitude)*factor;
		HashMap<Integer,Complex> output = new HashMap<Integer,Complex>();
		int rmax = fft.length;
		int cmax = fft[0].length;
		
	//	double maxDistance = (0.5*Math.sqrt(2.0*(rmax*rmax)));
		
		for(int row=0;row<rmax;row++){
			for(int col=0;col<cmax;col++){
				
				//double distanceFactor = Math.sqrt(row*row+col*col)/maxDistance;
				
				
										//*1-distanceFactor//
				if(amplitude[row][col]>threshold){
					output.put(new Integer(row*fft.length+col), fft[row][col]);				}	
			}
		}
		output.put(new Integer(0), fft[0][0]);
		output.put(new Integer(-1), new Complex(rmax,cmax));
		return output;
	}
	
	public static Complex[][] compressC(Complex[][] fft, double factor) {

		double[][] amplitude = FourierTransform.getSpectrum(fft);
		amplitude[0][0] = 0.0;

		double threshold = Utils.mean(amplitude) * factor;

		int rmax = fft.length;
		int cmax = fft[0].length;
		Complex[][] output = new Complex[rmax][cmax];
		// double maxDistance = (0.5*Math.sqrt(2.0*(rmax*rmax)));

		for (int row = 0; row < rmax; row++) {
			for (int col = 0; col < cmax; col++) {

				if (amplitude[row][col] > threshold) {
					output[row][col] = fft[row][col];
				} else {
					output[row][col] = new Complex(0, 0);
				}
			}
		}
		output[0][0] = fft[0][0];
		return output;
	}
	
	
	
	public static Complex[][] removeMirrors(Complex[][] input){
		int n = input.length;
		Complex[][] output = new Complex[n][n/2+1];
		
		for(int i = 0;i<n;i++){
			for(int k = 0;k<(n/2)+1;k++){
				output[i][k] = input[i][k];
			}
		}
		return output;
	}
	
	public static Complex[][] restoreMirrors(Complex[][] compressed){
		
		int n = compressed.length;
		Complex[][] restored = new Complex[n][n];
		
		for(int row=0;row<n;row++){
			for(int col=0;col<((n/2)+1);col++){
				restored[row][col] = compressed[row][col];
			}
		}
		
		
		//restore first row and middle row
		for(int col=((n/2)+1);col<n;col++){
			restored[0][col] = compressed[0][n-col].conjugate();
			restored[n/2][col]=compressed[n/2][n-col].conjugate();
		}

		
		//mirror third quadrant in first quadrant
		for(int row=1;row<((n/2));row++){
			for(int col=1;col<((n/2));col++){
				restored[n-row][n-col]=compressed[row][col].conjugate();
			}
		}

		//mirror second quadrant in fourth quadrant
		for(int row=((n/2)+1);row<(n);row++){
			for(int col=1;col<((n/2));col++){
				restored[n-row][n-col]=compressed[row][col].conjugate();
			}
		}
		return restored;
	}
	
	
	public static Complex[][] HashMap2Complex (HashMap<Integer,Complex> values){
		
		int n = (int) Math.round(values.get(new Integer(-1)).getReal());
	
		
		Complex[][] output = new Complex[n][n];
		
		for(int row=0;row<n;row++){
			for(int col=0;col<n;col++){
				output[row][col]=new Complex(0,0);
			}
		}
		
		Set<Integer> keys = values.keySet();
		for(Integer key : keys){
			
			if(key.intValue()>=0){
				output[key/n][key%n] = values.get(key);
			}
			
		}
		return output;
	}
	
	
}
