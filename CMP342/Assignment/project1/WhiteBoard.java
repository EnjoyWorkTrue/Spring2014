package project1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JApplet;

public class WhiteBoard implements MouseMotionListener{
	private static Color drawColor = Color.black;
	static int size = 30;
		
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Applet app = (Applet)e.getSource();
		Graphics g = app.getGraphics();
		g.setColor(drawColor);
		g.fillOval(x, y, size, size);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	public static void bigger(){
		size+=5;
	}
	public static void smaller(){
		size-=5;
	}
	
	public static void changeColor(Color s){
		drawColor=s;
	}

	public static Color getColor() {
		// TODO Auto-generated method stub
		return drawColor;
	}
	
}
