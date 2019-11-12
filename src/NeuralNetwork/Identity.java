package NeuralNetwork;

public class Identity extends NNModule {

	@Override
	public void initializeWeights(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public double[][] forward(double[][] input) throws Exception {
		// TODO Auto-generated method stub
		this.input = input;
		this.output = input;
		return this.output;
	}

	@Override
	public double[][] backward(double[][] grad_in, String method) throws Exception {
		// TODO Auto-generated method stub
		this.gradients_inp = grad_in;
		return this.gradients_inp;
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
