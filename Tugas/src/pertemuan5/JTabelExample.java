package pertemuan5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTabelExample {
	public static void main(String[] args) {
		JFrame frame = new JFrame("JTable Example");
		
		//Membuat Tabel dengan data awal dan header kolom
		String[] columnNames = {"ID", "Name", "Age"};
		Object[][] data = {
				{1, "Jhon", 25},
				{2, "Anna", 30},
				{3, "Mike", 35},
		};
		
		//Membuat model tabel
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		
		//Membuat JTable dengan model
		JTable table = new JTable(model);
		
		//Membuat ScrollPane untuk menampung JTable
		JScrollPane scrollPane = new JScrollPane(table);
		
		//Menambahkan JScrollPane ke Frame
		frame.add(scrollPane);
		
		//Konfigurasi Frame
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}