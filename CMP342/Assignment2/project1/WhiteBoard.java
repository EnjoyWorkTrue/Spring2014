package project1;

import java.awt.Color;
import java.awt.Dimension;
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
	private static Color preColor = drawColor;
	private static Color textColor = Color.black;
	static Graphics a1;
	public static Dimension dimension;
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
		a1 = getGraphics();
		dimension = super.getParent().getSize();
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
		a1 = getGraphics();
		dimension = super.getParent().getSize();
		recordPosition(e.getX(), e.getY());
	}
	public static void eraseEverything(){
		
		a1.setColor(Color.white);
		
		System.out.println(dimension.width);
		System.out.println(dimension.height);
		a1.fillRect(0, 0, dimension.width, dimension.height);
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {		
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
		Graphics g = getGraphics();
		//backspace
		if(e.getKeyChar()==8){			
		}
		else if(e.getKeyChar()==49){
			preColor = drawColor;
			drawColor = Color.white;
		}
		else if(e.getKeyChar()==32){
			drawColor = preColor;
		}
		else{
		String s = String.valueOf(e.getKeyChar());
		g.setColor(textColor);
		g.drawString(s,thisx,thisy);
		this.recordPosition(thisx+fm.stringWidth(s), thisy);}
		
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
