package NeuralNetwork;

public abstract class LossFunction {
	
	double loss;
	double[][] input;
	double[][] probabilities;
	double[] labels;
	double[][] grad;
	
	public abstract double loss(double[][] input, double[] y) throws Exception;
	
	public abstract double[][] backward() throws Exception;
	
}
