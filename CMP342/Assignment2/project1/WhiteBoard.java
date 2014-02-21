package project1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WhiteBoard implements MouseMotionListener,MouseListener{
	private static Color drawColor = Color.black;
	static int size = 30;
	
	public static MouseEvent a;
		
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
	
	public static void getAppletSize(){
		
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
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		this.a = e;
		System.out.println("hello1");
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
