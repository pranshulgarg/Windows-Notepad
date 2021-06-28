import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class FrmFont extends JDialog implements ListSelectionListener,ActionListener 
{
	JLabel lblFont,lblFontStyle,lblSize,lblScript,lblSam,lblSample;
	JTextField tf1,tf2,tf3;
	JList<String> l1,l2,l3;
	JComboBox<String> c1;
	DefaultListModel<String> m1,m2,m3;
	JScrollPane jsp1,jsp2,jsp3;
	JButton b1,b2;
	MyNotepad tis;
	FrmFont(MyNotepad tis) 
	{
		super(tis,true);
		this.tis=tis;
		setTitle("Font");
		setSize(500,625);
		setLocationRelativeTo(null);
		setLayout(null);
		Font defFnt=new Font(Font.SANS_SERIF,Font.PLAIN,15);
		//Font bFnt=new Font(Font.SANS_SERIF,Font.BOLD,15);
		//Font iFnt=new Font(Font.SANS_SERIF,Font.ITALIC,15);
		lblFont=new JLabel("Font:");
		lblFontStyle=new JLabel("Font Style:");
		lblSize=new JLabel("Size:");
		lblScript=new JLabel("Script:");
		lblSam=new JLabel("Sample");
		lblSample=new JLabel("AaBbYyZz");
		
		lblFont.setFont(defFnt);
		lblFontStyle.setFont(defFnt);
		lblSize.setFont(defFnt);
		lblScript.setFont(defFnt);
		lblSam.setFont(defFnt);
		
		tf1=new JTextField();
		tf2=new JTextField();
		tf3=new JTextField();
		
		m1=new DefaultListModel<> ();
		m2=new DefaultListModel<> ();
		m3=new DefaultListModel<> ();
		
		m1.addElement("SERIF");
		m1.addElement("SANS_SERIF");
		m1.addElement("MONOSPACED");
		
		m2.addElement("Regular");
		m2.addElement("Italic");
		m2.addElement("Bold");
		
		for(int i=8;i<=72;i+=2)
		{
			m3.addElement(i+"");
		}
		l1=new JList<> (m1);
		l1.setFont(defFnt);
		l1.setSelectedIndex(0);
		l1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		l1.addListSelectionListener(this);
		l2=new JList<> (m2);
		l2.setFont(defFnt);
		l2.setSelectedIndex(0);
		l2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		l2.addListSelectionListener(this);
		l3=new JList<> (m3);
		l3.setFont(defFnt);
		l3.setSelectedIndex(0);
		l3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		l3.addListSelectionListener(this);
		
		
		
		
		c1=new JComboBox<> ();
		
		c1.addItem("Western");
		c1.addItem("Greek");
		c1.addItem("Turkish");
		c1.setBackground(Color.WHITE);
		
		jsp1=new JScrollPane(l1);
		jsp2=new JScrollPane(l2);
		jsp3=new JScrollPane(l3);
		
		b1=new JButton("OK");
		b1.addActionListener(this);
		b2=new JButton("Cancel");
		b2.addActionListener(this);
		
		lblFont.setBounds(30,25,50,25);
		lblFontStyle.setBounds(220,25,100,25);
		lblSize.setBounds(400,25,50,25);
		tf1.setBounds(30,50,170,20);
		tf2.setBounds(220,50,160,20);
		tf3.setBounds(400,50,70,20);
		jsp1.setBounds(30,70,170,150);
		jsp2.setBounds(220,70,160,150);
		jsp3.setBounds(400,70,70,150);
		lblSam.setBounds(220,230,80,25);
		lblSample.setBounds(260,265,200,50);
		lblScript.setBounds(220,325,50,25);
		c1.setBounds(220,350,250,25);
		b1.setBounds(280,525,80,30);
		b2.setBounds(380,525,80,30);
		
		add(lblFont);	add(lblFontStyle);	add(lblSize);
		add(tf1);		add(tf2);			add(tf3);
		add(jsp1);		add(jsp2);			add(jsp3);
		add(lblSam);
		add(lblSample);
		add(lblScript);
		add(c1);
		add(b1);		add(b2);
		setVisible(true);
	}
	public void valueChanged(ListSelectionEvent le)
	{
		String s1=l1.getSelectedValue();
		String s2=l2.getSelectedValue();
		String s3=l3.getSelectedValue();
		tf1.setText(s1);
		tf2.setText(s2);
		tf3.setText(s3);
		String a="Font."+s1;
		String b1="Font."+s2;
		int b=Integer.parseInt(b1);
		int c=Integer.parseInt(s3);
		Font fnt=new Font(a,b,c);
		lblSample.setFont(fnt);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String s=ae.getActionCommand();
		if(s.equals("OK"))
		{
			MyNotepad.ta1.setFont(lblSample.getFont());
		}
		if(s.equals("Cancel"))
			dispose();
	}
}
