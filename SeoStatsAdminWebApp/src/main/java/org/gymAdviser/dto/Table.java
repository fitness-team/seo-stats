package org.gymAdviser.dto;

import java.util.ArrayList;

public class Table {
	private ArrayList<TableRow> rows = new ArrayList<TableRow>();
	public ArrayList<TableRow> getRows()
	{
		return rows;
	}
	public void setRows(ArrayList<TableRow> rows)
	{
		this.rows = rows;
	}
	public void addRow(TableRow obj)
	{
		rows.add(obj);
	}
	
	public TableRow getRow(int i)
	{
		if(i < 0 || i >= rows.size())
			return new TableRow();
		return rows.get(i);
	}
	public int size()
	{
		return rows.size();
	}
}