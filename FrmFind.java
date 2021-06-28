import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class FrmFind extends JDialog implements ActionListener
{
	JLabel l1,l2;
	JTextField findwht;
	JButton findNext,cancl;
	JCheckBox mtchCse;
	ButtonGroup bg;
	JRadioButton up,down;
	MyNotepad tis;
	FrmFind(MyNotepad tis) 
	{
		super(tis,false); 				//true for modal false for modal less
		this.tis=tis;
		setLayout(null);
		setTitle("Find");
		setSize(500,200);
		setLocationRelativeTo(null);
		Font fnt=new Font(Font.SANS_SERIF,Font.PLAIN,13);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		l1=new JLabel("Find what");
		l1.setFont(fnt);
		l2=new JLabel("Direction");
		l2.setFont(fnt);
		findwht=new JTextField();
		findNext=new JButton("Find Next");
		findNext.setBackground(new Color(225,225,225));
		findNext.setFont(fnt);
		cancl=new JButton("Cancel");
		cancl.setBackground(new Color(225,225,225));
		cancl.setFont(fnt);
		mtchCse=new JCheckBox("Match Case");
		mtchCse.setFont(fnt);
		bg=new ButtonGroup();
		up=new JRadioButton("Up");
		down=new JRadioButton("Down");
		
		l1.setBounds(5,10,70,20);
		findwht.setBounds(100,10,220,25);
		findNext.setBounds(330,10,130,25);
		l2.setBounds(210,40,100,30);
		cancl.setBounds(330,40,130,25);
		up.setBounds(210,65,40,50);
		down.setBounds(255,65,70,50);
		mtchCse.setBounds(20,90,130,35);
		bg.add(up); bg.add(down);
		
		add(l1); 		add(findwht);   add(findNext);
		add(l2); 		add(cancl);		
		add(mtchCse);	add(up);		add(down); 
						
		findNext.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
				findNext.setBackground(new Color(229,241,251));
				findNext.setBorder(BorderFactory.createLineBorder(new Color(0,120,215)));
			}
			public void focusLost(FocusEvent fe)
			{
				findNext.setBackground(new Color(225,225,225));
				findNext.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			}
		});
		cancl.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
				cancl.setBackground(new Color(229,241,251));
				cancl.setBorder(BorderFactory.createLineBorder(new Color(0,120,215)));
			}
			public void focusLost(FocusEvent fe)
			{
				cancl.setBackground(new Color(225,225,225));
				cancl.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			}
		});
		findNext.addActionListener(this);
		cancl.addActionListener(this);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		JButton b=(JButton)ae.getSource();
		if(b==findNext)
		{
			String findWhat=findwht.getText();
			String findIn=MyNotepad.ta1.getText();
			int l=findWhat.length();
			int dir;
			boolean matchCase=mtchCse.isSelected();
			if(down.isSelected())
				dir=1;
			else
				dir=-1;
			tis.find(findWhat,findIn,l,dir,matchCase);
		}
		else if(b==cancl)
			dispose();
	}
}
