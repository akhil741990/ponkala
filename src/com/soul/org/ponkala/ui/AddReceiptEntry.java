package com.soul.org.ponkala.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.soul.org.ponkala.db.MySqlDatabase;
import com.soul.org.ponkala.entity.PoojaType;
import com.soul.org.ponkala.entity.ReceiptBook;  
public class AddReceiptEntry {
	public static void main(String args[]){
		
		
		MySqlDatabase db = MySqlDatabase.getInstance();
		db.init();
		
		JFrame f=new JFrame();//creating instance of JFrame  
		
		JLabel lPoojaName = new JLabel("Pooja Name");
		lPoojaName.setBounds(30, 100, 100, 40);
		JTextArea tPoojaName = new JTextArea();
		tPoojaName.setBounds(130, 100, 100, 40);

		
		JLabel lPoojaPrice = new JLabel("Pooja Amount");
		lPoojaPrice.setBounds(30, 200, 100, 40);
		JTextArea tPoojaPrce = new JTextArea();
		tPoojaPrce.setBounds(130, 200, 100, 40);
		
		
		
		JLabel lName = new JLabel("Name");
		lName.setBounds(30, 300, 100, 40);
		JTextArea tName = new JTextArea();
		tName.setBounds(130, 300, 100, 40);
		
		JLabel lAddress = new JLabel("Address");
		lAddress.setBounds(30, 400, 100, 40);
		JTextArea tAddress = new JTextArea();
		tAddress.setBounds(130, 400, 100, 40);
		
		JButton b=new JButton("Submit Receipt");//creating instance of JButton  
		b.setBounds(130,500,200, 40);//x axis, y axis, width, height  
		
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ReceiptBook entry = new ReceiptBook();
				entry.setPoojaType(PoojaType.ANNADANAM);
				entry.setAmmount(Integer.parseInt(tPoojaPrce.getText()));
				entry.setName(tName.getText());
				entry.setAddress(tAddress.getText());
			
				db.insertIntoReceiptBook(entry);
			}
		});
		
		
		f.add(lPoojaName);
		f.add(lPoojaPrice);
		f.add(lName);
		f.add(lAddress);
		f.add(tPoojaName);
		f.add(tPoojaPrce);
		f.add(tName);
		f.add(tAddress);
		f.add(b);//adding button in JFrame  
		f.setSize(600,800);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	
		
	}
}
