package SyslogSender;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class LogReaderViewer extends Frame implements ActionListener
{
  static JFrame f;
  static TextField t,t1;
  static TextArea a;
  static JButton b,b1;
  static JLabel l,l1,l2;
  static boolean flags=true;
  public static void viewer ()
  {
	    LogReaderViewer te=new LogReaderViewer();
	    f  = new JFrame("Syslog Sender"); 
		f.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent windowEvent){System.exit(0);
		}
		}); 
		f.setLocation(200,0);
		
		JPanel p=new JPanel();
		t=new TextField(40);
		t1=new TextField(2);
		
		b=new JButton("select");
		b.setPreferredSize(new Dimension(70,20));
		b.addActionListener(te);
		b1=new JButton("Send");
		b1.addActionListener(te);
		
		l=new JLabel(" Frequency(ms)");
		l1=new JLabel("                                                                                                     ");
		a=new TextArea(30,127);
		
		t.setText("");
		t1.setText("3000");
		
		
		JCheckBox c = new JCheckBox();
		c.setText("Uncheck for continuous logs");
		c.setSelected(true);
		c.addActionListener(new ActionListener() 
		{
		    @Override
		    public void actionPerformed(ActionEvent event)
		    {
		        JCheckBox cb = (JCheckBox) event.getSource();
		        if (cb.isSelected()) 
		        {
		        	b1.setLabel("Send");
		        } 
		        else
		        {
		        	b1.setLabel("Start");
		        }
		    }
		});
		
		p.setBorder(BorderFactory.createTitledBorder("Hello Arcsight V1.0: "));
		p.add(t);
		p.add(b);
		p.add(l1);
		p.add(l);
		p.add(t1);
		p.add(b1);
		p.add(a);
		p.add(c);
		f.add(p);
		f.setSize(930,600);;
		f.setVisible(true);
  }
@SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e) 
	{ 
		String s = e.getActionCommand(); 
		if (s.equals("Start")) 
		{ 				
			if(t.getText().equals(""))
			{
				 JOptionPane.showMessageDialog(this,"Please Input path","Warning",JOptionPane.WARNING_MESSAGE);	       
			}
			else if(t1.getText().equals(""))
			{
				 JOptionPane.showMessageDialog(this,"Please frequency","Warning",JOptionPane.WARNING_MESSAGE);    
			}
			else if(a.getText().equals(""))
			{
				 JOptionPane.showMessageDialog(this,"Please Input logs","Warning",JOptionPane.WARNING_MESSAGE);		
			}
			else
			{
				LogSender logsender=new LogSender();
				flags=true;
				b1.setLabel("Stop");
				logsender.start();
			}
		} 
		else if(s.equals("Stop"))
		{
			flags=false;
			b1.setLabel("Start");
		}
		else if(s.equals("Send"))
		{
			if(t.getText().equals(""))
			{
				 JOptionPane.showMessageDialog(this,"Please Input path","Warning",JOptionPane.WARNING_MESSAGE);	       
			}
			else if(a.getText().equals(""))
			{
				 JOptionPane.showMessageDialog(this,"Please Input logs","Warning",JOptionPane.WARNING_MESSAGE);		
			}
			else
			{
				flags=false;
				LogSender logsender=new LogSender();
				logsender.start();
				JOptionPane.showMessageDialog(this,"sent","successful",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(s.equals("select"))
		{
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) 
			{
				File selectedFile = jfc.getSelectedFile();
				t.setText( selectedFile.getAbsolutePath());
			}
		}
	} 
}
