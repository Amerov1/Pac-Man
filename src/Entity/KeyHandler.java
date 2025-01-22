package Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.StateOfGame;

public class KeyHandler implements KeyListener{
	private char direction= ' ',preDirection= ' ';
	private boolean enterPressed=false,spacePressed=false;
	private StateOfGame sog;
	public void setSpacePressed(boolean spacePressed)
	{
		this.spacePressed=spacePressed;
	}
	public KeyHandler(StateOfGame state)
	{
		sog=state;
	}
	public boolean getEnterPressed()
	{
		return enterPressed;
	}
	public boolean getSpacePressed()
	{
		return spacePressed;
	}
	public StateOfGame getState()
	{
		return sog;
	}
	public char getPreDirection()
	{
		return preDirection;
	}
	public void setPreDirection(char d)
	{
		preDirection=d;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {}
	@Override
	public void keyReleased(KeyEvent arg0) {
		int code =arg0.getKeyCode();
		
		switch(code)
		{
		case KeyEvent.VK_W:
		{setDirection('U');}break;
		case KeyEvent.VK_D:{
			setDirection('R');
		}break;
		case KeyEvent.VK_S:{
			setDirection('D');
		}break;
		case KeyEvent.VK_A:{
			setDirection('L');
		}break;
		case KeyEvent.VK_ENTER:{
			if(sog==StateOfGame.Start)
			enterPressed=!enterPressed;
		}break;
		case KeyEvent.VK_SPACE:{
			if(sog!=StateOfGame.Start)
			spacePressed=!spacePressed;
		}break;
		default:
		    break;
		}
		
	}
	public void setDirection(char d)
	{
		preDirection=direction;
		direction=d;

	}
	public char getDirection()
	{
		return direction;
	}
	public void update(StateOfGame s)
	{
		sog=s;
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}

}
