package NeuralNetwork;

import Utils.DeepMath;

public class TanSigmoid extends NNModule {

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
		
		// Out now contains sigmoid output
		double[][] oneMatrix = DeepMath.getConstantMatrix(rows,  columns,  1.0);
		double[][] out = DeepMath.matSigmoid(input);
		out = DeepMath.matScale(out,  2.0);
		out = DeepMath.matSub(out, oneMatrix);
		
		this.output = out;
		return this.output;
	}

	@Override
	public double[][] backward(double[][] grad_in, String method) throws Exception {
		// TODO Auto-generated method stub
		int rows = this.output.length;
		int columns = this.output[0].length;
		
		double[][] sig_out = DeepMath.matSigmoid(input);
		double[][] grad = new double[rows][columns];
		double[][] factor1 = DeepMath.getConstantMatrix(rows, columns, 1);
		factor1 = DeepMath.matSub(factor1, sig_out);
		grad = DeepMath.matElementwiseMult(sig_out, factor1);
		grad = DeepMath.matScale(grad,  2.0);
		
		this.gradients_w = grad;
		return this.gradients_w;
	}

	@Override
	public void step(double lr) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void zero_grad() throws Exception {
		// TODO Auto-generated method stub

	}

}
