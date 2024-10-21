package pertemuan5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableSortExample {
	public static void main(String[] args) {
		JFrame frame = new JFrame("JTable Sorting Example");
		
		String[] columnNames = {"ID", "Name", "Age"};
		Object[][] data = {
				{1, "Jhon", 25},
				{2, "Mike", 35},
				{3, "Anna", 30},
				{4, "Ani", 20},
				{5, "Andi", 22},
		};
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(model);
		
		//Mengaktifkan pengurutan otomatis
		table.setAutoCreateRowSorter(true);
		
		JScrollPane scollPane = new JScrollPane(table);
		frame.add(scollPane);
		
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
