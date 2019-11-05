import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import Utils.BinaryClassifierData;
import Utils.BinaryDataSample;
import Utils.DeepMath;

public class Experiment{
	public void exec() throws Exception{
		File f = new File("34.png");
		BufferedImage img = ImageIO.read(f);
		System.out.println(img.getHeight() + " " + img.getWidth());
		int p = img.getRGB(27, 10);
		p = (p >> 0) & 0xff;
		System.out.println(p);
	}
	
	public static void data_check()throws Exception{
		BinaryClassifierData data = new BinaryClassifierData(0.2);
		System.out.println(data.length());		
		System.out.println("\n\nPRINTING ONE DATA LINE\n\n");
		BinaryDataSample sample = data.getDataIterator("train").next();
		for(int i=0; i < sample.x.length; i++) {
			System.out.println(sample.x[i]);
		}
		System.out.println("\n\nLABEL : " + sample.y);
	}
	
	public static void matrixMultCheck() throws Exception{
		double[][] a = {
				{1.0, 2.0, 3.0, 4.0},
				{5.0, 6.0, 7.0, 8.0},
				{9.0, 10.0, 11.0, 12.0}
		};
		
		double[][] b = {
				{1.0, 2.0},
				{3.0, 4.0},
				{5.0, 6.0},
				{7.0, 8.0}
		};
		
		System.out.println(a.length + " " + a[0].length);
		System.out.println(b.length + " " + b[0].length);
		
		double[][] c = DeepMath.matmul(a, b);
		DeepMath.printMatrix(c);
	}

	public static void main(String[] args) throws Exception{
//		double[][] a = DeepMath.getRandomMatrix(3,  4);
		double[][] a = DeepMath.getRangeMatrix(4, 3, 0);
		double[][] b = DeepMath.getRangeMatrix(4, 3, 0);
		DeepMath.printMatrix(a);
		System.out.println();
		DeepMath.printMatrix(b);
		System.out.println();
		DeepMath.printMatrix(DeepMath.matElementwiseMult(a, b));
		System.out.println();
		DeepMath.printMatrix(DeepMath.matAdd(a, b));
		System.out.println();
		DeepMath.printMatrix(DeepMath.matSub(a, b));
		System.out.println();
		DeepMath.printMatrix(DeepMath.matmul(a, DeepMath.transpose(b)));
	}
}