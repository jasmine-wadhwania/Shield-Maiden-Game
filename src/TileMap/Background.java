package TileMap;

import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;

import shield.display;
import shield.gfx.ImageLoader;

public class Background {
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;

	private double moveScale;

	public Background(String s,double ms)
	{
		try	
		{
			image=ImageIO.read(ImageLoader.class.getResource(s));
		}


		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void setPosition(double x,double y)
	{
		this.x=(x*moveScale)%display.width;
		this.y=(y*moveScale)%display.height;

	}
	//auto background scrolling
	public void setVector(double dx,double dy)
	{
		this.dx=dx;
		this.dy=dy;
}
public void update()
{
x+=dx;
y+=dy;
}

public void draw(Graphics g)
{
g.drawImage(image,(int)x,(int)y,display.width,display.height,null);

}}