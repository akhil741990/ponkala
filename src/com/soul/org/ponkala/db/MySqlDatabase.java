package com.soul.org.ponkala.db;

import java.awt.datatransfer.StringSelection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Future;
import org.apache.commons.dbcp.BasicDataSource;
import com.soul.org.ponkala.entity.ReceiptBook;

public class MySqlDatabase implements Database{
	
	
	private static MySqlDatabase instance = new MySqlDatabase();
	private BasicDataSource dataSource;
	private DBJobExecutor dbJobExecutor;
	protected MySqlDatabase() {
		
	}
	// instance is initialized at at Class Load,
	// so no need to gaurd instance from the race of initialization from multiple threads 
	public static MySqlDatabase getInstance(){
		return instance;
	}
	
	public void init(){
		
		this.dataSource = new BasicDataSource();
		
		//TODO :  makes these values configurable via INI/ Properties file
		this.dataSource.setUrl("jdbc:mysql://localhost:3306/ponkala");
		this.dataSource.setUsername("root");
		this.dataSource.setPassword("password");
		this.dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		this.dataSource.setInitialSize(3);
		this.dataSource.setMaxActive(10);
		this.dbJobExecutor = new DBJobExecutor();
	}
	
	private Connection getConnection() throws Exception {
			return this.dataSource.getConnection();
	}

	public int insertIntoReceiptBook(ReceiptBook receiptBook){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = DatabaseOps.createPreparedStatement(con,DatabaseQuery.INSERT_INTO_RECEIPT_BOOK, 
					new Object[]{receiptBook.getPoojaType().toString(),receiptBook.getAmmount(),receiptBook.getName(), 
							receiptBook.getAddress()});
			ReceiptBookUpdatorJob dbJob = new ReceiptBookUpdatorJob(ps);
			Future<Integer> rowsAffected = this.dbJobExecutor.submit(dbJob);
			return rowsAffected.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally {
			DatabaseOps.closeConnection(con, ps);
		}
	}
	
	public String[][] getPoojaDetailsByPoojaType(String poojaType){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = DatabaseOps.createPreparedStatement(con,DatabaseQuery.SEARCH_BY_POOJA_TYPE + poojaType + "%'");
			ReceiptBookQueryJob dbJob = new ReceiptBookQueryJob(ps);
			Future<String[][]> result = this.dbJobExecutor.submit(dbJob);
			return result.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			DatabaseOps.closeConnection(con, ps);
		}
	}
	
	
	public void getVehicleLocation(int vehicleId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet vehicleLocation = null;
		try {
			con = getConnection();
			ps = DatabaseOps.createPreparedStatement(con,DatabaseQuery.GET_VEHICLE_LOCATION, new Object[]{vehicleId});
			vehicleLocation = ps.executeQuery();
			if(vehicleLocation.next()){
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DatabaseOps.closeConnection(con, ps,vehicleLocation);
		}
	}
}
