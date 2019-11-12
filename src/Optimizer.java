import java.util.ArrayList;
import java.util.Iterator;

import NeuralNetwork.LossFunction;
import NeuralNetwork.NNModule;
import Utils.ClassifierData;
import Utils.DataSample;

public class Optimizer {
	LossFunction lossFunc;
	NNModule[] network;
	ClassifierData data;
	
	public Optimizer(LossFunction lossFn, NNModule[] net, ClassifierData data) {
		this.lossFunc = lossFn;
		this.network = net;
		this.data = data;
	}
	
	public double[] fit(double lr, int epochs, int batch_size) throws Exception {
		int train_size = this.data.TRAIN_DATA.size();
		int valid_size = this.data.VALID_DATA.size();
		double train_acc = 0.0;
		double valid_acc = 0.0;
		
		int cur_batch = 0;
		DataSample input;
		double[][] output = new double[1][1];
		ArrayList<Double> loss = new ArrayList<Double>();
		
		for(int epoch = 0; epoch < epochs; epoch++) {
			Iterator<DataSample> train = this.data.getDataIterator("train");
			Iterator<DataSample> valid = this.data.getDataIterator("valid");
			
			// Training Loop
			lossFunc.initializePredictions();
			this.zero_grad();
			cur_batch = 0;
			
			for(int i = 0; i < train_size; i++) {
				
				// Forward Prop
				input = train.next();
				cur_batch++;
				output = this.forward_prop(input.x);
				loss.add(lossFunc.loss(output,  input.y));
				
				// Backward prop
				double[][] last_gradient = lossFunc.backward();
				this.backward_prop(last_gradient);
				
				// Printing details and performing learning rate step
				if(cur_batch % batch_size == 0 || cur_batch % train_size == 0) {
					System.out.println("Epoch: "+ (epoch+1) +"\tBatch: "+(cur_batch / (epoch+1)) + "\tLoss: "+averageLastNitems(loss, batch_size));
					
					// Learning rate step
					this.lr_step(lr);
					this.zero_grad();
				}
			}
			System.out.println("Training Accuracy: "+lossFunc.accuracy());
			train_acc = lossFunc.accuracy();
			
//			 Validation loop
			lossFunc.initializePredictions();
			cur_batch = 0;
			for(int i = 0; i < valid_size; i++) {
				input = valid.next();
				cur_batch++;
				output = this.forward_prop(input.x);
				loss.add(lossFunc.loss(output, input.y));
			}
			System.out.println("Epoch: "+(epoch+1)+"\tValidation Set accuracy: "+(lossFunc.accuracy()) + "\tLoss: "+averageLastNitems(loss, valid_size));
			this.zero_grad();
			valid_acc = lossFunc.accuracy();
			
		}
		double[] metrics = {train_acc, valid_acc};
		return metrics;
	}
	
	public double averageLastNitems(ArrayList<Double> loss, int n) {
		double average = 0.0;
		for(int i = loss.size() - 1; i >= loss.size() - n; i--) {
			average += loss.get(i);
		}
		return average / n;
	}
	
	public double[][] forward_prop(double[][] input) throws Exception{
		double [][] output = new double[1][1];
		for(int m = 0; m < this.network.length; m++) {
			if( m == 0 ) {
				output = this.network[m].forward(input);
			}
			else {
				output = this.network[m].forward(output);
			}
		}
		
		return output;
	}
	
	public void backward_prop(double[][] last_gradient) throws Exception {
		for(int m = this.network.length - 1; m >= 0; m--) {
			last_gradient = this.network[m].backward(last_gradient, "add");
		}
	}
	
	public void lr_step(double lr_rate) throws Exception {
		for(int m = 0; m < this.network.length; m++) {
			network[m].step(lr_rate);
		}
	}
	
	public void zero_grad() throws Exception{
		for(int m = 0; m < this.network.length; m++) {
			network[m].zero_grad();
		}
	}
}
