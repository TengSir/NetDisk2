package edu.hbuas.netdisk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import edu.hbuas.netdisk.model.UserDAO;
import edu.hbuas.netdisk.model.Users;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField_1;
	private JLabel label_1;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JLayeredPane layeredPane;
	private JPasswordField passwordField;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JLabel label_4;
	private JTextField textField_1;
	private JLabel label_5;
	private JComboBox comboBox;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JButton button;
	private ButtonGroup  sex;
	
	private  UserDAO  dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		dao=new UserDAO();
		setTitle("Java\u7F51\u76D8");
		setResizable(false);
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
		
		label = new JLabel("\u7F51\u76D8\u8D26\u53F7:");
		label.setForeground(Color.ORANGE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD));
		label.setBounds(229, 46, 85, 15);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setBounds(337, 43, 168, 21);
		panel_1.add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("\u7F51\u76D8\u5BC6\u7801:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.ORANGE);
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD));
		label_1.setBounds(229, 85, 85, 15);
		panel_1.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(337, 82, 168, 21);
		panel_1.add(passwordField);
		
		label_2 = new JLabel("\u7F51\u76D8\u5BC6\u7801:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.ORANGE);
		label_2.setFont(label_2.getFont().deriveFont(label_2.getFont().getStyle() | Font.BOLD));
		label_2.setBounds(229, 126, 85, 15);
		panel_1.add(label_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(337, 123, 168, 21);
		panel_1.add(passwordField_1);
		
		label_3 = new JLabel("\u7528\u6237\u6027\u522B:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.ORANGE);
		label_3.setFont(label_3.getFont().deriveFont(label_3.getFont().getStyle() | Font.BOLD));
		label_3.setBounds(229, 164, 85, 15);
		panel_1.add(label_3);
		
		sex=new ButtonGroup();
		radioButton = new JRadioButton("\u7537");
		radioButton.setSelected(true);
		radioButton.setBounds(337, 160, 76, 23);
		sex.add(radioButton);
		panel_1.add(radioButton);
		
		radioButton_1 = new JRadioButton("\u5973");
		sex.add(radioButton_1);
		radioButton_1.setBounds(428, 160, 76, 23);
		panel_1.add(radioButton_1);
		
		label_4 = new JLabel("\u7528\u6237\u5E74\u9F84:");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.ORANGE);
		label_4.setFont(label_4.getFont().deriveFont(label_4.getFont().getStyle() | Font.BOLD));
		label_4.setBounds(229, 202, 85, 15);
		panel_1.add(label_4);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setBounds(339, 199, 166, 21);
		panel_1.add(comboBox);
		
		label_5 = new JLabel("\u7528\u6237\u90AE\u7BB1:");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.ORANGE);
		label_5.setFont(label_5.getFont().deriveFont(label_5.getFont().getStyle() | Font.BOLD));
		label_5.setBounds(229, 243, 85, 15);
		panel_1.add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(337, 240, 168, 21);
		panel_1.add(textField_1);
		
		JLabel label_6 = new JLabel("\u624B\u673A\u53F7\u7801:");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.ORANGE);
		label_6.setFont(label_6.getFont().deriveFont(label_6.getFont().getStyle() | Font.BOLD));
		label_6.setBounds(229, 283, 85, 15);
		panel_1.add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(337, 280, 168, 21);
		panel_1.add(textField_2);
		
		btnNewButton = new JButton("\u7ACB\u5373\u6CE8\u518C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击注册按钮的事件监听方法
				//1.先获取ui窗口上用户输入的各项内容
				String username=textField.getText().trim();
				String password=passwordField.getText();
				String querenpassword=passwordField.getText();
				String sex=radioButton.isSelected()?"男":"女";
				
				int  age=Integer.parseInt(comboBox.getSelectedItem().toString());
				
				String email=textField_1.getText().trim();
				String tel=textField_2.getText().trim();
				Users  u=new Users();
				u.setAge(age);
				u.setEmail(email);
				u.setSex(sex);
				u.setUsername(username);
				u.setPassword(password);
				u.setTel(tel);
				System.out.println(u);
				//2.表单验证
				
				//3.执行数据库插入操作，注册用户信息
				boolean result=dao.registerUser(u);
				//4.根据返回值执行具体的跳转或者通知
				if(result) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "注册成功", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(RegisterFrame.this, "注册失败", "温馨提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(246, 363, 93, 23);
		panel_1.add(btnNewButton);
		
		button = new JButton("\u8FD4\u56DE\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame m=new LoginFrame();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
				RegisterFrame.this.setVisible(false);
			}
		});
		button.setBounds(412, 363, 93, 23);
		panel_1.add(button);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 784, 462);
		layeredPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("sources/imgs/back.jpg"));
		panel.add(lblNewLabel, BorderLayout.CENTER);
	}
}
