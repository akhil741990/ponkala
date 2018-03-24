package com.soul.org.ponkala.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ReceiptBookQueryJob implements Callable<String[][]>{

	private PreparedStatement ps;
	ReceiptBookQueryJob(PreparedStatement ps){
		this.ps = ps;
	}
	@Override
	public String[][] call() throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = ps.executeQuery();
		List<String[]> records = new ArrayList<>();
		
		while(rs.next()){
			String row [] = new String[6];
			row[0] =   rs.getInt(1) + "";
			row[1] =   rs.getString(2);
			row[2] =   rs.getInt(3) + "";
			row[3] =   rs.getString(4);
			row[4] =   rs.getString(5);
			row[5] =   rs.getDate(6).toString();
			records.add(row);
		}
		String retVal[][] = new String[records.size()][6];
		records.toArray(retVal);
		return retVal;
	}

}
