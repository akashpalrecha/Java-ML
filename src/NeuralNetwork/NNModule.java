package NeuralNetwork;

public abstract class NNModule {
	
	public double[][] weights;
	public double[][] bias;
	public double[][] gradients;
	public double[][] input;
	public double[][] output;
	
	public abstract void initializeWeights(String type);
	
	public abstract double[][] forward(double[][] input) throws Exception;
	
	public abstract double[][] backward(double[][] grad_in);
	
	public abstract void save_gradients(double[][] grad, String type);
	
	public abstract void step(double lr);

}
