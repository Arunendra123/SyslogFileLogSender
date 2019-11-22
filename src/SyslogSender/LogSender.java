package SyslogSender;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class LogSender extends Thread
{
	static String temp1;
	static FileOutputStream fileOutputStream;
	static PrintStream printStream;
	public void run()
	{
		try
		{
			String temp=LogReaderViewer.t.getText();
			if(temp.equals(temp1)!=true)
			{
			  fileOutputStream=new FileOutputStream(LogReaderViewer.t.getText());
			  printStream=new PrintStream(fileOutputStream);
			  temp1=temp; 
			}
			do
			{
				printStream.println(LogReaderViewer.a.getText());
				try 
				{
					Thread.sleep(Integer.parseInt(LogReaderViewer.t1.getText()));
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			while(LogReaderViewer.flags);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
