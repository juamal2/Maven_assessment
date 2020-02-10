package com.qa.databases;


public interface Dao<T> {
	
	
	public void create(T t, Jdbc database);
	public void update(T t, Jdbc database);
	
	public void delete(T t, Jdbc database);
	
	public String read(Jdbc database, int id);
	public String read(Jdbc database);
	


}
