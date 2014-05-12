import java.applet.AppletContext;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class asd extends JApplet implements ActionListener{
	JLabel[] nickname;
	Button add;
	Panel textP = null;
	Button remove;
	int count;
	public void init(){
		count = 2;
		setLayout(new BorderLayout());
		textP = new Panel();
		textP.setLayout(new GridLayout(10,0));
		nickname = new JLabel[10];
		nickname[0] = new JLabel("joseph");
		nickname[1] = new JLabel("david");
		for(int i = 0; i<count;i++){
			textP.add(nickname[i]);
		}
		add = new Button("add");
		remove = new Button("remove");
		this.add(textP,BorderLayout.CENTER);
		add(add,BorderLayout.SOUTH);
		add(remove,BorderLayout.NORTH);
		add.addActionListener(this);
		remove.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add){
			change();
		}
		if(e.getSource()==remove){
			remove();
		}
		
	}
private void remove() {
	if(count != 0){
		textP.remove(nickname[count-1]);
		count--;
		getContentPane().add(textP,BorderLayout.CENTER);
		validate();
		repaint();}
	}

	public void change(){
		if(count<nickname.length){
		getContentPane().remove(textP);
		nickname[count] = new JLabel("hello " + count);
		textP.add(nickname[count]);		
		count++;
		
		getContentPane().add(textP,BorderLayout.CENTER);
		validate();
		repaint();}
	
		
		
	}
	

}
