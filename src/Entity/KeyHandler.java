package Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean upPressed,downPressed,rightPressed,leftPressed,spacePressed,enterPressed;
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		int code =arg0.getKeyCode();
		switch(code)
		{
		case KeyEvent.VK_W:
		{upPressed=true;}break;
		case KeyEvent.VK_D:{
			rightPressed=true;
		}break;
		case KeyEvent.VK_S:{
			downPressed=true;
		}break;
		case KeyEvent.VK_A:{
			leftPressed=true;
		}break;
		case KeyEvent.VK_SPACE:{
			spacePressed=true;
		}break;
		case KeyEvent.VK_ENTER:{
			enterPressed=true;
		}break;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		int code =arg0.getKeyCode();
		switch(code)
		{
		case KeyEvent.VK_W:
		{upPressed=false;}break;
		case KeyEvent.VK_D:{
			rightPressed=false;
		}break;
		case KeyEvent.VK_S:{
			downPressed=false;
		}break;
		case KeyEvent.VK_A:{
			leftPressed=false;
		}break;
		case KeyEvent.VK_ENTER:{
			enterPressed=false;
		}break;
		case KeyEvent.VK_SPACE:{
			spacePressed=false;
		}break;
		}
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
