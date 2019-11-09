package NeuralNetwork;

import Utils.DeepMath;

public class Softmax_cross_entropy extends NNModule {

	@Override
	public void initializeWeights(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public double[][] forward(double[][] input) throws Exception {
		// TODO Auto-generated method stub
		int rows = input.length;
		int columns = input[0].length;
		this.input = input;
		double[][] e_inp = DeepMath.matExp(this.input);
		double[][] e_sums = DeepMath.matrixSummation(e_inp, 1);
		double[][] out = new double[rows][columns];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				out[i][j] = this.input[i][j] / e_sums[i][0];
			}
		}
		
		this.output = out;
		return this.output;
	}

	@Override
	public double[][] backward(double[][] grad_in, String method) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void step(double lr) throws Exception {
		// TODO Auto-generated method stub

	}

}
