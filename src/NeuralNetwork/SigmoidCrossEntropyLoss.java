package NeuralNetwork;

import Utils.DeepMath;

public class SigmoidCrossEntropyLoss extends LossFunction {

	@Override
	public double loss(double[][] input, double[] y) throws Exception {
		// TODO Auto-generated method stub
		int rows = input.length;
		int columns = input[0].length;
		this.input = input;
		this.labels = y;
		
//		input = input.clone();
		input = DeepMath.matSub(DeepMath.getConstantMatrix(rows,  columns, 0), input); 
		
		double[][] e_inp  =DeepMath.matExp(input);
		double[][] oneMatrix = DeepMath.getConstantMatrix(rows,  columns,  1);
		
		double[][] out = DeepMath.matElementwiseDiv(oneMatrix, DeepMath.matAdd(e_inp, oneMatrix));
		
		double loss = 0.0;
		
		for(int i = 0; i < rows; i++) {
			if(y[i] == 0) {
				loss += Math.log(out[i][0]);
			}
			else if(y[i] == 1) {
				loss += Math.log(1 - out[i][0]);
			}
			else {
				loss += (1 - y[i]) * Math.log(out[i][0]) + y[i] * Math.log(1 - out[i][0]);
			}
		}
		
		this.probabilities = out;
		this.loss = loss / rows;
		return this.loss;
	}

	@Override
	public double[][] backward() throws Exception {
		// TODO Auto-generated method stub
		this.grad = this.probabilities.clone();
		for(int i = 0; i < this.grad.length; i++) {
			this.grad[i][0] -= this.labels[i];
		}
		return this.grad;
	}

}
