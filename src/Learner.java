import NeuralNetwork.Linear;
import NeuralNetwork.LossFunction;
import NeuralNetwork.NNModule;
import NeuralNetwork.ReLu;
import NeuralNetwork.Sigmoid;
import NeuralNetwork.SigmoidCrossEntropyLoss;
import NeuralNetwork.SoftmaxCrossEntropy;
import Utils.ClassifierData;
import Utils.DeepMath;


public class Learner {
	public static void main(String[] args) throws Exception{
		// IRIS DATA
		String train_csv = "Files/iris/iris_x.csv";
		String labels_csv = "Files/iris/iris_y.csv";
		ClassifierData data = new ClassifierData(train_csv, labels_csv, 0.2);
		LossFunction lossfn = new SoftmaxCrossEntropy();

		NNModule[] relu_network = new NNModule[3];
		relu_network[0] = new Linear(4, 3);
		relu_network[1] = new ReLu();
		relu_network[2] = new Linear(3,  3);
		
		NNModule[] sigmoid_network = new NNModule[3];
		sigmoid_network[0] = new Linear(4, 3);
		sigmoid_network[1] = new Sigmoid();
		sigmoid_network[2] = new Linear(3,  3);
		
		NNModule[] identity_network = new NNModule[3];
		identity_network[0] = new Linear(4, 3);
		identity_network[1] = new ReLu();
		identity_network[2] = new Linear(3, 3);
		
		NNModule[] tansigmoid_network = new NNModule[3];
		tansigmoid_network[0] = new Linear(4, 3);
		tansigmoid_network[1] = new ReLu();
		tansigmoid_network[2] = new Linear(3, 3);
		
		// Kaggle Email Spam Classifier Data
//		String train_csv = "Files/email-spam-detection/processed_train.csv";
//		String labels_csv = "Files/email-spam-detection/train_labels.csv";
//		ClassifierData data = new ClassifierData(train_csv, labels_csv, 0.3);
//		LossFunction lossfn = new SigmoidCrossEntropyLoss();
//			
//		NNModule[] relu_network = new NNModule[3];
//		relu_network[0] = new Linear(100, 20);
//		relu_network[1] = new ReLu();
//		relu_network[2] = new Linear(20,  1);
//		
//		NNModule[] sigmoid_network = new NNModule[3];
//		sigmoid_network[0] = new Linear(100, 20);
//		sigmoid_network[1] = new Sigmoid();
//		sigmoid_network[2] = new Linear(20,  1);
//		
//		NNModule[] identity_network = new NNModule[3];
//		identity_network[0] = new Linear(100, 20);
//		identity_network[1] = new ReLu();
//		identity_network[2] = new Linear(20,  1);
//		
//		NNModule[] tansigmoid_network = new NNModule[3];
//		tansigmoid_network[0] = new Linear(100, 20);
//		tansigmoid_network[1] = new ReLu();
//		tansigmoid_network[2] = new Linear(20,  1);
		
		// Comment out the above section to run the multi class classifier
		
		// Make Optimizer
		Optimizer relu_opt = new Optimizer(lossfn, relu_network, data);
		relu_opt.fit(0.01, 5, 4);
		relu_opt.fit(0.001, 5, 4);
		double[] relu_metrics = relu_opt.fit(0.0001, 10, 4);
		
		Optimizer sigmoid_opt = new Optimizer(lossfn, sigmoid_network, data);
		sigmoid_opt.fit(0.01, 5, 4);
		sigmoid_opt.fit(0.001, 5, 4);
		double[] sigmoid_metrics = sigmoid_opt.fit(0.0001, 10, 4);

		Optimizer identity_opt = new Optimizer(lossfn, identity_network, data);
		identity_opt.fit(0.01, 5, 4);
		identity_opt.fit(0.001, 5, 4);
		double[] identity_metrics = identity_opt.fit(0.0001, 10, 4);
		
		Optimizer tansigmoid_opt = new Optimizer(lossfn, tansigmoid_network, data);
		tansigmoid_opt.fit(0.01, 5, 4);
		tansigmoid_opt.fit(0.001, 5, 4);
		double[] tansigmoid_metrics = tansigmoid_opt.fit(0.0001, 10, 4);
		
//		Optimizer opt = new Optimizer(lossfn, network, data);
//		opt.fit(0.01, 5, 4);
//		opt.fit(0.001, 5, 4);
//		double[] metrics = opt.fit(0.0001, 10, 4);
		
		System.out.println("\n\n----------------\n\n");
		System.out.println("ReLu Results:\nTraining accuracy: "+relu_metrics[0]+"\tValidation Accuracy: "+relu_metrics[1] + "\n");
		System.out.println("Sigmoid Results:\nTraining accuracy: "+sigmoid_metrics[0]+"\tValidation Accuracy: "+sigmoid_metrics[1] + "\n");
		System.out.println("Identity Results:\nTraining accuracy: "+identity_metrics[0]+"\tValidation Accuracy: "+identity_metrics[1] + "\n");
		System.out.println("Tansigmoid Results:\nTraining accuracy: "+tansigmoid_metrics[0]+"\tValidation Accuracy: "+tansigmoid_metrics[1] + "\n");
	}
}
