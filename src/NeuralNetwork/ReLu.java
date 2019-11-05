package NeuralNetwork;

public class ReLu extends NNModule {

	@Override
	public void initializeWeights(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public double[][] forward(double[][] input) throws Exception {
		// TODO Auto-generated method stub
		this.input = input;
		double[][] out = new double[input.length][input[0].length];
		for(int i = 0; i < out.length; i++) {
			for(int j = 0; j < out[0].length; j++) {
				if(input[i][j] > 0.0) {
					out[i][j]= input[i][j]; 
				}
				else {
					out[i][j]= 0.0; 
				}
			}
		}
		this.output = out;
		return this.output;
	}

	@Override
	public double[][] backward(double[][] grad_in, String method) throws Exception {
		// TODO Auto-generated method stub
		double[][] relu_grad = new double[this.output.length][this.output[0].length];
		for(int i = 0; i < relu_grad.length; i++) {
			for(int j = 0; j < relu_grad[0].length; j++) {
				if(this.output[i][j] > 0.0) {
					relu_grad[i][j]= grad_in[i][j]; 
				}
				else {
					relu_grad[i][j]= 0.0; 
				}
			}
		}
		
		this.gradients_inp = relu_grad;
		return this.gradients_inp;
	}

	@Override
	public void step(double lr) throws Exception {
		// TODO Auto-generated method stub

	}

}
