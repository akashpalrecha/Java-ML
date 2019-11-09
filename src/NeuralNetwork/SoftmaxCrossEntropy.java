package NeuralNetwork;

import Utils.DeepMath;
import java.lang.Math;

public class SoftmaxCrossEntropy extends LossFunction {

	@Override
	public double loss(double[][] input, double[] y) throws Exception {
		// TODO Auto-generated method stub
		int rows = input.length;
		int columns = input[0].length;
		this.input = input;
		this.labels = y;
		
		double[][] e_inp = DeepMath.matExp(this.input);
		double[][] e_sums = DeepMath.matrixSummation(e_inp, 1);
		double[][] soft_out = new double[rows][columns];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				soft_out[i][j] = this.input[i][j] / e_sums[i][0];
			}
		}
		// Soft_out now contains softmax outputs
		double logLoss = 0;
		for(int i = 0; i < rows; i++) {
			logLoss += Math.log(soft_out[i][(int)y[i]]);
		}
		
		logLoss = logLoss / (double)rows;
		
		this.probabilities = soft_out;
		this.loss = logLoss;
		
		return this.loss;
	}

	@Override
	public double[][] backward() throws Exception {
		// TODO Auto-generated method stub
		int rows = this.probabilities.length;
		
		double[][] grad = this.probabilities.clone();
		
		for(int i = 0; i < rows; i ++) {
			grad[i][(int)this.labels[i]] -= 1;
		}
		
		grad = DeepMath.matScale(grad, 1.0 / (double)rows);
		this.grad = grad;
		
		return grad;
	}

}
