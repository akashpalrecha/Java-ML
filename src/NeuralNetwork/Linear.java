package NeuralNetwork;

import Utils.DeepMath;

public class Linear extends NNModule {
	
	public int features_in;
	public int features_out;
	
	public Linear(int features_in, int features_out) {
		this.features_in = features_in;
		this.features_out = features_out;
		
		this.weights = new double[features_in][features_out];
		this.gradients_w = new double[features_in][features_out];
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
		this.bias = new double[1][this.features_out];
		for(int i = 0; i < this.features_out; i++) {
			this.bias[0][i] = 0.0; 
		}

	}

	@Override
	public double[][] forward(double[][] input) throws Exception{
		this.input = input;
		this.output = DeepMath.matmul(input, this.weights);
		this.output = DeepMath.matAdd(this.output, this.bias);
		return this.output;
	}

	@Override
	public double[][] backward(double[][] grad_in, String method) throws Exception{
		this.gradients_inp = DeepMath.matmul(grad_in, DeepMath.transpose(this.weights));
		double[][] t_gradients_w = DeepMath.matmul(DeepMath.transpose(this.input), grad_in);
		double[][] t_gradients_bias = grad_in;
		
		if(method.equals("add")) {
			this.gradients_bias = DeepMath.matAdd(this.gradients_bias, gradients_bias);
			this.gradients_w = DeepMath.matAdd(this.gradients_w, gradients_w);
		}
		else if(method.equals("zero")) {
			this.gradients_bias = t_gradients_bias;
			this.gradients_w = t_gradients_w;
		}
		
		return this.gradients_inp;
	}


	@Override
	public void step(double lr) throws Exception {
		this.weights = DeepMath.matSub(this.weights, DeepMath.matScale(this.gradients_w, lr));
		this.bias = DeepMath.matSub(this.bias, DeepMath.matScale(this.gradients_bias, lr));
	}

}
