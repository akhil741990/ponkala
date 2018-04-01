package com.soul.org.ponkala.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import com.soul.org.ponkala.db.MySqlDatabase;
import com.soul.org.ponkala.entity.PoojaType;
import com.soul.org.ponkala.entity.ReceiptBook;  
public class AddReceiptEntry {
	
	public static void main(String args[]){
			
		MySqlDatabase db = MySqlDatabase.getInstance();
		db.init();
		
		JFrame f=new JFrame();//creating instance of JFrame  
		
		try{
			BufferedImage myImage = ImageIO.read(new File("C:\\Users\\akhil\\NLP\\ponkala\\resource\\ponkala-background-image.PNG"));
			f.setContentPane(new ImagePanel(myImage));
		}catch (Exception e) {
			System.out.println("Failed to load background image :"+ e.getMessage());
		}
		
		
		String poojaType[]={"Annadanam", "Ponkala", "GanapathiHomam", "Nirapara", "Kalasham", "Archana", "DurgaPooja"};        
		
		
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

		
		JLabel lPoojaName = new JLabel("Pooja Name");
		lPoojaName.setBounds(30, 100, 100, 40);
		JComboBox cbPoojaName = new JComboBox(poojaType);   
	    cbPoojaName.setBounds(130, 100, 100, 40);
		
		JLabel lPoojaPrice = new JLabel("Pooja Amount");
		lPoojaPrice.setBounds(30, 200, 100, 40);
		JTextArea tPoojaPrce = new JTextArea();
		tPoojaPrce.setBounds(130, 200, 100, 40);
		tPoojaPrce.setBorder(border);
		
		JLabel lName = new JLabel("Name");
		lName.setBounds(30, 300, 100, 40);
		JTextArea tName = new JTextArea();
		tName.setBounds(130, 300, 100, 40);
		tName.setBorder(border);
		
		JLabel lAddress = new JLabel("Address");
		lAddress.setBounds(30, 400, 100, 40);
		JTextArea tAddress = new JTextArea();
		tAddress.setBounds(130, 400, 100, 40);
		tAddress.setBorder(border);
		
		JButton b=new JButton("Submit Receipt");//creating instance of JButton  
		b.setBounds(130,500,200, 40);//x axis, y axis, width, height  
		
		
		JButton reportButton = new JButton("View Receipt Report");//creating instance of JButton  
		reportButton.setBounds(280, 100, 200, 40);//x axis, y axis, width, height
		
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ReceiptBook entry = new ReceiptBook();
				cbPoojaName.getSelectedItem().toString();
				entry.setPoojaType(PoojaType.getPoojaType(cbPoojaName.getSelectedItem().toString()));
				entry.setAmmount(Integer.parseInt(tPoojaPrce.getText()));
				entry.setName(tName.getText());
				entry.setAddress(tAddress.getText());
				db.insertIntoReceiptBook(entry);
				JOptionPane.showMessageDialog(null, "Receipt Saved Successfully");
				clearAllFields();
			}
			
			private void clearAllFields(){
				tPoojaPrce.setText("");
				tName.setText("");
				tAddress.setText("");
			}
		});
		
		
		
		reportButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				JFrame innerFrame = new JFrame();
				
				String column[]={"ID","POOJA NAME","POOJA PRICE", "NAME", "ADDRESS", "DATE"};    
				String [][] report = db.getPoojaDetailsByPoojaType(cbPoojaName.getSelectedItem().toString());
			    JTable jt = new JTable(report,column);    
			    jt.setBounds(100,200,10,20);          
			    JScrollPane sp=new JScrollPane(jt);    
			    innerFrame.add(sp);
			    innerFrame.setSize(500,600);    
			    innerFrame.setVisible(true);
			    JLabel lTotal = new JLabel("Total");
				lTotal.setBounds(100, 800, 100, 40);
				int cost = 0;
				for(String [] innerArray : report){
					cost = cost + Integer.parseInt(innerArray[2]);
				}
				JLabel total = new JLabel("" + cost);
				total.setBounds(200, 800, 100, 40);
				innerFrame.add(lTotal);
				innerFrame.add(total);
				
			}
		});
		
		
		f.add(lPoojaName);
		f.add(lPoojaPrice);
		f.add(lName);
		f.add(lAddress);
		f.add(cbPoojaName);
		f.add(tPoojaPrce);
		f.add(tName);
		f.add(tAddress);
		f.add(b);//adding button in JFrame
		f.add(reportButton);
		f.setSize(1000,1500);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	
		
	}
	
}
