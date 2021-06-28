import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class FrmReplace extends JDialog implements ActionListener 
{
	JLabel l1,l2;
	JTextField tf1,tf2;
	JButton fndNxt,replace,replaceAll,cancel;
	JCheckBox cb;
	MyNotepad tis;
	int l;
	FrmReplace(MyNotepad tis) 
	{
		super(tis,false);
		this.tis=tis;
		setTitle("Replace");
		setSize(475,230);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		Font fnt=new Font(Font.SANS_SERIF,Font.PLAIN,14);
		l1=new JLabel("Find what");
		l1.setFont(fnt);
		l2=new JLabel("Replace with:");
		l2.setFont(fnt);
		tf1=new JTextField();
		tf2=new JTextField();
		fndNxt=new JButton("Find Next");
		fndNxt.setFont(fnt);
		fndNxt.setBackground(new Color(225,225,225));
		replace=new JButton("Replace");
		replace.setBackground(new Color(225,225,225));
		replace.setFont(fnt);
		replaceAll=new JButton("Replace All");
		replaceAll.setBackground(new Color(225,225,225));
		replaceAll.setFont(fnt);
		cancel=new JButton("Cancel");
		cancel.setBackground(new Color(225,225,225));
		cancel.setFont(fnt);
		cb=new JCheckBox("Match Case");
		cb.setFont(fnt);
		
		l1.setBounds(8,15,90,25);
		tf1.setBounds(105,15,215,25);
		fndNxt.setBounds(340,15,110,25);
		l2.setBounds(8,50,90,25);
		tf2.setBounds(105,50,215,25);
		replace.setBounds(340,50,110,25);
		replaceAll.setBounds(340,85,110,25);
		cancel.setBounds(340,120,110,25);
		cb.setBounds(15,120,130,35);
		
		fndNxt.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
				fndNxt.setBackground(new Color(229,241,251));
				fndNxt.setBorder(BorderFactory.createLineBorder(new Color(0,120,215)));
			}
			public void focusLost(FocusEvent fe)
			{
				fndNxt.setBackground(new Color(225,225,225));
				fndNxt.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			}
		});
		replace.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
				replace.setBackground(new Color(229,241,251));
				replace.setBorder(BorderFactory.createLineBorder(new Color(0,120,215)));
			}
			public void focusLost(FocusEvent fe)
			{
				replace.setBackground(new Color(225,225,225));
				replace.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			}
		});
		replaceAll.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
				replaceAll.setBackground(new Color(229,241,251));
				replaceAll.setBorder(BorderFactory.createLineBorder(new Color(0,120,215)));
			}
			public void focusLost(FocusEvent fe)
			{
				replaceAll.setBackground(new Color(225,225,225));
				replaceAll.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			}
		});
		cancel.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
				cancel.setBackground(new Color(229,241,251));
				cancel.setBorder(BorderFactory.createLineBorder(new Color(0,120,215)));
			}
			public void focusLost(FocusEvent fe)
			{
				cancel.setBackground(new Color(225,225,225));
				cancel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			}
		});
		fndNxt.addActionListener(this);
		replace.addActionListener(this);
		replaceAll.addActionListener(this);
		cancel.addActionListener(this);
		cb.addActionListener(this);
		
		add(l1);	add(tf1);	add(fndNxt);
		add(l2);	add(tf2);	add(replace);
								add(replaceAll);
								add(cancel);
		add(cb);	
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String s1=ae.getActionCommand();
		String findWhat=tf1.getText();
		String findIn=MyNotepad.ta1.getText();
		String replaceWith=tf2.getText();
		int start=MyNotepad.ta1.getSelectionStart();
		int end=MyNotepad.ta1.getSelectionEnd();
		boolean mtchCse=cb.isSelected();
		int l=findWhat.length();
		if(s1.equals("Find Next"))
		{
			tis.find(findWhat,findIn,l,1,mtchCse);
		}
		else if(s1.equals("Replace"))	//shuruaat
		{
			findIn=findIn.substring(0,start)+replaceWith+findIn.substring(end,findIn.length());
			MyNotepad.ta1.setText(findIn);
			MyNotepad.ta1.setCaretPosition(end);
			tis.find(findWhat,findIn,l,1,mtchCse);
			//			OR*/
			/*findIn=findIn.replaceFirst(findWhat,replaceWith);
			MyNotepad.ta1.setText(findIn);*/
		}
		else if(s1.equals("Replace All"))
		{
			findIn=findIn.replaceAll(findWhat,replaceWith);
			MyNotepad.ta1.setText(findIn);
		}
		else if(s1.equals("Cancel"))
		{
			dispose();
		}
	}
}
