package NeuralNetwork;

public abstract class NNModule {
	
	public double[][] weights;
	public double[][] bias;
	public double[][] gradients_w;
	public double[][] gradients_inp;
	public double[][] gradients_bias;
	public double[][] input;
	public double[][] output;
	
	public abstract void initializeWeights(String type);
	
	public abstract double[][] forward(double[][] input) throws Exception;
	
	public abstract double[][] backward(double[][] grad_in, String method) throws Exception;
	
	public abstract void step(double lr) throws Exception;

}
