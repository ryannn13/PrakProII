package pertemuan5;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListExample {
	public static void main(String[] args) {
		JFrame frame = new JFrame("JList Example");
		
		//Data untuk JList
		String [] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
		
		//Membuat JList dengan data
		JList<String> list = new JList<>(items);
		
		//Mengatur mode seleksi (dalam hal ini SINGLE_SELECTION)
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Menambahan listener untuk menangani seleksi item
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectionItem = list.getSelectedValue();
					System.out.println("Selected: " + selectionItem);
				}
			}
		});
		
		//Menambahkan JScrollPane untuk JList jika item terlalu banyak
		JScrollPane scrollPane = new JScrollPane(list);
		
		//Mengatur dan Menambahkan komponen ke JFrame
		frame.setLayout(null);
		scrollPane.setBounds(50, 50, 150, 100);
		frame.add(scrollPane);
		
		//Konfigurasi Frame
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
