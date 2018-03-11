package com.soul.org.ponkala.entity;

public enum PoojaType {
	ANNADANAM,
	PONKALA,
	GANAPATHIHOMAM,
	NIRAPARA,
	KALASHAM,
	ARCHANA,
	DURAGA_POOJA;
	
	
	
	public static PoojaType getPoojaType(String poojaName) throws IllegalArgumentException{
		for(PoojaType type: values()){
			if(type.toString().equalsIgnoreCase(poojaName)){
				return type;
			}
		}
		throw new IllegalArgumentException("No PoojaType for name [" + poojaName + "]");
	}
}
