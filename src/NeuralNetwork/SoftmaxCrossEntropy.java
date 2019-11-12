package NeuralNetwork;

import Utils.DeepMath;
import java.lang.Math;

public class SoftmaxCrossEntropy extends LossFunction {

	@Override
	public double loss(double[][] input, int[] y) throws Exception {
		// TODO Auto-generated method stub
		double eps = 1e-3;
		int rows = input.length;
		int columns = input[0].length;
		this.input = input;
		this.labels = y;
		
		double[][] e_inp = DeepMath.matExp(this.input);
		double[][] e_sums = DeepMath.matrixSummation(e_inp, 1);
		double[][] soft_out = new double[rows][columns];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				soft_out[i][j] = e_inp[i][j] / e_sums[i][0];
			}
		}
		// Soft_out now contains softmax outputs
		double logLoss = 0;
		for(int i = 0; i < rows; i++) {
			logLoss -= Math.log(eps + soft_out[i][y[i]]);
		}
		for(int i = 0; i < rows; i++) {
			int argmax = 0;
			for(int j = 1; j < columns; j++) {
				if( soft_out[i][j] > soft_out[i][j - 1]) {
					argmax = j;
				}
			}
			if(argmax == y[i]) {
				this.correct_predictions.add(true);
			}
			else {
				this.correct_predictions.add(false);
			}
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
			grad[i][this.labels[i]] -= 1;
		}
		
		grad = DeepMath.matScale(grad, 1.0 / (double)rows);
		this.grad = grad;
		
		return grad;
	}

}
