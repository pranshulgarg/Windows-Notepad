import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.*;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class MyNotepad extends JFrame implements ActionListener
{
	JMenuBar mbar;
	JMenu mnuFile,mnuEdit,mnuFormat,mnuHelp;
	JMenuItem mitNew,mitOpen,mitSave,mitSaveAs,mitPrint,mitExit;
	JMenuItem mitCut,mitCopy,mitPaste,mitDelete,mitFind,mitFindNext,mitReplace,mitSelectAll,mitTimeDate;
	JMenuItem mitFont,mitViewHelp,mitAboutNotepad;
	JCheckBoxMenuItem mitWordWrap;
	static JTextArea ta1;
	JScrollPane jsp;
	String s1;
	boolean saveFlag=true,matchCase;
	File currentFile;
	String findWhat,findIn;
	int l,dir;
	MyNotepad()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Pranshul's Notepad");
		setBounds(920,0,1000,600);
		setLayout(new BorderLayout());
		Font defltFnt=new Font(Font.SERIF,Font.PLAIN,35);
		ImageIcon ii=new ImageIcon("C://JavaProg/abc.jpg");
		ii=new ImageIcon(ii.getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));
		setIconImage(ii.getImage());
		mbar=new JMenuBar();
		
		ta1=new JTextArea();
		ta1.setFont(defltFnt);
		ta1.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent de)
			{
				saveFlag=false;
			}
			public void removeUpdate(DocumentEvent de)
			{
				saveFlag=false;
			}
			public void changedUpdate(DocumentEvent de)
			{
				saveFlag=false;
			}
		});
		
		jsp=new JScrollPane(ta1);
		
		mnuFile=new JMenu("File");
		mnuFile.setMnemonic(KeyEvent.VK_F);
		mnuEdit=new JMenu("Edit");
		mnuEdit.setMnemonic(KeyEvent.VK_E);
		mnuFormat=new JMenu("Format");
		mnuFormat.setMnemonic(KeyEvent.VK_O);
		mnuHelp=new JMenu("Help");
		mnuHelp.setMnemonic(KeyEvent.VK_H);
		
		mitNew=new JMenuItem("New");
		mitNew.setMnemonic(KeyEvent.VK_N);
		mitOpen=new JMenuItem("Open...");
		mitOpen.setMnemonic(KeyEvent.VK_O);
		mitSave=new JMenuItem("Save");
		mitSave.setMnemonic(KeyEvent.VK_S);
		mitSaveAs=new JMenuItem("Save As...");
		mitSaveAs.setMnemonic(KeyEvent.VK_A);
		mitPrint=new JMenuItem("Print...");
		mitPrint.setMnemonic(KeyEvent.VK_P);
		mitExit=new JMenuItem("Exit");
		mitExit.setMnemonic(KeyEvent.VK_X);
		
		mnuFile.add(mitNew);
		mnuFile.add(mitOpen);
		mnuFile.add(mitSave);
		mnuFile.add(mitSaveAs);
		mnuFile.addSeparator();
		mnuFile.add(mitPrint);
		mnuFile.addSeparator();
		mnuFile.add(mitExit);
		
		mitCut=new JMenuItem("Cut");
		mitCut.setMnemonic(KeyEvent.VK_T);
		mitCopy=new JMenuItem("Copy");
		mitCopy.setMnemonic(KeyEvent.VK_C);
		mitPaste=new JMenuItem("Paste");
		mitPaste.setMnemonic(KeyEvent.VK_P);
		mitDelete=new JMenuItem("Delete");
		mitDelete.setMnemonic(KeyEvent.VK_L);
		mitFind=new JMenuItem("Find...");
		mitFind.setMnemonic(KeyEvent.VK_F);
		mitFindNext=new JMenuItem("Find Next");
		mitFindNext.setMnemonic(KeyEvent.VK_N);
		mitReplace=new JMenuItem("Replace...");
		mitReplace.setMnemonic(KeyEvent.VK_R);
		mitSelectAll=new JMenuItem("Select All");
		mitSelectAll.setMnemonic(KeyEvent.VK_A);
		mitTimeDate=new JMenuItem("Time/Date");
		mitTimeDate.setMnemonic(KeyEvent.VK_D);
		
		mnuEdit.add(mitCut);
		mnuEdit.add(mitCopy);
		mnuEdit.add(mitPaste);
		mnuEdit.add(mitDelete);
		mnuEdit.addSeparator();
		mnuEdit.add(mitFind);
		mnuEdit.add(mitFindNext);
		mnuEdit.add(mitReplace);
		mnuEdit.addSeparator();
		mnuEdit.add(mitSelectAll);
		mnuEdit.add(mitTimeDate);
		
		mitFont=new JMenuItem("Font...");
		mitFont.setMnemonic(KeyEvent.VK_F);
		mitWordWrap=new JCheckBoxMenuItem("Word Wrap");
		mitWordWrap.setMnemonic(KeyEvent.VK_W);
		
		mitViewHelp=new JMenuItem("View Help");
		mitViewHelp.setMnemonic(KeyEvent.VK_H);
		mitAboutNotepad=new JMenuItem("About Notepad");
		mitAboutNotepad.setMnemonic(KeyEvent.VK_A);
		
		mnuFormat.add(mitWordWrap);
		mnuFormat.add(mitFont);
		
		mnuHelp.add(mitViewHelp);
		mnuHelp.add(mitAboutNotepad);
		
		mitNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		mitOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		mitSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		mitSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
		mitPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		mitCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		mitCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		mitPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		mitFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		mitFindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,ActionEvent.CTRL_MASK));
		mitReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		mitSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		mitTimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,ActionEvent.CTRL_MASK));
		
		mbar.add(mnuFile);
		mbar.add(mnuEdit);
		mbar.add(mnuFormat);
		mbar.add(mnuHelp);
		
		mitNew.addActionListener(this);
		mitOpen.addActionListener(this);
		mitSave.addActionListener(this);
		mitSaveAs.addActionListener(this);
		mitPrint.addActionListener(this);
		mitExit.addActionListener(this);
		mitCut.addActionListener(this);
		mitCopy.addActionListener(this);
		mitPaste.addActionListener(this);
		mitDelete.addActionListener(this);
		mitFind.addActionListener(this);
		mitFindNext.addActionListener(this);
		mitReplace.addActionListener(this);
		mitSelectAll.addActionListener(this);
		mitTimeDate.addActionListener(this);
		mitFont.addActionListener(this);
		mitAboutNotepad.addActionListener(this);
		mitWordWrap.addActionListener(this);
		
		
		setJMenuBar(mbar);
		add(jsp);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		s1=ae.getActionCommand();
		if(s1.equals("New"))
		{
			if(saveFlag==true)
				ta1.setText("");
			else
			{
				int ans=JOptionPane.showConfirmDialog(this,"Do you want to save changes to Untitled?","Notepad",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(ans==JOptionPane.YES_OPTION)
					save();
				else if(ans==JOptionPane.NO_OPTION)
					ta1.setText("");
			}
		}
		else if(s1.equals("Open..."))
		{
			if(saveFlag==false)
			{
				int ans=JOptionPane.showConfirmDialog(this,"Do you want to save changes to Untitled?","Notepad",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(ans==JOptionPane.YES_OPTION)
				{
					save();
					return;
				}
				else
				{
					try
					{
						JFileChooser jfc=new JFileChooser("c://JavaProg");
						FileNameExtensionFilter filter=new FileNameExtensionFilter("Text Files","txt");
						jfc.addChoosableFileFilter(filter);
						jfc.setFileFilter(filter);
						int code=jfc.showOpenDialog(this);
						if(code==JFileChooser.APPROVE_OPTION)
						{
							currentFile=jfc.getSelectedFile();
							setTitle(currentFile.getName()+" -Notepad");
							FileInputStream fis=new FileInputStream(currentFile);
							byte b[]=new byte[(int)currentFile.length()];
							fis.read(b);
							ta1.setText(new String(b));
							fis.close();
						}
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		else if(s1.equals("Save"))
		{
			save();
		}
		else if(s1.equals("Save As"))
		{
			saveAs();
		}
		else if(s1.equals("Print..."))	//remaining
		{
			ta1.setText(s1);
		}
		else if(s1.equals("Exit"))
		{
			System.exit(0);
		}
		else if(s1.equals("Cut"))
		{
			ta1.cut();
		}
		else if(s1.equals("Copy"))
		{
			ta1.copy();
		}
		else if(s1.equals("Paste"))
		{
			ta1.paste();
		}
		else if(s1.equals("Delete"))
		{
			StringBuffer sb=new StringBuffer(ta1.getText());
			int startPos=ta1.getSelectionStart();
			int endPos=ta1.getSelectionEnd();
			ta1.setText(sb.delete(startPos,endPos).toString());
			ta1.setCaretPosition(startPos);
		}
		else if(s1.equals("Find..."))
		{
			new FrmFind(this);
		}
		else if(s1.equals("Find Next"))
		{
			this.findWhat=findWhat;
			this.findIn=findIn;
			this.l=l;
			this.dir=dir;
			this.matchCase=matchCase;
			find(findWhat,findIn,l,dir,matchCase);
		}
		else if(s1.equals("Replace..."))
		{
			new FrmReplace(this);
		}
		else if(s1.equals("Select All"))
		{
			ta1.setSelectionStart(0);
			ta1.setSelectionEnd(ta1.getText().length());
		}
		else if(s1.equals("Time/Date"))
		{
			SimpleDateFormat sdf=new SimpleDateFormat("hh:mm dd-MM-yyyy");
			Date d1=new Date();
			ta1.insert(sdf.format(d1),ta1.getCaretPosition());
		}
		else if(s1.equals("Word Wrap"))
		{
			ta1.setLineWrap(mitWordWrap.isSelected());
		}
		else if(s1.equals("Font..."))
		{
			new FrmFont(this);
		}
		else if(s1.equals("View Help")) //remaining
		{
			ta1.setText(s1);
		}
		else if(s1.equals("About Notepad"))
		{
			new FrmAboutNotepad(this);
		}
	}
	void save()
	{
		if(currentFile==null)
		{
			saveAs();
		}
		try
		{
			FileOutputStream fos=new FileOutputStream(currentFile);
			fos.write(ta1.getText().getBytes());
			fos.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	void saveAs()
	{
		try
		{
			JFileChooser jfc1=new JFileChooser("c://JavaProg");
			FileNameExtensionFilter filter1=new FileNameExtensionFilter("Text Files","txt");
			jfc1.addChoosableFileFilter(filter1);
			jfc1.setFileFilter(filter1);
			int ans=jfc1.showSaveDialog(this);
			if(ans==JFileChooser.APPROVE_OPTION)
			{
				currentFile=jfc1.getSelectedFile();
				setTitle(currentFile.getName()+" -Notepad");
				FileOutputStream fos=new FileOutputStream(currentFile);
				fos.write(ta1.getText().getBytes());
				fos.close();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No file selected");
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	void find(String findWhat,String findIn,int l,int dir,boolean matchCase)
	{
		this.findWhat=findWhat;
		this.findIn=findIn;
		this.l=l;
		this.dir=dir;
		this.matchCase=matchCase;
		int pos=ta1.getCaretPosition();
		if(matchCase==false)
		{
			findWhat=findWhat.toUpperCase();
			findIn=findIn.toUpperCase();
		}
		if(dir==1)
		{
			pos=findIn.indexOf(findWhat,pos);
			if(pos==-1)
				JOptionPane.showMessageDialog(this,"Cannot find '"+findWhat+"'","Notepad",JOptionPane.INFORMATION_MESSAGE);
			ta1.setSelectionStart(pos);
			ta1.setSelectionEnd(pos+l);
		}
		if(dir==-1)
		{
			pos=findIn.lastIndexOf(findWhat,pos);
			if(pos==-1)
				JOptionPane.showMessageDialog(this,"Cannot find '"+findWhat+"'","Notepad",JOptionPane.INFORMATION_MESSAGE);
			ta1.setSelectionStart(pos);
			ta1.setSelectionEnd(pos+l);
		}
	}
	public static void main(String[] args) 
	{
		new MyNotepad();
	}
}
