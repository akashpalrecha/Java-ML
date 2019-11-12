package NeuralNetwork;

import Utils.DeepMath;

public class SigmoidCrossEntropyLoss extends LossFunction {

	public SigmoidCrossEntropyLoss() {
		// TODO Auto-generated constructor stub
		this.initializePredictions();
	}
	@Override
	public double loss(double[][] input, int[] y) throws Exception {
		// TODO Auto-generated method stub
		int rows = input.length;
		int columns = input[0].length;
		this.input = input;
		this.labels = y;
		
		input = DeepMath.matSub(DeepMath.getConstantMatrix(rows,  columns, 0), input); 
		
		double[][] e_inp  =DeepMath.matExp(input);
		double[][] oneMatrix = DeepMath.getConstantMatrix(rows,  columns,  1);
		
		// Out now contains sigmoid output
		double[][] out = DeepMath.matElementwiseDiv(oneMatrix, DeepMath.matAdd(e_inp, oneMatrix));
		
		double loss = 0.0;
		
		for(int i = 0; i < rows; i++) {
			if(y[i] == 0) {
				loss -= Math.log(1 - out[i][0]);
				if(out[i][0] <= 0.5) {
					this.correct_predictions.add(true);
				}
				else {
					this.correct_predictions.add(false);
				}
			}
			else if(y[i] == 1) {
				loss -= Math.log(out[i][0]);
				if(out[i][0] > 0.5) {
					this.correct_predictions.add(true);
				}
				else {
					this.correct_predictions.add(false);
				}
			}
			else {
				loss -= (1 - (double)y[i]) * Math.log(1 - out[i][0]) + (double)y[i] * Math.log(out[i][0]);
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
			this.grad[i][0] -= (double)this.labels[i];
		}
		return this.grad;
	}

}
