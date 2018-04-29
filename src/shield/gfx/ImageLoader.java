package shield.gfx;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class ImageLoader {
//buffered image is javas way of storing images
   public static BufferedImage loadImage(String path)
   {
   	try
   	{//loads image in game ie, returns buffered image object of the image we loaded
   	return ImageIO.read(ImageLoader.class.getResource(path));
   	}
   	catch(IOException e)
   	{
   	e.printStackTrace();
   	System.exit(1);//without image game should not load
   	}
   	return null;
   }
}