package com.soul.org.ponkala.db;

public interface DatabaseQuery {
	public static String INSERT_OR_UPDATE_VEHICLE_LOCATION = "INSERT INTO vehicle_location (vehicle_id, latitude, longitude) VALUES(?,?,?) ON DUPLICATE KEY UPDATE "    
																+"latitude = ?, longitude = ?;";

    public static String GET_VEHICLE_LOCATION = "SELECT * from vehicle_location where vehicle_id = ?";
    
    
    public static String GET_POOJA_TYPES = "select * from pooja_type";
    
    public static String INSERT_INTO_RECEIPT_BOOK = "insert into receipt_book(pooja_name, pooja_price, name, address, submission_date) values(?,?,?,?,now())";
    
    public static String SEARCH_BY_POOJA_TYPE = "select * from receipt_book where pooja_name like '%";
    
}
