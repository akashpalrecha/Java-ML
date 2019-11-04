import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class BinaryClassifierData {
	public String path;
	public String train_path;
	public String label_path;
	public double val_split;
	public ArrayList<BinaryDataSample> DATA;
	
	public BinaryClassifierData(double val_split) throws FileNotFoundException {
		this.path = "Files/Binary-classifier-data/";
		this.train_path = this.path + "processed_train.csv";
		this.label_path = this.path + "train_labels.csv";
		this.val_split = val_split;
		DATA = new ArrayList<BinaryDataSample>();
		this.populateData();
	}
	
	public void populateData() throws FileNotFoundException{
		File X_data = new File(this.train_path);
		File Y_data = new File(this.label_path);
		Scanner train = new Scanner(new FileReader(X_data));
		Scanner label = new Scanner(new FileReader(Y_data));
		train.next(); // Ignore Column Header line
		
		// Start reading in Data
		while(train.hasNext() && label.hasNext()) {
			String[] x_str = train.next().split(",");
			int y = Integer.parseInt(label.next());
			
			double[] x = new double[x_str.length];
			for(int i = 0; i < x_str.length; i++) {
				x[i] = Double.parseDouble(x_str[i]);
			}
			this.DATA.add(new BinaryDataSample(x, y));
			
		}
	}
	
	public int length() throws FileNotFoundException{
		return this.DATA.size();
	}

}
