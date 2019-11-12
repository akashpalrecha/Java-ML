import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import NeuralNetwork.Linear;
import NeuralNetwork.ReLu;
import Utils.ClassifierData;
import Utils.DataSample;
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
////		ClassifierData data = new ClassifierData(0.2);
//		System.out.println(data.length());		
//		System.out.println("\n\nPRINTING ONE DATA LINE\n\n");
//		DataSample sample = data.getDataIterator("train").next();
//		for(int i=0; i < sample.x[0].length; i++) {
//			System.out.println(sample.x[0][i]);
//		}
//		System.out.println("\n\nLABEL : " + sample.y[0]);
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

	public static void matrixOperationsCheck() throws Exception{
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
	
	public static void linearCheck() throws Exception{
		Linear lin = new Linear(4,  10);
		double[][] x = DeepMath.getRangeMatrix(1, 4, 1);
		double[][] out = lin.forward(x);
//		DeepMath.printMatrix(out);
		double[][] grad = lin.backward(out, "zero");
		DeepMath.printMatrix(grad);
		System.out.println();
		DeepMath.printMatrix(lin.gradients_bias);
		System.out.println();
		DeepMath.printMatrix(lin.gradients_w);
		System.out.println();
		DeepMath.printMatrix(lin.gradients_inp);
	}
	
	public static void reluCheck() throws Exception{
		ReLu relu = new ReLu();
		double[][] x = DeepMath.getRangeMatrix(4, 6, -6);
		double[][] out = relu.forward(x);
		double[][] grad= relu.backward(DeepMath.getRangeMatrix(4, 6, 1), "add");
		DeepMath.printMatrix(x);
		System.out.println();
		DeepMath.printMatrix(out);
		System.out.println();
		DeepMath.printMatrix(DeepMath.getRangeMatrix(4, 6, 1));
		System.out.println();
		DeepMath.printMatrix(grad);
	}
	
	
	
//	public static void main(String[] args) throws Exception{
////		linearCheck();
////		reluCheck();
//		data_check();
//		
//	}
	
}