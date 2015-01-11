package database;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class awal extends JFrame{
	private JToolBar toolbar =new JToolBar();
	private static JDesktopPane dp=new JDesktopPane();
	private static login lg=new login();
	private final static JButton btnLogin = new JButton("login");
	private final static JButton btnLogout = new JButton("logout");
	
	public static void tampil(javax.swing.JInternalFrame f){
		lg.setVisible(false);
		Dimension size=dp.getSize();
		dp.add(f);
		f.setVisible(true);
		f.setSize(size);
		f.setLocation(0,0);
		btnLogin.setVisible(false);
		btnLogout.setVisible(true);
	}
	public static void tidaktampil(javax.swing.JInternalFrame f){
		f.setVisible(false);
		dp.removeAll();
		dp.add(lg);
		lg.setVisible(true);
		btnLogin.setVisible(true);
		btnLogout.setVisible(false);
	}
	
	
	
	public awal()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container konten=getContentPane();
		konten.setLayout(new BorderLayout());
		JTextField txtid=new JTextField();
		getContentPane().add(toolbar,BorderLayout.NORTH);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				dp.add(lg);
				lg.setVisible(true);
				
			}
		});
		
		toolbar.add(btnLogin);
		btnLogout.setVisible(false);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.hehe();
			}
		});
		
		toolbar.add(btnLogout);
		getContentPane().add(dp,BorderLayout.CENTER);
		setVisible(true);
		setSize(789,464);
		
	}
	public static void main(String []args)
	{
		awal awl=new awal();
	}
}
