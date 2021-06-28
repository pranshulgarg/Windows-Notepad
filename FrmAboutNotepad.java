import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class FrmAboutNotepad extends JDialog implements ActionListener 
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l;
	JButton b1;
	MyNotepad tis;
	FrmAboutNotepad(MyNotepad tis) 
	{
		super(tis,true);
		this.tis=tis;
		setSize(600,525);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("About Notepad");
		setLocationRelativeTo(null);
		setLayout(null);
		ImageIcon ii=new ImageIcon("C://JavaProg/images.png");
		//Image ii2=ii.getImage().getScaledInstance(400,150,Image.SCALE_SMOOTH);
		ImageIcon ii3=new ImageIcon("C://JavaProg/note.jpg");
		ii3=new ImageIcon(ii3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		l1=new JLabel(ii);
		l2=new JLabel("__________________________________________________________");
		l=new JLabel(ii3);
		l3=new JLabel("Microsoft Windows");
		l4=new JLabel("Version 2020(HandMade)");
		l5=new JLabel("@2020 Pranshul Corporation All Rights Reserved");
		l6=new JLabel("This Notepad has been designed by Pranshul Garg ");
		l7=new JLabel("Pranshul Garg");
		l8=new JLabel("LNMIIT");
		b1=new JButton("OK");
		b1.setBackground(new Color(225,225,225));
		b1.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
				b1.setBackground(new Color(229,241,251));
				b1.setBorder(BorderFactory.createLineBorder(new Color(0,120,215)));
			}
			public void focusLost(FocusEvent fe)
			{
				b1.setBackground(new Color(225,225,225));
				b1.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			}
		});
		
		l1.setBounds(100,15,400,150);
		l2.setBounds(80,168,400,30);
		l.setBounds(60,205,60,60);
		l3.setBounds(120,205,400,30);
		l4.setBounds(120,240,400,30);
		l5.setBounds(100,275,400,30);
		l6.setBounds(100,315,400,30);
		l7.setBounds(100,355,400,30);
		l8.setBounds(100,388,400,30);
		b1.setBounds(465,430,100,30);
		
		add(l1);
		add(l2);
		add(l);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		add(l7);	
		add(l8);		add(b1);
		b1.addActionListener(this);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String s1=ae.getActionCommand();
		if(s1.equals("OK"))
			dispose();
	}
}
