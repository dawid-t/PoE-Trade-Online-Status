// @author Dawid Tarasienko
package pl.tarasienko.poetradeonlinestatus;

import java.awt.Color;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.swing.*;


public class Connection
{
	private int onlineTime = 0;
	private Thread thread;
	private JButton jButton_Connect;
	private JLabel jLabel_CurrentStatus;
	private JLabel jLabel_CurrentOnlineTime;
	private JTextField jTextField_URL;
	
	
	public Connection(JButton jButton_Connect, JLabel jLabel_CurrentStatus, JLabel jLabel_CurrentOnlineTime, JTextField jTextField_URL)
	{
		this.jButton_Connect = jButton_Connect;
		this.jLabel_CurrentStatus = jLabel_CurrentStatus;
		this.jLabel_CurrentOnlineTime = jLabel_CurrentOnlineTime;
		this.jTextField_URL = jTextField_URL;
	}

	private boolean refreshOnlineStatus(String url)
	{
		try
		{
			Document doc = Jsoup.connect(url).validateTLSCertificates(false).userAgent("Mozilla").post();
			
			int index = doc.text().indexOf("You are online for the next");
			if(index != -1)
			{
				String value = doc.text().substring(index+28, index+31);
				onlineTime = (int)Float.parseFloat(value);
			}
			
			return doc.text().contains("You are online for the next");
		}
		catch(Exception e)
		{
			return false;
		}
	}

	private void setOfflineStatus(String url)
	{
		try
		{
			Jsoup.connect(url + "/offline").validateTLSCertificates(false).userAgent("Mozilla").post();
		}
		catch(Exception e)
		{ }
	}

	public void connect(String url)
	{
		if(thread != null) // If thread exists then set status to offline and "destroy" him.
		{
			jButton_Connect.setEnabled(false);
			setOfflineStatus(url);
			jButton_Connect.setEnabled(true);
			thread.interrupt();
			thread = null;
			onlineTime = 0;
			changeUI(false);
			return;
		}

		thread = new Thread() {
			@Override
			public void run()
			{
				try
				{
					jButton_Connect.setEnabled(false);
					jTextField_URL.setEnabled(false);
					boolean isConnected = refreshOnlineStatus(url);
					jButton_Connect.setEnabled(true);

					if(isConnected)
					{
						changeUI(true);

						while(true)
						{
							Thread.sleep(5000); // Refresh counter every 5 seconds.
							int time = Integer.parseInt(jLabel_CurrentOnlineTime.getText()) - 5;
							jLabel_CurrentOnlineTime.setText(time + "");

							if(time < 30) // If counter is under 30 then refresh online status.
							{
								isConnected = refreshOnlineStatus(url);
								if(isConnected)
								{
									jLabel_CurrentOnlineTime.setText(onlineTime+"");
								}
								else
								{
									onlineTime = 0;
									changeUI(false);
									thread = null;
									break;
								}
							}
						}
					}
					else
					{
						throw new Exception("Connected failed.");
					}
				}
				catch(Exception e)
				{
					onlineTime = 0;
					changeUI(false);
					thread = null;
				}
			}
		};
		thread.start();
	}

	private void changeUI(boolean connected)
	{
		if(connected)
		{
			jButton_Connect.setText("Disconnect");
			jButton_Connect.setBackground(Color.RED);
			jLabel_CurrentStatus.setText("Online");
			jLabel_CurrentStatus.setForeground(Color.GREEN);
		}
		else
		{
			jButton_Connect.setText("Connect");
			jButton_Connect.setBackground(new Color(128, 255, 19));
			jTextField_URL.setEnabled(true);
			jLabel_CurrentStatus.setText("Offline");
			jLabel_CurrentStatus.setForeground(Color.RED);
		}
		jLabel_CurrentOnlineTime.setText(onlineTime+"");
	}
}
