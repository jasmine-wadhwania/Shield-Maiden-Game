package shield;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class display {//other functions shud not hv access to this code
	private JFrame frame;
	private Canvas canvas;//creates a canvas for graphics
	private String title;
	public static int width;
	public static int height;
	public display(String title,int width,int height)
	{
		this.title=title;//this is used becoz of ambiguity between the variables,i.e,both hv same namesa on lhs and rhs
		this.width=width;
		this.height=height;
		createDisplay();
	}
	private void createDisplay()
	{
		frame=new JFrame(title);
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//to prevent game from running in background after pressing Xbutton
		frame.setLocationRelativeTo(null);//window opens in middle on first opening
		frame.setVisible(true);//to make the JFrame visible
		frame.setResizable(false);
		canvas=new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));//canvas takes a dimension object
		canvas.setMaximumSize(new Dimension(width,height));//ensures tht canvas stays in the frame
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);//helps jframe to be focused on rather thn wat is being drawn
		frame.add(canvas);
		frame.pack();//adjusts awad so tht we can view full canvas
		
	}
	public Canvas getCanvas()//to get access to canvas in game class
	{
		return canvas;
	}
	public JFrame getFrame()
	{
		return frame;
	}
}