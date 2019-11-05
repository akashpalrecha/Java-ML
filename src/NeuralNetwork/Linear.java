package NeuralNetwork;

import Utils.DeepMath;

public class Linear extends NNModule {
	
	public int features_in;
	public int features_out;
	
	public Linear(int features_in, int features_out) {
		this.features_in = features_in;
		this.features_out = features_out;
		
		this.weights = new double[features_in][features_out];
		this.gradients = new double[features_in][features_out];
		this.initializeWeights("gaussian");
	}
	
	
	@Override
	public void initializeWeights(String type) {
		if(type.equals("random")) {
			DeepMath.initializeRandom(this.weights);
		}
		else if(type.equals("gaussian")) {
			DeepMath.initializeGaussian(this.weights, 0.0, 1.0);
		}

	}

	@Override
	public double[][] forward(double[][] input) throws Exception{
		this.input = input;
		this.output = DeepMath.matmul(input, this.weights);
		return this.output;
	}

	@Override
	public double[][] backward(double[][] grad_in) {
		// TODO Auto-generated method stub
		return null;l
	}

	@Override
	public void save_gradients(double[][] grad, String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(double lr) {
		// TODO Auto-generated method stub

	}

}
