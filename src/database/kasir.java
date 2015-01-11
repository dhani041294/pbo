package database;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class kasir extends JInternalFrame {
	private JTextField txtNama;
	private JLabel lblNewLabel;
	private JLabel lblIdTransaksi;
	private JTextField txtIdProd;
	private JButton btnCari;
	private JLabel lblNamaProduct;
	private JTextField txtNmProd;
	private JLabel lblHarga;
	private JTextField txtHarga;
	private JLabel lblJumlah;
	private JLabel lblTotal;
	private JLabel lblTotal_1;
	private JLabel lblJumlahBarang;
	private JLabel lblJumlah_1;
	private JLabel lblBayar;
	private JTextField txtBayar;
	private JLabel lblRp;
	private JButton btnHitung;
	private JTable table;
	private DefaultTableModel tabelModel;
	private JLabel lblId;
	private JLabel lblNamaPembeli;
	private JButton btnMasuk;
	private JLabel lblIdProduct;
	private JSpinner spinner;
	private JButton btnTambah;
	private static String dtrans;
	private static int jumlahBeli=0;
	/**
	 * Create the frame.
	 */
	public void disable()
	{
		lblNamaPembeli.setEnabled(false);
		txtNama.setEnabled(false);
		btnMasuk.setEnabled(false);
	}
	public void enabled()
	{
		table.setEnabled(true);
		lblIdProduct.setEnabled(true);
		txtIdProd.setEnabled(true);
		btnCari.setEnabled(true);
		lblNamaProduct.setEnabled(true);
		lblHarga.setEnabled(true);
		lblJumlah.setEnabled(true);
		spinner.setEnabled(true);
		btnTambah.setEnabled(true);
		lblJumlahBarang.setEnabled(true);
		lblTotal.setEnabled(true);
		lblBayar.setEnabled(true);
		txtBayar.setEnabled(true);
		btnHitung.setEnabled(true);
	}
	public void enabled2()
	{
		lblNamaPembeli.setEnabled(true);
		txtNama.setEnabled(true);
		btnMasuk.setEnabled(true);
	}
	public void disable2()
	{
		table.setEnabled(false);
		lblIdProduct.setEnabled(false);
		txtIdProd.setEnabled(false);
		btnCari.setEnabled(false);
		lblNamaProduct.setEnabled(false);
		lblHarga.setEnabled(false);
		lblJumlah.setEnabled(false);
		spinner.setEnabled(false);
		btnTambah.setEnabled(false);
		lblJumlahBarang.setEnabled(false);
		lblTotal.setEnabled(false);
		lblBayar.setEnabled(false);
		txtBayar.setEnabled(false);
		btnHitung.setEnabled(false);
		txtIdProd.setText("");
		txtBayar.setText("");
		txtNmProd.setText("");
    	txtHarga.setText("");
	}
	public void setTanggal()
    {
        java.util.Date skrng = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        lblNewLabel.setText(kal.format(skrng));
    }
	public void autoNumber()
	{
		try
		{
			Connection konek = config.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM transaksi ";
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				lblId.setText("T001");
			}
			else{	
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					lblId.setText("T00"+IDPesan);

			}
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	public void autoDtrans()
	{
		try
		{
			Connection konek = config.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM dtransaksi ";
			
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				dtrans="dt001";
			}
				else
				{
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					String IDFix = "00" + IDPesan;
					dtrans="dt" + IDFix;
				}
					
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	
	public void tampilTotal()
    {
        String kpesan=lblId.getText();
         try {
            Connection konek = config.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select SUM(subtotal) from dtransaksi where idtrans='"+kpesan+"'");
            while (rs.next()) {
                lblTotal_1.setText(""+rs.getInt(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
	public kasir() {
		autoDtrans();
		setBounds(100, 100, 1366, 490);
		getContentPane().setLayout(null);
		lblNamaPembeli = new JLabel("Nama Pembeli :");
		lblNamaPembeli.setBounds(10, 11, 90, 14);
		getContentPane().add(lblNamaPembeli);
		
		txtNama = new JTextField();
		txtNama.setBounds(121, 8, 170, 20);
		getContentPane().add(txtNama);
		txtNama.setColumns(10);
		
		btnMasuk = new JButton("Beli");
		btnMasuk.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\shop-cart-icon.png"));
		btnMasuk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = config.getCon();
		        String query = "INSERT INTO transaksi VALUES(?,?,?,?,0)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, lblId.getText());
		        prepare.setString(2, lblNewLabel.getText());
		        prepare.setString(3, login.nama);
		        prepare.setString(4, txtNama.getText());


		        prepare.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
		        prepare.close();
		        tampilTabel();
		        disable();
		        enabled();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
			}
		});
		btnMasuk.setBounds(301, 7, 100, 23);
		getContentPane().add(btnMasuk);
		
		lblNewLabel = new JLabel("-");
		lblNewLabel.setBounds(1229, 419, 83, 23);
		getContentPane().add(lblNewLabel);
		setTanggal();
		
		lblIdTransaksi = new JLabel("ID transaksi :");
		lblIdTransaksi.setBounds(1143, 11, 100, 14);
		getContentPane().add(lblIdTransaksi);
		
		Panel panel = new Panel();
		panel.setBounds(10, 43, 1320, 370);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblIdProduct = new JLabel("ID Product :");
		lblIdProduct.setEnabled(false);
		lblIdProduct.setBounds(82, 215, 69, 20);
		panel.add(lblIdProduct);
		
		txtIdProd = new JTextField();
		txtIdProd.setEnabled(false);
		txtIdProd.setBounds(204, 215, 236, 20);
		panel.add(txtIdProd);
		txtIdProd.setColumns(10);
		
		btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String id=txtIdProd.getText();
		        try {
		            Connection konek = config.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select nama,harga from product where id='"+id+"'");
		            while (rs.next()) {
		            	txtNmProd.setText(rs.getString(1));
		            	txtHarga.setText(rs.getString(2));
		            }
		        } catch (Exception e1) {
		            JOptionPane.showMessageDialog(null, "Product yang anda masukkan Tidak Tersedia");
		        }
			}
		});
		btnCari.setEnabled(false);
		btnCari.setBounds(450, 214, 89, 23);
		panel.add(btnCari);
		
		lblNamaProduct = new JLabel("Nama Product");
		lblNamaProduct.setEnabled(false);
		lblNamaProduct.setBounds(82, 241, 95, 14);
		panel.add(lblNamaProduct);
		
		txtNmProd = new JTextField();
		txtNmProd.setEnabled(false);
		txtNmProd.setBounds(204, 238, 397, 20);
		panel.add(txtNmProd);
		txtNmProd.setColumns(10);
		
		lblHarga = new JLabel("Harga :");
		lblHarga.setEnabled(false);
		lblHarga.setBounds(82, 263, 46, 14);
		panel.add(lblHarga);
		
		txtHarga = new JTextField();
		txtHarga.setEnabled(false);
		txtHarga.setBounds(204, 260, 397, 20);
		panel.add(txtHarga);
		txtHarga.setColumns(10);
		
		lblJumlah = new JLabel("Jumlah");
		lblJumlah.setEnabled(false);
		lblJumlah.setBounds(82, 291, 46, 14);
		panel.add(lblJumlah);
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setBounds(204, 288, 46, 20);
		panel.add(spinner);
		
		btnTambah = new JButton("Tambah");
		btnTambah.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\Save-icon.png"));
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = config.getCon();
		        String query = "INSERT INTO dtransaksi VALUES(?,?,?,?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, dtrans);
		        prepare.setString(2, lblId.getText());
		        prepare.setString(3, txtIdProd.getText());
		        prepare.setInt(4, (Integer)spinner.getValue());
		        prepare.setInt(5,  Integer.valueOf(txtHarga.getText())*(Integer) spinner.getValue());

		        prepare.executeUpdate();
		        jumlahBeli+=1;
		        lblJumlah_1.setText(""+jumlahBeli);
		        JOptionPane.showMessageDialog(null, "Data pembelian disimpan");
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data pembelian gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
		        
		        //tampil total
				autoDtrans();
		        tampilTotal();
		         
		         //update total database
		          try{
		        Connection konek = config.getCon();
		        String query = "UPDATE transaksi SET total = ? WHERE idtrans = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, Integer.parseInt(lblTotal_1.getText()));
		        prepare.setString(2, lblId.getText());
		        
		        prepare.executeUpdate();
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal diubah");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
		          
		          
		        //kurang stok  
		        try{
		        Connection konek = config.getCon();
		        String query = "UPDATE product SET stok = stok-? WHERE id = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, (Integer) spinner.getValue());
		        prepare.setString(2, (String) txtIdProd.getText());
		        
		        prepare.executeUpdate();
		        tampilTabel();
	            table.setModel(tabelModel);	
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Stok gagal dikurangi");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
			}
		});
		btnTambah.setEnabled(false);
		btnTambah.setBounds(382, 324, 108, 23);
		panel.add(btnTambah);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1300, 193);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Product");
        tabelModel.addColumn("Nama Product");
        tabelModel.addColumn("Harga");
        tabelModel.addColumn("Jumlah");
        tabelModel.addColumn("Total Harga");
		table.setModel(tabelModel);	
        tampilTabel();
        table.setEnabled(false);
		
		lblTotal = new JLabel("Total :");
		lblTotal.setEnabled(false);
		lblTotal.setBounds(1068, 241, 62, 14);
		panel.add(lblTotal);
		
		lblTotal_1 = new JLabel("-");
		lblTotal_1.setBounds(1183, 241, 46, 14);
		panel.add(lblTotal_1);
		
		lblJumlahBarang = new JLabel("Jumlah Barang :");
		lblJumlahBarang.setEnabled(false);
		lblJumlahBarang.setBounds(1068, 218, 95, 14);
		panel.add(lblJumlahBarang);
		
		lblJumlah_1 = new JLabel("-");
		lblJumlah_1.setBounds(1183, 218, 46, 14);
		panel.add(lblJumlah_1);
		
		lblBayar = new JLabel("Bayar :");
		lblBayar.setEnabled(false);
		lblBayar.setBounds(1068, 266, 62, 14);
		panel.add(lblBayar);
		
		txtBayar = new JTextField();
		txtBayar.setEnabled(false);
		txtBayar.setBounds(1164, 263, 108, 20);
		panel.add(txtBayar);
		txtBayar.setColumns(10);
		
		lblRp = new JLabel("Rp.");
		lblRp.setBounds(1140, 266, 23, 14);
		panel.add(lblRp);
		
		btnHitung = new JButton("Hitung");
		btnHitung.setIcon(new ImageIcon("C:\\Users\\dhani\\workspace\\database\\src\\icon\\shopping-icon.png"));
		btnHitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kembalian =Integer.parseInt(txtBayar.getText())-Integer.parseInt(lblTotal_1.getText());
	            JOptionPane.showMessageDialog(null, "Kembalian: Rp."+kembalian);
				autoNumber();
				enabled2();
				disable2();
				txtNama.setText("");
				tampilTabel();
				jumlahBeli=0;
			}
		});
		btnHitung.setEnabled(false);
		btnHitung.setBounds(1151, 324, 108, 23);
		panel.add(btnHitung);
		
		lblId = new JLabel("-");
		lblId.setBounds(1266, 11, 46, 14);
		getContentPane().add(lblId);
		
		autoNumber();
	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = config.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT dt.idbarang,p.nama,p.harga,dt.qty,dt.subtotal FROM transaksi t join dtransaksi dt on t.idtrans=dt.idtrans join product p on dt.idbarang=p.id where dt.idtrans='"+lblId.getText()+"'";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
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
}
