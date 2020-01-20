package gestionare;

import java.sql.*;
import java.awt.Font;
import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private Catalog catalog;

	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Login() {
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(500, 360));
		setMaximumSize(new Dimension(500, 360));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setName("dialogLogin");
		setResizable(false);
		setSize(new Dimension(500, 360));
		setTitle("LOGIN catalog virtual CNVA 2019-2020");
		setType(Type.POPUP);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel labelUsername = new JLabel("Username:");
		labelUsername.setBackground(Color.WHITE);
		labelUsername.setBounds(105, 107, 69, 21);
		labelUsername.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		getContentPane().add(labelUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBackground(Color.WHITE);
		textFieldUsername.setBounds(195, 104, 200, 27);
		textFieldUsername.setColumns(10);
		textFieldUsername.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(textFieldUsername);
		
		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setBackground(Color.WHITE);
		labelPassword.setBounds(110, 149, 64, 21);
		labelPassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		getContentPane().add(labelPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(195, 146, 200, 27);
		passwordField.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(passwordField);
		
		JButton butonLogin = new JButton("LOGIN");
		butonLogin.setBackground(Color.LIGHT_GRAY);
		butonLogin.setBounds(187, 203, 120, 23);
		butonLogin.setFocusPainted(false);
		butonLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		getContentPane().add(butonLogin);
		
		butonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conexiune = DriverManager.getConnection("jdbc:mysql://localhost:3306/cnva", "dbuser", "dbpassword");
					Statement stmn = conexiune.createStatement();
					String interogare = "SELECT * FROM tabel_login " +
							"WHERE username = '" + textFieldUsername.getText() + "' AND password = '" + passwordField.getText().toString() + "'";
					ResultSet rezultat = stmn.executeQuery(interogare);
					if (rezultat.next()) {
						JOptionPane.showMessageDialog(null, "Successful Login");
						catalog.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect USERNAME or PASSWORD! Please try again!");
					}
					conexiune.close();
				} catch (Exception exc) {
					System.out.println(exc);
				}
			}
		});
	}
}
