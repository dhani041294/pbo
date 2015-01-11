package database;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class pengguna extends JInternalFrame {
	private JTextField txtUser;
	private JTextField txtPass;
	private JTable table;
	private DefaultTableModel tabelModel;

	/**
	 * Launch the application.
	 */
	public void refresh()
	{
		txtUser.setText("");
		txtPass.setText("");
		
	}
	public pengguna() {
		setBounds(100, 100, 638, 377);
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(10, 130, 72, 14);
		getContentPane().add(lblUsername);
		
		txtUser = new JTextField();
		txtUser.setBounds(76, 127, 276, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(10, 155, 72, 14);
		getContentPane().add(lblPassword);
		
		txtPass = new JTextField();
		txtPass.setBounds(76, 152, 276, 20);
		getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"admin", "kasir"}));
		comboBox.setBounds(76, 180, 72, 20);
		getContentPane().add(comboBox);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(10, 183, 46, 14);
		getContentPane().add(lblStatus);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 725, 108);
		getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String uname = (String) tabelModel.getValueAt(a, 0);
		        txtUser.setText(uname);
		        String pass = (String) tabelModel.getValueAt(a, 1);
		        txtPass.setText(pass);
		        String stat = (String) tabelModel.getValueAt(a, 2);
		        comboBox.setSelectedItem(stat);
		        
		        txtUser.setEnabled(false);
			}
		});
		scrollPane_1.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("Username");
        tabelModel.addColumn("Password");
        tabelModel.addColumn("Status");
		table.setModel(tabelModel);	
        tampilTabel();
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\Save-icon.png"));
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			        Connection konek = config.getCon();
			        String query = "INSERT INTO pengguna VALUES(?,?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, txtUser.getText());
			        prepare.setString(2, txtPass.getText());
			        prepare.setString(3, (String) comboBox.getSelectedItem());


			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            refresh();
			        }
			}
		});
		btnTambah.setBounds(265, 284, 113, 23);
		getContentPane().add(btnTambah);
		
		JButton button = new JButton("Ubah");
		button.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\edit-validated-icon.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = config.getCon();
			        String query = "UPDATE pengguna SET  password = ?, status = ? WHERE username = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, txtPass.getText());
			        prepare.setString(2, (String) comboBox.getSelectedItem());
			        prepare.setString(3, txtUser.getText());
			        
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            txtUser.setEnabled(true);
			            refresh();            
			        }
			}
		});
		button.setBounds(388, 284, 89, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Hapus");
		button_1.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\Actions-window-close-icon.png"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			            Connection konek = config.getCon();
			            String query = "DELETE FROM pengguna WHERE username = ?";
			            PreparedStatement prepare = konek.prepareStatement(query);
			            
			            prepare.setString(1, txtUser.getText());
			            prepare.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
			            prepare.close();
			        }
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            txtUser.setEnabled(true);
			            refresh();
			        }
			}
		});
		button_1.setBounds(487, 284, 113, 23);
		getContentPane().add(button_1);

	}
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = config.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM pengguna";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
