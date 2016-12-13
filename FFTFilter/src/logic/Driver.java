package logic;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.math3.complex.Complex;

import javafx.scene.image.Image;

public class Driver {

public static void main(String args[]) throws IOException{
	
//	String path = "./resources/";
//	String filename = "test_square.jpg";
//	
//	
//	Image img = new Image(path+filename);
//	
//	ImageIO.write(img, "jpg", new File(path+filename+"FTCRESTORE.jpg"));
//	
//	
//	HashMap<Integer,Complex> map = Compressor.img2fc(path+filename,3);
//	
//	//serialize Map
//	FileOutputStream fos;
//	fos = new FileOutputStream(path+filename+".FTC");
//	ObjectOutputStream oos = new ObjectOutputStream(fos);
//    oos.writeObject(map);
//    oos.close();
//	
//	
//	BufferedImage test = null;
//	
//	try {
//		test = Decrompessor.ftc2Image(path+filename+".FTC");
//	} catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	ImageIO.write(test, "jpg", new File(path+filename+"FTCRESTORE.jpg"));
	//test
//						BufferedImage img = ImageIO.read(new File(path));
//						byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
//						
//						
//						Complex[][] image= JPEGio.img2CArray(path);
//						
//						
//						//transform
//						Complex[][] fft = FourierTransform.fft2d(image.length, image);
	
	//remove mirrors
	//Complex[][] fft2 = Compressor.removeMirrors(fft);
	//map to hashmap
//	HashMap<Integer,Complex> hashmap= Compressor.compress(fft2, 1);
//	
//	
//
//	//restore from hashmap
//	//Complex[][] restore = Compressor.HashMap2Complex(hashmap);
//	
//	//restore array
//	Complex[][] restore= Decrompessor.restoreArray(hashmap);
//	
	//restore mirrors
	//Complex[][] restoreMirrors = Compressor.restoreMirrors(restore);
	//restore values
//					Complex[][] ifftRestore2 = FourierTransform.ifft2d(fft);
//					
//					//get real values to image
//					byte[][] data2 = JPEGio.complex2byte(ifftRestore2);
//					BufferedImage result = JPEGio.byteArray2Img(data2);
//					
//					ImageIO.write(result, "jpg", new File("./resources/DRIVER6.jpg"));
	
//	//test 
//		String filename = "roof.jpg";
//		
//		//read file to hashmap
//		HashMap<Integer,Complex> compressed2 = Compressor.img2fc("./resources/"+filename, 1);
//		
//		//write information in folder
//		FileOutputStream fout = new FileOutputStream("./resources/"+filename+"-COMP2.ftc");
//		ObjectOutputStream oos = new ObjectOutputStream(fout);
//		oos.writeObject(compressed2);
//		oos.close();
////	
//		BufferedImage test = null;
//		try {
//			test = Decrompessor.ftc2Image("./resources/"+filename+"-COMP2.ftc");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		ImageIO.write(test, "jpg", new File("./resources/test_"+filename+"_-COMP2.jpg"));
//		
		
//		Complex[][] input = JPEGio.img2CArray("./resources/"+filename);		
//		int n = input.length;
////		
//		Complex[][] fftResult = FourierTransform.fft2d(n,input);
//		Complex[][] ifft = FourierTransform.ifft2d(fftResult);
//		Complex[][] shiftResult = Utils.arrayCShift(fftResult);

//		//remove mirrors
//		Complex[][] fftResult2 = Compressor.removeMirrors(fftResult);
//		//map to hashmap
//		HashMap<Integer,Complex> hashmap= Compressor.compress3(fftResult2, 1);
//		//restore from hashmap
//		fftResult2 = Compressor.HashMap2Complex(hashmap);
//		//restore mirrors
//		Complex[][] restoreMirrors = Compressor.restoreMirrors(fftResult2);
//		//restore values
//		Complex[][] ifftRestore2 = FourierTransform.ifft2d(restoreMirrors);
		
//		
//		double[][] amp = FourierTransform.getSpectrum(fftResult);
//		
//		byte[][] amplitude = JPEGio.doubleToGrayscale(FourierTransform.getSpectrum(shiftResult), "amplitude");
//		byte[][] phase = JPEGio.doubleToGrayscale(FourierTransform.getPhase(shiftResult),"phase");
//		byte[][] ifftRestore = JPEGio.complex2byte(ifftRestore2);
//		
//		byte[][] ifftRestore = JPEGio.complex2byte(ifft);
//		
//		BufferedImage testImg = JPEGio.byteArray2Img(ifftRestore);
		
		
//		BufferedImage ampImg = JPEGio.byteArray2Img(amplitude);
//		BufferedImage phaseImg = JPEGio.byteArray2Img(phase);
//		BufferedImage restored = JPEGio.byteArray2Img(ifftRestore);
		//BufferedImage compressed = JPEGio.byteArray2Img(compImg);
		
		// Write it out:
//		ImageIO.write(ampImg, "jpg", new File("./resources/test_Amplitude.jpg"));
//		ImageIO.write(phaseImg, "jpg", new File("./resources/test_Phase.jpg"));
//		ImageIO.write(restored, "jpg", new File("./resources/test_restore.jpg"));
		//ImageIO.write(compressed, "jpg", new File("./resources/test_compressed.jpg"));
		
//		ImageIO.write(test, "jpg", new File("./resources/test_"+filename+"_23DECOMPRESSOR.jpg"));
//		ImageIO.write(testImg, "jpg", new File("./resources/test_"+filename+"_2DECOMPRESSOR.jpg"));
		
		System.out.println("success");
	}

}
