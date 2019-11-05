package Utils;
import java.util.Random;


public class DeepMath {
	private static Random fRandom = new Random();
	
	public static double[][] matmul(double[][] a, double[][] b) throws Exception{
		double[][] res;
		// getting shape information
		int a_rows = a.length;
		int a_cols = a[0].length;
		int b_rows = b.length;
		int b_cols = b[0].length;
		
		if(a_cols != b_rows) {
			throw new Exception("Matrix Dimension mismatch. A="+a_rows+","+a_cols+" . B="+b_rows + "," + b_cols);
		}
		
		res = new double[a_rows][b_cols];
		
		for(int i = 0; i < a_rows; i++) {
			for(int j = 0; j < b_cols; i++) {
				double sum = 0;
				for(int k = 0; k < a_cols; k++) {
					sum += a[i][k] * b[k][j];
				}
				res[i][j]= sum; 
			}
		}
		
		return res;
	}
	
	public static double[][] transpose(double[][] a){
		double[][] aT = new double[a[0].length][a.length];
		
		for(int i = 0; i < aT.length; i++) {
			for(int j = 0; j < aT[0].length; j++) {
				aT[i][j] = a[j][i]; 
			}
		}
		
		return aT;
	}
	
	public static double[][] matrixSummation(double[][] a, int axis){
		double[][] sum = null;
		
		// Summing across Rows
		if(axis == 0) {
			sum = new double[1][a[0].length];
			for(int i = 0; i < sum[0].length; i++) {
				for(int j = 0; j < a.length; j++) {
					sum[0][i]+= a[j][i]; 
				}
			}
		}
		// Summing across columns
		else if(axis == 1) {
			sum = new double[a.length][1];
			for(int i = 0; i < sum.length; i++) {
				for(int j = 0; j < a[0].length; j++) {
					sum[i][0]+= a[i][j]; 
				}
			}
		}
		
		return sum;
	}
	
	public static void initializeGaussian(double[][] a, double mean, double variance) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				a[i][j] = getGaussian(mean,  variance); 
			}
		}
	}
	
	
	public static void initializeRandom(double[][] a) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				a[i][j] = fRandom.nextDouble();
			}
		}
	}
	
	private static double getGaussian(double aMean, double aVariance){
	    return aMean + fRandom.nextGaussian() * aVariance;
	}
	
}