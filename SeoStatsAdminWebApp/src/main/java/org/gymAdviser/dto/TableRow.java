package org.gymAdviser.dto;

import java.util.ArrayList;

public class TableRow {
	private ArrayList<Object> row = new ArrayList<Object>();

	public ArrayList<Object> getRow() {
		return row;
	}

	public void setRow(ArrayList<Object> row) {
		this.row = row;
	}
	
	public void addFild(Object obj)
	{
		row.add(obj);
	}
	
	public Object getFild(int i)
	{
		if(i < 0 || i >= row.size())
			return new Object();
		return row.get(i);
	}
	public int size()
	{ 
		return row.size();
	}
}
