// @author Dawid Tarasienko
package poetradeonlinestatus;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainJFrame extends javax.swing.JFrame
{
	private Connection connection;
	private SaveURL saveURL;

	public MainJFrame()
	{
		setTitle("PoE Trade Online Status");
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		initComponents();
		initData();
		createListeners();
	}

	private void initData() // Create objects used to connection and save URL to txt file.
	{
		saveURL = new SaveURL();
		String savedUrl;
		if((savedUrl = saveURL.loadFile()) != null) // Load saved URL if exists.
		{
			jTextField_URL.setText(savedUrl);
			jCheckBox_SaveURL.setSelected(true);
			if(!jTextField_URL.getText().startsWith("http://control.poe.trade/"))
			{
				jButton_Connect.setEnabled(false);
			}
		}
		else
		{
			jButton_Connect.setEnabled(false);
		}
		connection = new Connection(jButton_Connect, jLabel_CurrentStatus, jLabel_CurrentOnlineTime, jTextField_URL);
	}

	private void createListeners() // Create listeners which will save URL and check correctness of URL.
	{
		addWindowListener(new WindowAdapter() { // When exit from program then save or delete txt file with URL.
			public void windowClosing(WindowEvent e)
			{
				if(jCheckBox_SaveURL.isSelected())
				{
					saveURL.saveFile(jTextField_URL.getText());
				}
				else
				{
					saveURL.deleteFile();
				}
			}
		});

		jTextField_URL.getDocument().addDocumentListener(new DocumentListener() { // Add listener who will check the correctness of URL.
			public void changedUpdate(DocumentEvent e)
			{
				checkURL();
			}

			public void removeUpdate(DocumentEvent e)
			{
				checkURL();
			}

			public void insertUpdate(DocumentEvent e)
			{
				checkURL();
			}

			public void checkURL()
			{
				if(jTextField_URL.getText().startsWith("http://control.poe.trade/"))
				{
					jButton_Connect.setEnabled(true);
				}
				else
				{
					jButton_Connect.setEnabled(false);
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel_Main = new javax.swing.JPanel();
        jTextField_URL = new javax.swing.JTextField();
        jLabel_URL = new javax.swing.JLabel();
        jButton_Connect = new javax.swing.JButton();
        jLabel_OnlineTime = new javax.swing.JLabel();
        jLabel_CurrentOnlineTime = new javax.swing.JLabel();
        jCheckBox_SaveURL = new javax.swing.JCheckBox();
        jLabel_Status = new javax.swing.JLabel();
        jLabel_CurrentStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_Main.setBackground(new java.awt.Color(255, 255, 255));

        jTextField_URL.setToolTipText("Correct URL: http://control.poe.trade/...");
        jTextField_URL.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_URLActionPerformed(evt);
            }
        });

        jLabel_URL.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel_URL.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_URL.setText("URL:");

        jButton_Connect.setBackground(new java.awt.Color(128, 255, 19));
        jButton_Connect.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton_Connect.setForeground(new java.awt.Color(0, 0, 0));
        jButton_Connect.setText("Connect");
        jButton_Connect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ConnectActionPerformed(evt);
            }
        });

        jLabel_OnlineTime.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel_OnlineTime.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_OnlineTime.setText("Online time:");

        jLabel_CurrentOnlineTime.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel_CurrentOnlineTime.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_CurrentOnlineTime.setText("0");

        jCheckBox_SaveURL.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox_SaveURL.setText("Save URL");

        jLabel_Status.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel_Status.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Status.setText("Status:");

        jLabel_CurrentStatus.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel_CurrentStatus.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_CurrentStatus.setText("Offline");

        javax.swing.GroupLayout jPanel_MainLayout = new javax.swing.GroupLayout(jPanel_Main);
        jPanel_Main.setLayout(jPanel_MainLayout);
        jPanel_MainLayout.setHorizontalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel_OnlineTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_CurrentOnlineTime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(jPanel_MainLayout.createSequentialGroup()
                        .addComponent(jLabel_Status)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_CurrentStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Connect, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_MainLayout.createSequentialGroup()
                        .addComponent(jLabel_URL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_SaveURL)
                            .addComponent(jTextField_URL, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_MainLayout.setVerticalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_OnlineTime)
                    .addComponent(jLabel_CurrentOnlineTime))
                .addGap(18, 18, 18)
                .addGroup(jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_URL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_URL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox_SaveURL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Connect)
                    .addComponent(jLabel_Status)
                    .addComponent(jLabel_CurrentStatus))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_URLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_URLActionPerformed

    }//GEN-LAST:event_jTextField_URLActionPerformed

    private void jButton_ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConnectActionPerformed
		connection.connect(jTextField_URL.getText());
    }//GEN-LAST:event_jButton_ConnectActionPerformed

	public static void main(String args[])
	{
		try
		{
			for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			{
				if("Nimbus".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch(ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch(InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch(IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch(javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainJFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Connect;
    private javax.swing.JCheckBox jCheckBox_SaveURL;
    private javax.swing.JLabel jLabel_CurrentOnlineTime;
    private javax.swing.JLabel jLabel_CurrentStatus;
    private javax.swing.JLabel jLabel_OnlineTime;
    private javax.swing.JLabel jLabel_Status;
    private javax.swing.JLabel jLabel_URL;
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JTextField jTextField_URL;
    // End of variables declaration//GEN-END:variables
}
