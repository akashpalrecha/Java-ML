package Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class ClassifierData {
	
	public String train_path;
	public String label_path;
	public double val_split;
	public ArrayList<DataSample> DATA;
	public ArrayList<DataSample> TRAIN_DATA;
	public ArrayList<DataSample> VALID_DATA;
	
	public ClassifierData(String train, String labels, double val_split) throws FileNotFoundException {
//		this.path = "Files/Binary-classifier-data/";
//		this.train_path = this.path + "processed_train.csv";
//		this.label_path = this.path + "train_labels.csv";
		this.train_path = train;
		this.label_path = labels;
		this.val_split = val_split;
		DATA = new ArrayList<DataSample>();
		TRAIN_DATA = new ArrayList<DataSample>();
		VALID_DATA = new ArrayList<DataSample>();
		this.populateData();
		this.splitData();
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
			
			double[][] x = new double[1][x_str.length];
			for(int i = 0; i < x_str.length; i++) {
				x[0][i] = Double.parseDouble(x_str[i]);
			}
			this.DATA.add(new DataSample(x, y));
			
		}
		train.close();
		label.close();
	}
	
	public int length() throws FileNotFoundException{
		return this.DATA.size();
	}
	
	public Iterator<DataSample> getDataIterator(String type) {
		if(type.equals("train")) {
			return this.TRAIN_DATA.iterator();
		}
		else if(type.equals("valid")) {
			return this.VALID_DATA.iterator();
		}
		else {
		return this.DATA.iterator();
		}
	}
	
	public void splitData() {
		int split = (int)(this.DATA.size() * this.val_split);
		
		for(int i = 0; i < split; i++) {
			this.VALID_DATA.add(this.DATA.get(i));
		}
		for(int i = split; i < this.DATA.size(); i++) {
			this.TRAIN_DATA.add(this.DATA.get(i));
		}
	}

}
