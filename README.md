This is part of my project for the Machine Learning course in college.

We were asked to implement a neural network in java, which is generalized enough to have an arbitrary number of layers, arbitrary ordering of activation functions, and can work with different machine learning datastes.

All of this was supposed to be done without using any libraries such as deeplearning4j, etc. 

I will be honest, having to give up Python's NumPy powers did slow down a lot of things while coding the whole project, and it also created a lot of unnecessary issues such as numerical instabilities and divide by zero errors, vanishing gradients, exploding matrices, etc. But having to solve all of this using plain vanilla Java was an education in itself. It helped me much better appreciate popular data science libraries such as Pandas, NumPy, PyTorch, Tensorflow, etc.

---

While using just plain java, I've managed to reduce the amount of code required to train a neural network to this (see learner.java for more complete code):
```java
    String train_csv = "Files/iris/iris_x.csv";
    String labels_csv = "Files/iris/iris_y.csv";
    ClassifierData data = new ClassifierData(train_csv, labels_csv, 0.2);
    LossFunction lossfn = new SoftmaxCrossEntropy();
    
    // NNModule can be as big as we want
    NNModule[] relu_network = new NNModule[3];
    // Initialize all layers in order
    relu_network[0] = new Linear(4, 3);
    relu_network[1] = new ReLu();
    relu_network[2] = new Linear(3,  3);
    
    Optimizer relu_opt = new Optimizer(lossfn, relu_network, data);
    double[] relu_metrics = relu_opt.fit(0.0001, 10, 4); //lr, epochs, batch-size
    // relu_metrics contains the final validation loss and validation accuracy
    System.out.println("ReLu model Results:\nTraining accuracy: "+relu_metrics[0]+"\tValidation Accuracy: "+relu_metrics[1] + "\n");
```
The `iris_x.csv` file needs to be preprocessed (no missing / badly formatted values) with the top row containing only column names. (The file can be of any arbitrary dataset)

The `iris_y.csv` should not have a column name. All labels for the corresponding training sample should be in separate rows. Labels need to be indexed from `0` to `num_classes - 1` depending on the specific dataset.

---

This package supports both binary classification and multi-class classification datasets out of the box.

I have also written math functions in `Utils/DeepMath.java` to help with experimentation. You can use these to:

- Generate random matrices of desired size
- Generate range matrices of desired size
- Generate constant matrices of desired size
- Print matrices
- Perform matrix multiplication
- Perform element-wise addition, subtraction, etc.
- Perform sigmoid op on matrices
- etc.

Other than `ReLu`, I have implemented the `Sigmoid` and `TanSigmoid` activation function modules

You can design your own activation functions / neural network modules which will be compatible with this framework by subclassing the `NNModule` class.

Similarly, you can subclass the `LossFunction` class to design your own loss functions.
