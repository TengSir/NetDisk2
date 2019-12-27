package edu.hbuas.netdisk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import edu.hbuas.netdisk.config.SocketConfig;
import edu.hbuas.netdisk.model.Message;
import edu.hbuas.netdisk.model.MessageType;
import edu.hbuas.netdisk.model.Users;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLayeredPane layeredPane;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JButton button;
	private JButton button_1;
	private JLabel lblgg;
	private JButton button_2;
	private JButton button_3;
	private JPanel panel_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel label_1;
	private JLabel lblOfficeexe;
	private JLabel label_2;
	private JLabel lblzip;
	
	private Users user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame(Users user) {
		
		
		
		this.user=user;
		setResizable(false);
		setTitle("Java\u7F51\u76D8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 0, 784, 462);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 0, 217, 462);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("sources/imgs/default.png").getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		lblNewLabel_1.setBounds(56, 20, 100, 100);
		panel_2.add(lblNewLabel_1);
		
		label = new JLabel(user.getUsername());
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(82, 144, 54, 15);
		panel_2.add(label);
		
		button = new JButton("�ϴ�");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					//1.�����ļ�ѡ����ȡ�û�ѡ��Ҫ�ϴ����ļ�
						JFileChooser  fc=new JFileChooser();
						fc.showOpenDialog(MainFrame.this);
						
						File  file=fc.getSelectedFile();//��ȡ�û���ѡ�����ѡ����ļ�
						
						if(file==null) {
							return ;
						}
						System.out.println(file.getAbsolutePath());
				    //2.����socket����
						ObjectOutputStream  out=null;
						ObjectInputStream  in=null;
						Socket  client=null;
					 try {
						  client=new Socket(SocketConfig.netDiskServerIP,SocketConfig.netDiskServerPort);
						out=new ObjectOutputStream(client.getOutputStream());
						in=new ObjectInputStream(client.getInputStream());
						System.out.println("���ӷ������ɹ�");
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				    //3.��װһ����Ϣ������Ϣ�����б�Ǻõ�ǰ���������Ҫִ��ʲô����
					 Message  m=new Message();
					 m.setFrom(user);
					 m.setFileSize(file.length());
					 m.setFilename(file.getName());
					 m.setType(MessageType.UPLOAD);
					 
					 try {
						out.writeObject(m);
						 out.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				    //4.ʹ��socket������ʼ�ϴ�����
					 try {
						FileInputStream fileIn=new FileInputStream(file);
						byte[] bs=new byte[1024];
						int length=-1;
						while((length=fileIn.read(bs))!=-1) {
							out.write(bs,0,length);
							out.flush();
						}
						out.close();
						in.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					 
				    //6.�ϴ������ʾ���
					 JOptionPane.showMessageDialog(MainFrame.this, "�ļ��ϴ��ɹ�!", "��ܰ��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button.setBounds(63, 193, 93, 23);
		panel_2.add(button);
		
		button_1 = new JButton("\u6211\u7684\u7F51\u76D8");
		button_1.setBounds(63, 240, 93, 23);
		panel_2.add(button_1);
		
		lblgg = new JLabel("\u5F53\u524D5G/\u5269\u4F5915G");
		lblgg.setForeground(Color.ORANGE);
		lblgg.setFont(lblgg.getFont().deriveFont(lblgg.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblgg.setHorizontalAlignment(SwingConstants.CENTER);
		lblgg.setBounds(38, 168, 138, 15);
		panel_2.add(lblgg);
		
		button_2 = new JButton("\u5347\u7EA7\u6210\u4F1A\u5458");
		button_2.setBounds(63, 291, 93, 23);
		panel_2.add(button_2);
		
		button_3 = new JButton("\u9000\u51FA\u7F51\u76D8");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame m=new LoginFrame();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
				MainFrame.this.setVisible(false);
			}
		});
		button_3.setBounds(63, 343, 93, 23);
		panel_2.add(button_3);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(217, 0, 567, 462);
		panel_3.setLayout(null);
		panel_1.add(panel_3);
		
	
		
		panel = new JPanel();
		panel.setBounds(0, 0, 784, 462);
		layeredPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("sources/imgs/back.jpg"));
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		
		
		//���췽���������ڼ��ع����б�����õķ���
				//���Զ�ȡ�ļ��б�ķ����Ϳ���������ִ��
				Message  getAllFilesMessage=new Message();
				getAllFilesMessage.setFrom(user);
				getAllFilesMessage.setType(MessageType.LISTALLFILES);
				
				//��Ϣ��װ��Ͼ���Ҫ����Ϣ���ͳ�ȥ��������Ҫ����socket���󣨱���Ŀ���ö����ӣ�
				try {
					Socket  client=new Socket(SocketConfig.netDiskServerIP,SocketConfig.netDiskServerPort);
					ObjectOutputStream  out=new ObjectOutputStream(client.getOutputStream());
					ObjectInputStream  in=new ObjectInputStream(client.getInputStream());
					out.writeObject(getAllFilesMessage);//����Ϣ�����͸�������
					out.flush();
					System.out.println("�Ѿ�����Ϣ���͸�������");
					
					//��Ϣ���͹�ȥ��������϶��ᴦ����Ϣ�������ҷ���һ�������ļ�����Ϣ����
					//������Ҫʹ����������ȡ���������ҵ���Ϣ
					Message allFiles=(Message)in.readObject();
					System.out.println("���ܷ�������Ϣ�ꮅ");
					int x=10;
					int y=10;
					 for(File f:allFiles.getFiles()) {
						 System.out.println(f.getAbsolutePath());
						 //���´����Ǹ��ݵ�ǰ�ļ����ͼ��ض�Ӧ��ͼ����ʾ�������б���
						 JPanel  oneFile=new JPanel();//��ʾ�ļ������
						 oneFile.addMouseListener(new MouseAdapter() {
							 @Override
							public void mouseClicked(MouseEvent e) {
								 //����¼�
								 if(e.getClickCount()==1&&e.getButton()==3) {//��������Ҽ�
									
									 //2.����һ���Ҽ��˵�����������ʾһ���ɲ����Ĳ˵����û�����
									 JPopupMenu  menu=new JPopupMenu();
									 JMenuItem  down=new JMenuItem("����");
									 JMenuItem  delete=new JMenuItem("ɾ��");
									 menu.add(down);
									 menu.addSeparator();
									 menu.add(delete);
//									 menu.setLocation(e.getXOnScreen(), e.getYOnScreen());
									 menu.show(oneFile, e.getX(), e.getY());
									 
									 //3.�����صĲ˵���ӵ���¼��������û������������ʱӦ��ִ�е�ҵ�����
									 down.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											//������ذ�ť��ִ�е�ҵ�����
											//1.�Ȼ���û�Ҫ���ص��ļ���
											String fileName=((JLabel)oneFile.getComponents()[1]).getText();//1.����û����ĸ��ļ����������Ҽ�����ø��ļ���
											 System.out.println(fileName);	
											 
											 //2.��װһ����Ϣ���͸�������֪ͨ����Ҫ�����ļ�
											 Message  willDownloadMessage=new Message();
											 willDownloadMessage.setFilename(fileName);
											 willDownloadMessage.setFrom(user);
											 willDownloadMessage.setType(MessageType.DOWNLOAD);
											 
											 
											 //3.ʹ��socket�������Ϣ���͸�������
											 try {
												Socket  client=new Socket(SocketConfig.netDiskServerIP,SocketConfig.netDiskServerPort);
												ObjectOutputStream  out=new ObjectOutputStream(client.getOutputStream());
												ObjectInputStream  in=new ObjectInputStream(client.getInputStream());
												out.writeObject(willDownloadMessage);//����Ϣ�����͸�������
												out.flush();
												
												//�ͻ�����Ҫ���ص���Ϣ����������֮�󣬷��������յ��϶��ᴦ���һ���û������ļ�����
												//4.�����ļ�ѡ������û�ѡ���ļ�����λ��
												JFileChooser  fc=new JFileChooser();
												fc.showSaveDialog(MainFrame.this);
												File selectPath=fc.getSelectedFile();
												System.out.println(selectPath.getAbsolutePath());
													if(!selectPath.exists()) selectPath.mkdirs();

												//5.�ļ�·��ѡ����ϾͿ���ʹ��IO������ȡ�ļ����ݲ������ļ�
												FileOutputStream  fileOut=new FileOutputStream(new File(selectPath,fileName));
												byte[] bs=new byte[1024];
												int length=-1;
												while((length=in.read(bs))!=-1) {
													fileOut.write(bs,0,length);
													fileOut.flush();
												}
												out.close();
												fileOut.close();
												in.close();
												
												
												//6.�ļ�������ϣ�������ʾ����ʾ�û��������
												JOptionPane.showMessageDialog(MainFrame.this, "�ļ����سɹ�!", "��ܰ��ʾ", JOptionPane.INFORMATION_MESSAGE);
											 }catch (Exception eee) {
												 eee.printStackTrace();
											}
											 
										}
									});
									 
								 }
							 }
						});
						 oneFile.setLayout(null);
						 oneFile.setBounds(x, y, 80, 100);
						 oneFile.setBorder(BorderFactory.createLineBorder(Color.black));
						 String  fileExt=f.getName().substring(f.getName().lastIndexOf(".")+1,f.getName().length());
						 File  fileIcon=new File("sources/imgs/fileicons/"+fileExt+".gif");
						 JLabel  fileImage=null;
						 if(fileIcon.exists()) {
							 if(f.isDirectory()) {
								 fileImage=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("sources/imgs/fileicons/dir.gif").getScaledInstance(78, 78, Image.SCALE_DEFAULT)));
							 }else {
								 fileImage=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("sources/imgs/fileicons/"+fileExt+".gif").getScaledInstance(78, 78, Image.SCALE_DEFAULT)));
							 }
							 }else {
							  fileImage=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("sources/imgs/fileicons/file.gif").getScaledInstance(78, 78, Image.SCALE_DEFAULT)));
						 }
						 fileImage.setBounds(1, 1, 78, 78);
						 JLabel  fileName=new JLabel(f.getName(),SwingConstants.CENTER);
						 fileName.setToolTipText(f.getName());
						 fileName.setBounds(0, 80, 80, 20);
						 oneFile.add(fileImage);
						 oneFile.add(fileName);
						 panel_3.add(oneFile);
						 x+=90;
						 if(x>=480) {
							 x=10;
							 y+=120;
						 }
						 
					 }
					 client.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		
		
	}
	
	
	/**
	 * ������������������û��������ļ��б�
	 */
	private void showAllFiles(Message allFiles) {
		System.out.println("��ʾ�����ļ��ķ���");
		panel_3.removeAll();
		int x=10;
		int y=10;
		 for(File f:allFiles.getFiles()) {
			 System.out.println(f.getAbsolutePath());
			 //���´����Ǹ��ݵ�ǰ�ļ����ͼ��ض�Ӧ��ͼ����ʾ�������б���
			 JPanel  oneFile=new JPanel();
			 oneFile.setLayout(null);
			 oneFile.setBounds(x, y, 80, 100);
			 String  fileExt=f.getName().substring(f.getName().lastIndexOf(".")+1,f.getName().length());
			 File  fileIcon=new File("sources/imgs/fileicons/"+fileExt+".gif");
			 JLabel  fileImage=null;
			 if(fileIcon.exists()) {
				 if(f.isDirectory()) {
					 fileImage=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("sources/imgs/fileicons/dir.gif").getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
				 }else {
					 fileImage=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("sources/imgs/fileicons/"+fileExt+".gif").getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
				 }
				 }else {
				  fileImage=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("sources/imgs/fileicons/file.gif").getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
			 }
			 fileImage.setBounds(0, 0, 80, 80);
			 JLabel  fileName=new JLabel(f.getName(),SwingConstants.CENTER);
			 fileName.setToolTipText(f.getName());
			 fileName.setBounds(0, 80, 80, 20);
			 oneFile.add(fileImage);
			 oneFile.add(fileName);
			 panel_3.add(oneFile);
			 x+=90;
			 if(x>=480) {
				 x=10;
				 y+=120;
			 }
			 
		 }
	}

}
