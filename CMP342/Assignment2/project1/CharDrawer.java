package project1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharDrawer implements KeyListener{
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("something might happen");
		Font font = new Font("Serif", Font.BOLD, 20);
		int x = e.getID();
		int y = e.getID();
		String s = String.valueOf(e.getKeyChar());
		Applet app = (Applet)e.getSource();
		Graphics g = app.getGraphics();
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(s,50,50);
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
