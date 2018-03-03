package com.soul.org.ponkala.db;

import java.sql.PreparedStatement;
import java.util.concurrent.Callable;

public class ReceiptBookUpdatorJob implements Callable<Integer>{

	private PreparedStatement ps;
	ReceiptBookUpdatorJob(PreparedStatement ps){
		this.ps = ps;
	}
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return ps.executeUpdate();
	}

}
