package NeuralNetwork;

import Utils.DeepMath;



public class Sigmoid extends NNModule {

	@Override
	public void initializeWeights(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public double[][] forward(double[][] input) throws Exception {
		// TODO Auto-generated method stub
		this.input = input;
		this.output = DeepMath.matExp(input);
		return this.output;
	}

	@Override
	public double[][] backward(double[][] grad_in, String method) throws Exception {
		// TODO Auto-generated method stub
		int rows = this.output.length;
		int columns = this.output[0].length;
		double[][] grad = new double[rows][columns];
		double[][] factor1 = DeepMath.getConstantMatrix(rows, columns, 1);
		factor1 = DeepMath.matSub(factor1, this.output);
		grad = DeepMath.matElementwiseMult(this.output, factor1);
		
		this.gradients_w = grad;
		return this.gradients_w;
	}

	@Override
	public void step(double lr) throws Exception {
		// TODO Auto-generated method stub

	}
	

}
