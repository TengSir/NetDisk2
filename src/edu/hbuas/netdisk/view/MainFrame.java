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
		
		button = new JButton("上传");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					//1.弹出文件选择框获取用户选择要上传的文件
						JFileChooser  fc=new JFileChooser();
						fc.showOpenDialog(MainFrame.this);
						
						File  file=fc.getSelectedFile();//获取用户从选择框上选择的文件
						
						if(file==null) {
							return ;
						}
						System.out.println(file.getAbsolutePath());
				    //2.建立socket链接
						ObjectOutputStream  out=null;
						ObjectInputStream  in=null;
						Socket  client=null;
					 try {
						  client=new Socket(SocketConfig.netDiskServerIP,SocketConfig.netDiskServerPort);
						out=new ObjectOutputStream(client.getOutputStream());
						in=new ObjectInputStream(client.getInputStream());
						System.out.println("连接服务器成功");
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				    //3.封装一个消息对象，消息对象中标记好当前这个连接想要执行什么动作
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
					
				    //4.使用socket的流开始上传数据
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
					 
				    //6.上传完毕提示结果
					 JOptionPane.showMessageDialog(MainFrame.this, "文件上传成功!", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
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
		
		
		
		//构造方法是主窗口加载过程中必须调用的方法
				//所以读取文件列表的方法就可以在这里执行
				Message  getAllFilesMessage=new Message();
				getAllFilesMessage.setFrom(user);
				getAllFilesMessage.setType(MessageType.LISTALLFILES);
				
				//消息封装完毕就需要将消息发送出去，这里需要创建socket对象（本项目采用短连接）
				try {
					Socket  client=new Socket(SocketConfig.netDiskServerIP,SocketConfig.netDiskServerPort);
					ObjectOutputStream  out=new ObjectOutputStream(client.getOutputStream());
					ObjectInputStream  in=new ObjectInputStream(client.getInputStream());
					out.writeObject(getAllFilesMessage);//将消息对象发送给服务器
					out.flush();
					System.out.println("已经将消息发送给服务器");
					
					//消息发送过去后服务器肯定会处理消息，并给我返回一个所有文件的消息对象
					//这里需要使用输入流读取服务器给我的消息
					Message allFiles=(Message)in.readObject();
					System.out.println("接受服務器消息完畢");
					int x=10;
					int y=10;
					 for(File f:allFiles.getFiles()) {
						 System.out.println(f.getAbsolutePath());
						 //如下代码是根据当前文件类型加载对应的图标显示在网盘列表中
						 JPanel  oneFile=new JPanel();//显示文件的面板
						 oneFile.addMouseListener(new MouseAdapter() {
							 @Override
							public void mouseClicked(MouseEvent e) {
								 //鼠标事件
								 if(e.getClickCount()==1&&e.getButton()==3) {//鼠标点击了右键
									
									 //2.生成一个右键菜单对象，用来显示一个可操作的菜单给用户操作
									 JPopupMenu  menu=new JPopupMenu();
									 JMenuItem  down=new JMenuItem("下载");
									 JMenuItem  delete=new JMenuItem("删除");
									 menu.add(down);
									 menu.addSeparator();
									 menu.add(delete);
//									 menu.setLocation(e.getXOnScreen(), e.getYOnScreen());
									 menu.show(oneFile, e.getX(), e.getY());
									 
									 //3.给下载的菜单添加点击事件，处理用户真正点击下载时应该执行的业务代码
									 down.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											//点击下载按钮后执行的业务代码
											//1.先获得用户要下载的文件名
											String fileName=((JLabel)oneFile.getComponents()[1]).getText();//1.获得用户在哪个文件上面点击了右键，获得该文件名
											 System.out.println(fileName);	
											 
											 //2.封装一个消息发送给服务器通知它我要下载文件
											 Message  willDownloadMessage=new Message();
											 willDownloadMessage.setFilename(fileName);
											 willDownloadMessage.setFrom(user);
											 willDownloadMessage.setType(MessageType.DOWNLOAD);
											 
											 
											 //3.使用socket将这个消息发送给服务器
											 try {
												Socket  client=new Socket(SocketConfig.netDiskServerIP,SocketConfig.netDiskServerPort);
												ObjectOutputStream  out=new ObjectOutputStream(client.getOutputStream());
												ObjectInputStream  in=new ObjectInputStream(client.getInputStream());
												out.writeObject(willDownloadMessage);//将消息对象发送给服务器
												out.flush();
												
												//客户端想要下载的消息发给服务器之后，服务器接收到肯定会处理并且会给用户发送文件数据
												//4.弹出文件选择框让用户选择文件保存位置
												JFileChooser  fc=new JFileChooser();
												fc.showSaveDialog(MainFrame.this);
												File selectPath=fc.getSelectedFile();
												System.out.println(selectPath.getAbsolutePath());
													if(!selectPath.exists()) selectPath.mkdirs();

												//5.文件路径选择完毕就可以使用IO流来读取文件数据并存入文件
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
												
												
												//6.文件传输完毕，弹出提示框提示用户操作结果
												JOptionPane.showMessageDialog(MainFrame.this, "文件下载成功!", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
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
	 * 向服务器发送请求获得用户的所有文件列表
	 */
	private void showAllFiles(Message allFiles) {
		System.out.println("显示所有文件的方法");
		panel_3.removeAll();
		int x=10;
		int y=10;
		 for(File f:allFiles.getFiles()) {
			 System.out.println(f.getAbsolutePath());
			 //如下代码是根据当前文件类型加载对应的图标显示在网盘列表中
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
