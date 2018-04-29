package shield.states;
import shield.game;
import java.awt.Graphics;

public abstract class state
{
private static state currentstate=null;

public static void setState(state State)
{
currentstate=State;
}
public static state getstate()
{
return currentstate;
}
protected game Game;
public state(game Game)
{
this.Game=Game;
}

//class
public abstract void tick();
public abstract void render(Graphics g);

}