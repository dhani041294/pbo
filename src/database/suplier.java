package database;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class suplier extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel tabelModel;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public void refresh()
	{
		textField.setText("");
		textField_1.setText("");
		
	}
	public suplier() {
		setBounds(100, 100, 579, 452);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 725, 179);
		getContentPane().add(scrollPane);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(10, 222, 31, 24);
		getContentPane().add(lblId);
		
		JLabel lblNamaSuplier = new JLabel("Nama Suplier :");
		lblNamaSuplier.setBounds(10, 253, 81, 24);
		getContentPane().add(lblNamaSuplier);
		
		textField = new JTextField();
		textField.setBounds(130, 222, 409, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 253, 409, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\Actions-window-close-icon.png"));
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
		        {
		            Connection konek = config.getCon();
		            String query = "DELETE FROM suplier WHERE idSupp = ?";
		            PreparedStatement prepare = konek.prepareStatement(query);
		            
		            prepare.setString(1, textField.getText());
		            prepare.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Supplier berhasil dihapus");
		            prepare.close();
		        }
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Supplier gagal dihapus");
		            System.out.println(ex);
		        }
		        finally
		        {
		            tampilTabel();
		            refresh();
		        }
			}
		});
		btnHapus.setBounds(428, 318, 111, 23);
		getContentPane().add(btnHapus);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\edit-validated-icon.png"));
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
			        Connection konek = config.getCon();
			        String query = "UPDATE suplier SET nama = ? WHERE idSupp = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, textField_1.getText());
			        prepare.setString(2, textField.getText());
			        
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Supplier berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Supplier gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            textField.setEnabled(true);
			            refresh();            
			        }
			}
		});
		btnUbah.setBounds(329, 318, 89, 23);
		getContentPane().add(btnUbah);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\Save-icon.png"));
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try
			        {
			        Connection konek = config.getCon();
			        String query = "INSERT INTO suplier VALUES(?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, textField.getText());
			        prepare.setString(2, textField_1.getText());


			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Suplier berhasil disimpan");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Suplier gagal disimpan");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            refresh();
			        }
			}
		});
		btnTambah.setBounds(208, 318, 111, 23);
		getContentPane().add(btnTambah);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String id = (String) tabelModel.getValueAt(a, 0);
		        textField.setText(id);
		        String nama = (String) tabelModel.getValueAt(a, 1);
		        textField_1.setText(nama); 
		        textField.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID");
        tabelModel.addColumn("Nama Supplier");
		table.setModel(tabelModel);	
        tampilTabel();
	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = config.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM suplier";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[2];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
