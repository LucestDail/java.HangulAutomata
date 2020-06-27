import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static MainFrame mainframe = new MainFrame();
	static MainFrame GetInstance() {
		return mainframe;
	}
	public static void main(String[] args) {
		mainframe.init();
	}	
	JTextField top = null;
	JButton keys[] = new JButton[12];
	String keyFaces[] = {"1<br>太", "2<br>.", "3<br>天", "4<br>丑 六", "5<br>中 予", "6<br>之 兮", 
			"7<br>仆 公", "8<br>今 冗", "9<br>元 內", "*", "0<br>仄 仃", "#"};
	JTextArea log = null;
	CJIAutomata auto = new CJIAutomata();
	void init() {

		setTitle("繭雖檣@OSH");

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);

		setBounds(100, 100, 600, 400);
		setVisible(true);
		setLayout(new GridLayout(1, 2));
		
		Font f = new Font("掉葡", Font.BOLD, 25);
		Dimension preferredSize = new Dimension(300, 80);
		
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		add(left);
		
		top = new JTextField(20);
		top.setFont(f);
		top.setBackground(new Color(200, 255, 255));
		top.setPreferredSize(preferredSize);
		left.add(top, BorderLayout.NORTH);
		
		JPanel keyPad = new JPanel();
		left.add(keyPad, BorderLayout.CENTER);
		keyPad.setLayout(new GridLayout(4, 3));
	    preferredSize = new Dimension(100, 70);
		for (int i = 0; i < 12; i++) {
			keys[i] = new JButton();
			keys[i].setText("<html><p align=center>"+keyFaces[i]+"</html>");
			keys[i].setFont(f);
	        keys[i].setPreferredSize(preferredSize);
			keys[i].addActionListener(this);
			keyPad.add(keys[i]);
		}
		
		//
		//JPanel right = new JPanel();
		f = new Font("掉葡", Font.BOLD, 14);
	    log = new JTextArea(16, 36);
	    log.setEditable(false); // set textArea non-editable
	    log.setFont(f);
	    JScrollPane scroll = new JScrollPane(log);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    add(scroll);
		pack();
	}
	public void appendLog(String lines) {
		log.append(lines);
	}
	String keyChar = "123456789*0#";
	private char getButtonChar(JButton b) {
		for (int i = 0; i < 12; i++)
			if (keys[i].equals(b))
				return keyChar.charAt(i);
		return ' ';
	}
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		char key = getButtonChar(b);
		char letter = ' ';
		boolean result=false;
		String output = top.getText();
		if (key == '*' || key == '#') {
			auto.reset();
			letter = key;
		} else {
			result = auto.next(key-'0');
			if (auto.Q == CJIAutomata.ERROR_STATE) {
				letter = auto.errorLetter;
				//auto.reset();
			} else {
				if (!result && output.length() > 0) 
					output = output.substring(0, output.length()-1);
				letter = auto.getCurrentLetter();
			}
		}
		output += letter;
		top.setText(output);
	}
}