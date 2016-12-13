package logic;

import org.apache.commons.math3.complex.Complex;

public class FourierTransform {
	
	public static Complex[] fft(int n, Complex[] f) {

		Complex[] result = new Complex[n];
		Complex[] half1 = new Complex[n / 2];
		Complex[] half2 = new Complex[n / 2];

		if (n == 1) {
			return f;
		} else {
			// divide and conquer!
			Complex[] fEven = new Complex[n / 2];
			Complex[] fOdd = new Complex[n / 2];

			int oi = 0;
			int ei = 0;

			for (int i = 0; i < f.length; i++) {
				if (i % 2 == 0) {
					fEven[oi] = (f[i]);
					oi++;
				} else {
					fOdd[ei] = (f[i]);
					ei++;
				}
			}

			half1 = fft(n / 2, fEven);
			half2 = fft(n / 2, fOdd);

		}
		// combine the result

		for (int p = 0; p <= ((n / 2) - 1); p++) {

			double exponent = -2.0 * Math.PI * ((double) p) / ((double) n);
			Complex ek = new Complex(Math.cos(exponent), Math.sin(exponent));
			Complex multi = half2[p].multiply(ek);

			result[p] = half1[p].add(multi);
			result[p + (n / 2)] = half1[p].subtract(multi);
		}

		return result;

	}
	
	
	public static Complex[][] fft2d(int n, Complex[][] f) {

		Complex[][] dim1transformation = new Complex[n][n];

		for (int row = 0; row < n; row++) {
			Complex[] input = f[row];
			dim1transformation[row] = fft(n, input);
		}

		Complex[][] dim2transformation = new Complex[n][n];

		for (int col = 0; col < n; col++) {

			Complex[] input = new Complex[n];

			for (int k = 0; k < n; k++) {
				input[k] = dim1transformation[k][col];
			}

			dim2transformation[col] = fft(n, input);
		}
		return dim2transformation;
	}
	

	public static Complex[][] ifft2d(Complex[][] input) {
		return ifftFactor(complexConjugate(fft2d(input.length, complexConjugate(input))));
	}

	public static double[][] getPhase(Complex[][] dim2FFT) {

		double[][] result = new double[dim2FFT.length][dim2FFT[0].length];

		for (int row = 0; row < dim2FFT.length; row++) {
			for (int col = 0; col < dim2FFT[0].length; col++)

				result[row][col] = dim2FFT[row][col].getArgument();
		}
		return result;
	}

	public static double[][] getSpectrum(Complex[][] dim2FFT) {

		double[][] result = new double[dim2FFT.length][dim2FFT[0].length];

		for (int row = 0; row < dim2FFT.length; row++) {
			for (int col = 0; col < dim2FFT[0].length; col++)

				result[row][col] = dim2FFT[row][col].abs();

		}
		return result;
	}


	public static Complex[][] complexConjugate(Complex[][] input) {

		int n = input.length;
		Complex[][] output = new Complex[n][n];

		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				output[i][k] = input[i][k].conjugate();
			}
		}
		return output;
	}

	public static Complex[][] ifftFactor(Complex[][] input) {

		int n = input.length;
		Complex[][] output = new Complex[n][n];

		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				output[i][k] = input[i][k].multiply(1.0 / (n * n));
			}
		}
		return output;
	}

	

	

}
