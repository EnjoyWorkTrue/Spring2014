package project1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WhiteBoard extends Panel implements MouseMotionListener,MouseListener,KeyListener{
	private static Color drawColor = Color.black;
	private static Color textColor = Color.black;
	protected FontMetrics fm;
	static int size = 30;
	private int thisx;
	private int thisy;
	
	public static MouseEvent a;
	
	public WhiteBoard(){
		Font font = new Font("Serif", Font.BOLD, 20);
		fm = getFontMetrics(font);
		setFont(font);
		setBackground(Color.white);
		setForeground(drawColor);		
		addKeyListener(this);
	    addMouseMotionListener(this);
	    addMouseListener(this);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Graphics g = getGraphics();
		g.setColor(drawColor);
		g.fillOval(x, y, size, size);
		recordPosition(x,y);
		
	}
private void recordPosition(int x, int y) {
		thisx = x;
		thisy = y;
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		this.a = e;		
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
		thisx = e.getX();
		thisy = e.getY();
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		System.out.println("mouseReleased");
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		String s = String.valueOf(e.getKeyChar());
		Graphics g = getGraphics();
		g.setColor(textColor);
		g.drawString(s,thisx,thisy);
		this.recordPosition(thisx+fm.stringWidth(s), thisy);
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static Color getTextColor() {
		// TODO Auto-generated method stub
		return textColor;
	}

	public static void chageTextColor(Color chooseColor) {
		textColor=chooseColor;
		
	}	

	
}
