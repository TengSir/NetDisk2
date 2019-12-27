package edu.hbuas.netdisk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import edu.hbuas.netdisk.model.UserDAO;
import edu.hbuas.netdisk.model.Users;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private UserDAO dao;
	private JLayeredPane layeredPane;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					LoginFrame frame = new LoginFrame();
					frame.setLocationRelativeTo(null);
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
	public LoginFrame() {
		dao=new UserDAO();
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
		
		label = new JLabel("\u7F51\u76D8\u8D26\u6237\uFF1A");
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setBounds(230, 184, 69, 15);
		panel_1.add(label);
		
		textField = new JTextField("test");
		textField.setBounds(324, 181, 171, 21);
		panel_1.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("\u7F51\u76D8\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(lblNewLabel_1.getFont().deriveFont(lblNewLabel_1.getFont().getStyle() | Font.BOLD));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(230, 233, 69, 15);
		panel_1.add(lblNewLabel_1);
		
		passwordField = new JPasswordField("test");
		passwordField.setBounds(324, 230, 171, 21);
		panel_1.add(passwordField);
		
		btnNewButton = new JButton("\u7ACB\u5373\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1.�Ȼ�ȡ�û��ڴ�����������û���������
				String username=textField.getText().trim();
				String password=passwordField.getText();
				//2.����֤
				
				
				//3.�������ݿⷽ����ѯ���˻����������Ƿ����
				Users u=dao.login(username, password);
				//4.���ݲ�ѯ�����תҳ�����֪ͨ�û�
				if(u==null) {
					JOptionPane.showMessageDialog(LoginFrame.this, "��¼ʧ��", "��ܰ��ʾ", JOptionPane.ERROR_MESSAGE);
				}else {
					MainFrame  m=new MainFrame(u);
					m.setVisible(true);
					m.setLocationRelativeTo(null);
					LoginFrame.this.dispose();//���ص�ǰ��¼����
				}
			}
		});
		btnNewButton.setBounds(230, 319, 93, 23);
		panel_1.add(btnNewButton);
		
		JButton button = new JButton("\u6CE8\u518C\u8D26\u6237");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame m=new RegisterFrame();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
				LoginFrame.this.setVisible(false);
			}
		});
		button.setBounds(402, 319, 93, 23);
		panel_1.add(button);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 784, 462);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 784, 462);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setIcon(new ImageIcon("sources/imgs/back.jpg"));
		panel.add(lblNewLabel);
	}
}
