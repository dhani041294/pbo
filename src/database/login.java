package database;
import java.sql.*;

import javax.swing.JOptionPane;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JInternalFrame {
	private JButton btnLogin;
	private static JTextField txtUser;
	private static JPasswordField txtPass;
	private JLabel lbl_back;
	public static javax.swing.JInternalFrame f;
	public static javax.swing.JInternalFrame f2;
	public static String nama;
	private static int status;
	private JLabel label;
	/**
	 * Create the frame.
	 */
	public login() {
		super("Login");
		setClosable(true);
		setIconifiable(true);
		setBounds(400, 150, 600, 375);
		getContentPane().setLayout(null);
		setVisible(true);
		f=new admin();
		f2=new kasir();
		
		JLabel lbl_nama = new JLabel("Username : ");
		lbl_nama.setBounds(42, 144, 100, 35);
		getContentPane().add(lbl_nama);
		
		JLabel lbl_pass = new JLabel("Password : ");
		lbl_pass.setBounds(42, 190, 100, 27);
		getContentPane().add(lbl_pass);
		
		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon("C:\\Users\\dhani\\Downloads\\penjualan_tiket\\src\\icon\\login.png"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("com.mckoi.JDBCDriver");
					Connection conn=DriverManager.getConnection("jdbc:mckoi://localhost/","dhani","123");
			        String sql = "Select * from pengguna where username='"+txtUser.getText()+"' and password='" + txtPass.getText() + "'";
			        Statement st=conn.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        if (rs.next()){
			        if (rs.getString(3).equalsIgnoreCase("admin"))
			        {
			            JOptionPane.showMessageDialog(null, "Login berhasil");
			            status=0;
			            awal.tampil(f);
			            nama=txtUser.getText();
			        }else{
			        	
			            JOptionPane.showMessageDialog(null, "Login berhasil");
			            status=1;
			            awal.tampil(f2);
			            nama=txtUser.getText();
			           
			        }
			        }else{
			        JOptionPane.showMessageDialog(null, "Login gagal");
			        }
			        }catch (Exception ex){
			        JOptionPane.showMessageDialog(null, "koneksi gagal");
			        }
			}
		});
		btnLogin.setBounds(375, 256, 138, 64);
		getContentPane().add(btnLogin);
		
		txtUser = new JTextField();
		txtUser.setBounds(152, 144, 365, 27);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(152, 190, 365, 27);
		getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\Login_title.png"));
		label.setBounds(152, -25, 321, 159);
		getContentPane().add(label);
		
		lbl_back = new JLabel("");
		lbl_back.setIcon(new ImageIcon("C:\\Users\\dhani\\Downloads\\background-login.jpg"));
		lbl_back.setBounds(0, 0, 584, 346);
		getContentPane().add(lbl_back);
		
		
		
		

	}
	public static void hehe()
	{
		txtUser.setText("");
		txtPass.setText("");
		if (status==0){
		awal.tidaktampil(f);
		}
		else if (status==1)
		{
			awal.tidaktampil(f2);
		}
	}
}
