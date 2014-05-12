package Application;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import Client.ChatClient;
import Interface.FCTA;

public class ClientApp extends JApplet implements ActionListener,KeyListener,FCTA,ItemListener{
	Button connect;
	Button send;
	JScrollPane scroll;
	JTextArea output;
	JScrollPane input_scroll;
	JTextArea input;
	JTextArea nickName;
	JLabel nickname_label;
	ArrayList<JLabel> nickname_list;
	Panel nicknameP;
	int count = 1;
	boolean connected = false;
	
	JTextArea privatePort;
	private ChatClient client;
	private String nickname;
	
	private CheckboxGroup cbg;
	private Checkbox rb1,rb2;
	private JRadioButton rb3;
	
		          
	public void init(){
		setSize(400,400);
		setLayout(new BorderLayout());
		// Panel 1 for input | send | connect ?? South
		Panel p1 = new Panel(new BorderLayout());
		Panel down = new Panel(new GridLayout(0,4));
		
		privatePort = new JTextArea();
		privatePort.setEditable(false);
		down.add(privatePort);
		cbg = new CheckboxGroup();
		rb1 = new Checkbox("Public",cbg,true);
		rb2 = new Checkbox("Private",cbg,false);
		rb3 = new JRadioButton("Encryption",false);
		down.add(rb1);
		down.add(rb2);
		down.add(rb3);
		
		
		input = new JTextArea();
		input.setEditable(false);
		input_scroll = new JScrollPane(input);
		connect = new Button("Connect");
		send = new Button("Send");
		p1.add(input_scroll,BorderLayout.CENTER);
		p1.add(down,BorderLayout.SOUTH);
		Panel buttonP = new Panel(new GridLayout(0,2));
		send.setEnabled(false);
		buttonP.add(send);
		buttonP.add(connect);
		p1.add(buttonP,BorderLayout.EAST);
		
		
		// Panel 2  NickName Label | nickName
		Panel p2 = new Panel(new BorderLayout());
		nickName = new JTextArea();
		nickname_label = new JLabel("NickName: ");
		p2.add(nickname_label,BorderLayout.WEST);
		p2.add(nickName,BorderLayout.CENTER);
		
		
		
		
		// outputArea
		output = new JTextArea();
		scroll = new JScrollPane(output);
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		output.setEditable(false);
		
		
		// nickName Group
		nickname_list = new ArrayList<JLabel>();
		nickname_list.add(new JLabel("      CLASS       "));
		nicknameP = new Panel(new GridLayout(10,0));
		nicknameP.add(nickname_list.get(0));

		
		         add(p2,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER); add(nicknameP,BorderLayout.EAST); 
			     add(p1,BorderLayout.SOUTH);	
		         
		send.addActionListener(this);
		connect.addActionListener(this);
		input.addKeyListener(this);
		rb3.addActionListener(this);
		rb2.addItemListener(this);
		rb1.addItemListener(this);
	}
	
	
	
	
	// for key board
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==10){
			sendMessage(input.getText());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==10)
			input.setText("");
			
	}
	// for click
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==send){	
			sendMessage(input.getText());
			input.setText("");
		}
		if(e.getSource()==connect){
			if(isNickname()){
			connect();
			connect.setEnabled(false);
			sendMessage(nickName.getText());
			input.setEditable(true);
			output.setText("connected \n");
			nickName.setEditable(false);
			send.setEnabled(true);
			}
			
		}
		if(e.getSource()==rb3){
			output.setText(rb3.isSelected()+"");
		}
		
	}
	private void sendMessage(String text) {
		
		
		try {
			if(rb2.getState()){
				boolean yes = true;
				int port = 0;
				try{
					port = Integer.parseInt(privatePort.getText());
					
				}
				catch(NumberFormatException e){
					output.append("PortNumber can not be string \n");
					yes = false;
				}
				if(yes){
					output.append("private message to " + privatePort.getText()+": "
				+input.getText()+ "\n");
					
					client.sendPrivateMessage(port,text);
				}
				}
			else{
				output.append("me: "+text+"\n");
				client.sendMessage(text);
			}
			
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(rb1.getState()){
			privatePort.setEditable(false);
		}
		if(rb2.getState()){
			privatePort.setEditable(true);
			privatePort.setText("Port Number Here");
		}
		
	}




	private boolean isNickname() {
		if(nickName.getText().length()>8){
			output.setText("NickName is Too long limited up to 8 character");
			return false;
		}
		if(nickName.getText().equals("")){
			output.setText("need Nickname");
			return false;
		}
		return true;
	}




	private void connect(){
		client = new ChatClient("0.0.0.0",8000,this);
		try {
			client.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	private void addUser(String nickname){
		
			getContentPane().remove(nicknameP);
			nickname_list.add(new JLabel(nickname));
			nicknameP.add(nickname_list.get(count));
			count++;
			
			getContentPane().add(nicknameP,BorderLayout.EAST);
			validate();
			repaint();
		
		
	}
	private void removeUser(String nickname){
		getContentPane().remove(nicknameP);
		nicknameP.removeAll();
		JLabel remove =  null;
		for(JLabel a : nickname_list){
			if(a.getText().equals(nickname)){
				remove=a;
			}
		}
		if(remove!=null){
		nickname_list.remove(remove);
		count--;
		}
		for(JLabel a:nickname_list){
			nicknameP.add(a);
		}		
		getContentPane().add(nicknameP,BorderLayout.EAST);
		validate();
		repaint();
	}




	private void getNickname(String message) {
		String s = "";
		for(int i = 17; i<message.length();i++){
			s = s+message.charAt(i);
		}
		addUser(s);

		
	}




	@Override
	public void appendMessage(String message) {
		output.append(message+"\n");
		
	}




	@Override
	public void setNicknameList(String readUTF) {
		if(readUTF.equals("you are first")){
			addUser("me: " + nickName.getText());
		}
		else{
			addUser("me: " + nickName.getText());
			String[] s = readUTF.split(",");
			for(int i = 0; i<s.length;i++){
				addUser(s[i]);
			}
		}
		
	}




	@Override
	public void addClient(String name) {
		addUser(name);
		
		
	}




	@Override
	public void deleteUser(String user) {
		removeUser(user);
		
	}




	

}
