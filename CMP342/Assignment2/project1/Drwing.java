package project1;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Drwing extends Applet{

buttonPanel btn = new buttonPanel();
WhiteBoard wb = new WhiteBoard();


public void init() {
	
	setSize(800,600);


	setLayout(new BorderLayout());
    add(btn,BorderLayout.EAST);    
    addMouseMotionListener(new WhiteBoard());
    addMouseListener(new WhiteBoard());
   
}





}