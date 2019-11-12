package Utils;

public class DataSample {
	public double[][] x;
	public int[] y;
	
	public DataSample(double[][] x, int y) {
		this.x = x;
//		double[][] temp = new double[0][5];
//		for(int i = 0; i < 5; i++) {
//			temp[0][i] = x[0][i];
//		}
//		this.x = temp;
		
		this.y = new int[1];
		this.y[0] = y;
	}

	public double[][] getX() {
		return x;
	}

	public int[] getY() {
		return y;
	}
	
	
}
