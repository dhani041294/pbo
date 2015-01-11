package database;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.Color;

public class admin extends JInternalFrame {
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public admin() {
		
		setBounds(100, 100, 1366, 720);
		setLocation(0,0);
		this.setTitle("Admin");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_product = new JPanel();
		panel_product.setBackground(new Color(102, 153, 255));
		tabbedPane.addTab("Maitenance Product", null, panel_product, null);
		panel_product.setLayout(null);
		
		
		final JDesktopPane dp_product = new JDesktopPane();
		dp_product.setBounds(350, 50, 753, 500);
		panel_product.add(dp_product);
		
		JPanel panel_suplier = new JPanel();
		panel_suplier.setBackground(new Color(102, 153, 255));
		tabbedPane.addTab("Maitenance Supplier", null, panel_suplier, null);
		panel_suplier.setLayout(null);
		
		final JDesktopPane dp_psuplier = new JDesktopPane();
		dp_psuplier.setBounds(350, 50, 755,500);
		panel_suplier.add(dp_psuplier);
		
		JPanel panel_user = new JPanel();
		panel_user.setBackground(new Color(102, 153, 255));
		tabbedPane.addTab("Maintenance User", null, panel_user, null);
		panel_user.setLayout(null);
		
		//Dimension sizePanel = panel_laporan.getBounds();
		final JDesktopPane dp_user = new JDesktopPane();
		dp_user.setBounds(350, 50, 755,500);
		panel_user.add(dp_user);
		
		JPanel panel_laporan = new JPanel();
		panel_laporan.setBackground(new Color(102, 153, 255));
		tabbedPane.addTab("Laporan", null, panel_laporan, null);
		panel_laporan.setLayout(null);
		
		//Dimension sizePanel = panel_laporan.getBounds();
		final JDesktopPane dp_laporan = new JDesktopPane();
		dp_laporan.setBounds(350, 50, 755,500);
		panel_laporan.add(dp_laporan);
		
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pengguna p = new pengguna();
				suplier s =new suplier();
				laporan l=new laporan();
				Dimension size=dp_user.getSize();
				Dimension size2=dp_psuplier.getSize();
				Dimension size3=dp_laporan.getSize();
				dp_user.add(p);
				dp_psuplier.add(s);
				dp_laporan.add(l);
				p.setVisible(true);
				p.setSize(size);
				p.setLocation(0,0);
				s.setVisible(true);
				s.setSize(size2);
				s.setLocation(0,0);
				l.setVisible(true);
				l.setSize(size3);
				l.setLocation(0,0);

				
			}
		});
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				produk p = new produk ();
				Dimension size2=dp_product.getSize();
				dp_product.add(p);
				p.setVisible(true);
				p.setSize(size2);
				p.setLocation(0,0);
			}
		});
	}
	
	public javax.swing.JInternalFrame  returnAdmin(){
		return this;
	}

}
