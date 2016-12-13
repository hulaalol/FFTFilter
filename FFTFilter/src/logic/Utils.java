package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.math3.complex.Complex;

public class Utils {

	

	
	public static Complex[][] arrayCShift(Complex[][] input) {
		//performs a circular shift of the quadrants of an 2 dimensional array of size nxn
		Complex[][] output = new Complex[input.length][input.length];
		
		int n = input.length;
		
		
		// first quadrant becomes third quadrant
		for(int row=0;row<(n/2);row++){
			for(int col=(n/2);col<n;col++){
			output[row+(n/2)][col-(n/2)]=input[row][col];	
			}
		}
		
		//second quadrant becomes fourth quadrant
		for(int row=0;row<(n/2);row++){
			for(int col=0;col<(n/2);col++){
			output[row+(n/2)][col+(n/2)]=input[row][col];	
			}
		}
		
		//third quadrant becomes first quadrant
		for(int row=(n/2);row<n;row++){
			for(int col=0;col<(n/2);col++){
			output[row-(n/2)][col+(n/2)]=input[row][col];	
			}
		}
		
		//fourth quadrant becomes second quadrant
		for(int row=(n/2);row<n;row++){
			for(int col=(n/2);col<n;col++){
			output[row-(n/2)][col-(n/2)]=input[row][col];	
			}
		}

		return output;
	  
	}
	
	
public static double getMax(double[][] array){
		
		int n = array.length;
		double max = 0;
		
		for(int row=0;row<n;row++){
			for(int col=0;col<n;col++){
				double value = array[row][col];
				
				if(value>max){
					max=value;
				}
			}
		}
		return max;
	}


public static Complex[][] mirrorRestore(Complex[][] mirror) {
	//performs a circular shift of the quadrants of an 2 dimensional array of size nxn
	int n = 2*mirror.length;
	Complex[][] output = new Complex[n][n];

	//enter second quadrant
	for(int row=0;row<(n/2);row++){
		for(int col=0;col<(n/2);col++){
		output[row][col]=mirror[row][col];	
		}
	}
	
	
	//mirror second quadrant into first quadrant
	for(int row=0;row<(n/2);row++){
		for(int col=(n/2);col<n;col++){
			output[row][col]=output[row][n-col-1];
		}
	}
	
	//mirror second quadrant into third quadrant
	for(int row=(n/2);row<n;row++){
		for(int col=0;col<(n/2);col++){
			output[row][col]=output[n-row-1][col];
		}
	}
	
	//mirror second quadrant into fourth quadrant
	for(int row=(n/2);row<n;row++){
		for(int col=(n/2);col<n;col++){
			output[row][col]=output[n-row][col];
		}
	}
	return output;
  
}

	public static void printCArray(Complex[][] c){
	
		int n = c.length;
		
		for(int row=0;row<n;row++){
			System.out.println("");
			for(int col=0;col<n;col++){
				
				int re = (int) Math.round(c[row][col].getReal());
				int im = (int) Math.round(c[row][col].getImaginary());
				System.out.print("("+re+","+im+")\t" );
			}
		}

	}
	
	
	public static double mean(double[][] input){
		int rmax = input.length;
		int cmax = input[0].length;
		double sum = 0;
		for(int row=0;row<rmax;row++){
			for(int col=0;col<cmax;col++){
				sum+=input[row][col];
			}
		}
		return sum/(input.length*input.length);
		
	}

	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

