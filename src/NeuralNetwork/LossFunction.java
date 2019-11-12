package NeuralNetwork;

import java.util.ArrayList;

public abstract class LossFunction {
	
	double loss;
	double[][] input;
	double[][] probabilities;
	int[] labels;
	double[][] grad;
	ArrayList<Boolean> correct_predictions;
	
	public abstract double loss(double[][] input, int[] y) throws Exception;
	
	public abstract double[][] backward() throws Exception;
	
	public double accuracy() {
		int n_correct = 0;
//		boolean pred;
		for(boolean pred:this.correct_predictions) {
			if(pred == true) {
				n_correct++;
			}
		}
		return (double)n_correct / (double)this.correct_predictions.size();
	}
	
	public void initializePredictions() {
		correct_predictions = new ArrayList<Boolean>();
	}
}
