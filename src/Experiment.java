import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import Utils.BinaryClassifierData;
import Utils.BinaryDataSample;

public class Experiment{
	public void exec() throws Exception{
		File f = new File("34.png");
		BufferedImage img = ImageIO.read(f);
		System.out.println(img.getHeight() + " " + img.getWidth());
		int p = img.getRGB(27, 10);
		p = (p >> 0) & 0xff;
		System.out.println(p);
	}
	
	public static void main(String[] args)throws Exception{
		BinaryClassifierData data = new BinaryClassifierData(0.2);
		System.out.println(data.length());		
		System.out.println("\n\nPRINTING ONE DATA LINE\n\n");
		BinaryDataSample sample = data.getDataIterator("train").next();
		for(int i=0; i < sample.x.length; i++) {
			System.out.println(sample.x[i]);
		}
		System.out.println("\n\nLABEL : " + sample.y);
	}
}