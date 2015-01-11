package database;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class produk extends JInternalFrame {
	private JTable table;
	private JTextField txtIdProd;
	private JTextField txtIdSup;
	private JTextField txtNmProd;
	private JTextField txtHrg;
	private JComboBox cbSup;
	private JLabel lbltxtIdProduk;
	private JLabel lblSuplier;
	private JLabel lblNamaProduk;
	private JLabel lblHarga;
	private DefaultTableModel tabelModel;
	DefaultComboBoxModel model = new DefaultComboBoxModel(); 
	private JLabel lblStok;
	private JTextField stok;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public void refresh()
	{
		txtIdProd.setText("");
		txtIdSup.setText("");
		txtNmProd.setText("");
		txtHrg.setText("");
		stok.setText("");
	}
	public produk() {
		setBounds(100, 100, 651, 508);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 725, 250);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("ID Supplier");
        tabelModel.addColumn("Harga Produk");
        tabelModel.addColumn("Stok");
		table.setModel(tabelModel);	
        tampilTabel();
		
		txtIdProd = new JTextField();
		txtIdProd.setBounds(143, 272, 405, 20);
		getContentPane().add(txtIdProd);
		txtIdProd.setColumns(10);
		
		txtIdSup = new JTextField();
		txtIdSup.setColumns(10);
		txtIdSup.setBounds(143, 304, 125, 20);
		getContentPane().add(txtIdSup);
		
		txtNmProd = new JTextField();
		txtNmProd.setColumns(10);
		txtNmProd.setBounds(143, 335, 405, 20);
		getContentPane().add(txtNmProd);
		
		txtHrg = new JTextField();
		txtHrg.setColumns(10);
		txtHrg.setBounds(143, 366, 405, 20);
		getContentPane().add(txtHrg);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\Save-icon.png"));
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
		        {
		        Connection konek = config.getCon();
		        String query = "INSERT INTO product VALUES(?,?,?,?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, txtIdProd.getText());
		        prepare.setString(2, txtNmProd.getText());
		        prepare.setString(3, txtIdSup.getText());
		        prepare.setInt(4, Integer.parseInt(txtHrg.getText()));
		        prepare.setInt(5, Integer.parseInt(stok.getText()));

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
		btnTambah.setBounds(233, 429, 103, 23);
		getContentPane().add(btnTambah);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\edit-validated-icon.png"));
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = config.getCon();
			        String query = "UPDATE product SET nama = ?,idSupp=?,harga=?,stok=? WHERE id = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, txtNmProd.getText());
			        prepare.setString(2, txtIdSup.getText());
			        prepare.setInt(3, Integer.parseInt(txtHrg.getText()));
			        prepare.setInt(4, Integer.parseInt(stok.getText()));
			        prepare.setString(5, txtIdProd.getText());
			        
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Produk berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Produk gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            refresh();            
			        }
			}
			
		});
		btnUbah.setBounds(346, 429, 89, 23);
		getContentPane().add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\Actions-window-close-icon.png"));
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		            Connection konek = config.getCon();
		            String query = "DELETE FROM product WHERE id = ?";
		            PreparedStatement prepare = konek.prepareStatement(query);
		            
		            prepare.setString(1, txtIdProd.getText());
		            prepare.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Produk berhasil dihapus");
		            prepare.close();
		        }
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Produk gagal dihapus");
		            System.out.println(ex);
		        }
		        finally
		        {
		            tampilTabel();
		            refresh();
		        }
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String id = (String) tabelModel.getValueAt(a, 0);
		        txtIdProd.setText(id);
		        String nama = (String) tabelModel.getValueAt(a, 1);
		        txtNmProd.setText(nama);
		        String txtIdSupp = (String) tabelModel.getValueAt(a, 2);
		        txtIdSup.setText(txtIdSupp);
		        int harga = (int) tabelModel.getValueAt(a, 3);
		        txtHrg.setText(""+harga);
		        int stk = (int) tabelModel.getValueAt(a, 4);
		        stok.setText(""+stk);
		        txtIdProd.setEnabled(false);
		        
		       
			}
		});
		btnHapus.setBounds(445, 429, 103, 23);
		getContentPane().add(btnHapus);
		
		cbSup = new JComboBox();
		cbSup.setBounds(278, 304, 270, 20);
		getContentPane().add(cbSup);
		
		//tampil kode supplier dari nama supplier yang dipilih di combo box
		cbSup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nama=cbSup.getSelectedItem().toString();
		        try {
		            Connection konek = config.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select idSupp from suplier where nama='"+nama+"'");
		            while (rs.next()) {
		                txtIdSup.setText(rs.getString(1));
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }
			}
		});
		// end
		
		lbltxtIdProduk = new JLabel("ID Produk :");
		lbltxtIdProduk.setBounds(44, 275, 67, 14);
		getContentPane().add(lbltxtIdProduk);
		
		lblSuplier = new JLabel("ID Supplier :");
		lblSuplier.setBounds(44, 307, 67, 14);
		getContentPane().add(lblSuplier);
		
		lblNamaProduk = new JLabel("Nama Produk :");
		lblNamaProduk.setBounds(44, 338, 89, 14);
		getContentPane().add(lblNamaProduk);
		
		lblHarga = new JLabel("Harga :");
		lblHarga.setBounds(44, 369, 46, 14);
		getContentPane().add(lblHarga);
		
		//akses method isiSupplier
		isiSupplier();
		//model comboBox di set sesuai comboBoxModel pada method isiSupplier
		cbSup.setModel(model);
		
		lblStok = new JLabel("Stok :");
		lblStok.setBounds(44, 388, 46, 38);
		getContentPane().add(lblStok);
		
		stok = new JTextField();
		stok.setBounds(143, 397, 405, 20);
		getContentPane().add(stok);
		stok.setColumns(10);
		
		

	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = config.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM product";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	
	//method isiSupplier untuk tampilkan nama supplier ke comboBox. Semua nama supplier di masukan ke comboBoxmodel
	public void isiSupplier()
	{
		try {
            Connection konek = config.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select nama from suplier");
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
	}
}
