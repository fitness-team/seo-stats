package org.gymadviser.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.gymAdviser.dto.Table;
import org.gymAdviser.dto.TableRow;

public class TablesService extends CommonService {

	public void insertData(String database, String userId, String passward,
			String tableName, Map<String, String[]> params) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			Map<String, String> types = new HashMap<String, String>();
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL + database, userId,
					passward);

			stmt = conn.createStatement();

			Iterator<String> it = params.keySet().iterator();
			int i = 0;
			String key;

			ResultSet rsColumns = null;
			DatabaseMetaData meta = conn.getMetaData();
			rsColumns = meta.getColumns(null, null,
					tableName.substring(1, tableName.length() - 1), null);
			while (rsColumns.next()) {
				types.put(rsColumns.getString("COLUMN_NAME"),
						rsColumns.getString("TYPE_NAME"));
				System.out.println("key = "
						+ rsColumns.getString("COLUMN_NAME"));
			}
			String sqlInsert;

			sqlInsert = "insert into "
					+ tableName.substring(1, tableName.length() - 1) + " (";
			it = params.keySet().iterator();
			i = 0;
			while (it.hasNext()) {
				key = (String) it.next();
				++i;
				if (key.equals("submit")) {
					continue;
				}
				sqlInsert += key;
				if (i < params.size()) {
					sqlInsert += ", ";
				}
			}
			sqlInsert += ") VALUES(";
			it = params.keySet().iterator();
			i = 0;
			String value = null;
			while (it.hasNext()) {
				key = (String) it.next();
				++i;
				if (key.equals("submit")) {
					continue;
				}
				String[] values = ((String[]) params.get(key));
				value = values[0];
				System.out.println("key!!! = " + key);
				if (types.get(key).equals("varchar")) {

					value = "'" + value + "'";
				}
				sqlInsert += value;
				if (i < params.size()) {
					sqlInsert += ", ";
				}
			}
			sqlInsert += ");";
			System.out.println(sqlInsert);
			stmt.executeUpdate(sqlInsert);
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			ex.printStackTrace();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
	}

	public Table getTable(String database, String userId, String passward,
			String tableName) {
		Table table = new Table();
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL + database, userId,
					passward);

			stmt = conn.createStatement();
			String sqlRows;
			sqlRows = "select  * from "
					+ tableName.substring(1, tableName.length() - 1);

			ResultSet rsRows = stmt.executeQuery(sqlRows);
			ResultSetMetaData rsmd = rsRows.getMetaData();

			int columnsNumber = rsmd.getColumnCount();

			TableRow row = new TableRow();
			for (int i = 1; i <= columnsNumber; ++i) {
				row.addFild(rsmd.getColumnName(i));
			}
			table.addRow(row);

			while (rsRows.next()) {
				System.out.println(rsRows.getString(1));
				System.out.println(columnsNumber);
				TableRow row1 = new TableRow();
				for (int i = 1; i <= columnsNumber; ++i) {
					row1.addFild(rsRows.getString(i));
				}
				table.addRow(row1);
			}
			rsRows.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			ex.printStackTrace();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return table;
	}

	public ArrayList<String> getTables(String database, String userId,
			String passward) {

		ArrayList<String> tables = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL + database, userId,
					passward);
			stmt = conn.createStatement();
			String sql;
			sql = "select  table_schema, table_name from information_schema.tables";
			ResultSet rs = stmt.executeQuery(sql);
			/*
			 * DatabaseMetaData md = conn.getMetaData(); ResultSet rs =
			 * md.getTables(null, null, "%", null);
			 */
			while (rs.next()) {
				if (rs.getString("table_schema").equals("public")) {
					tables.add(rs.getString("table_name"));
				}
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			ex.printStackTrace();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return tables;
	}

	public TablesService() {

	}
}
