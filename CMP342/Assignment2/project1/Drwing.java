package project1;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

public class Drwing extends Applet{

buttonPanel btn = new buttonPanel();
WhiteBoard wb = new WhiteBoard();
CharDrawer chd = new CharDrawer();

public void init() {
	
	setSize(800,600);


	setLayout(new BorderLayout());
    add(btn,BorderLayout.EAST);    
    
    addMouseMotionListener(wb);
    addMouseListener(wb);
    addKeyListener(chd);
   
   
}





}