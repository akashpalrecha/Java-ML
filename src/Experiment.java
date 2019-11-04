import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

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
	}
}