package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import business.Validator;
import business.utilities.DateTime;
import business.utilities.Messages;
import business.utilities.SqlCommand;
import controllers.StockListController;
import dataaccess.DbUtil;

public class StockModel {

	private String stokCode;
	private String stockName;
	private int stockType;
	private String unit;
	private String barcode;
	private double vatType;
	private String description;
	private Date dateOfCreation;

	public String getStokCode() {
		return stokCode;
	}

	public void setStokCode(String stokCode) {
		this.stokCode = stokCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getStockType() {
		return stockType;
	}

	public void setStockType(int stockType) {
		this.stockType = stockType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public double getVatType() {
		return vatType;
	}

	public void setVatType(double vatType) {
		this.vatType = vatType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateOfCreation() {

		return dateOfCreation;
	}

	public String getDateOfCreationFor() {

		return dateOfCreation.toString();
	}

	public void setDateOfCreation(Date value) {
		dateOfCreation = value;
	}

	public String stockModel() {
		return getStokCode().toString() + " " + getStockName().toString() + " " + String.valueOf(getStockType()) + " "
				+ getUnit().toString() + " " + getBarcode().toString() + " " + String.valueOf(getVatType()) + " "
				+ getDescription().toString() + " " + getDateOfCreation().toString();
	}

	public StockModel(String stokCode, String stockName, int stockType, String unit, String barcode, double vatType,
			String description) {
		this.stokCode = stokCode;
		this.stockName = stockName;
		this.stockType = stockType;
		this.unit = unit;
		this.barcode = barcode;
		this.vatType = vatType;
		this.description = description;
	}

	public StockModel(String stokCode, String stockName, int stockType, String unit, String barcode, double vatType,
			String description, Date date) {
		this.stokCode = stokCode;
		this.stockName = stockName;
		this.stockType = stockType;
		this.unit = unit;
		this.barcode = barcode;
		this.vatType = vatType;
		this.description = description;
		this.dateOfCreation = date;
	}

	public StockModel(String stockCode) {
		this.stokCode=stockCode;
	}
	public StockModel() {
	}

	private Connection connection = DbUtil.connection;
	private ArrayList<StockRowModel> stocks;
	private Statement stmt;
	private ResultSet rs;

	public void insertStock()  {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(SqlCommand.insertStock);
			pstmt.setString(1, getStokCode());
			pstmt.setString(2, getStockName());
			pstmt.setInt(3, getStockType());
			pstmt.setString(4, getUnit());
			pstmt.setString(5, getBarcode());
			pstmt.setDouble(6, getVatType());
			pstmt.setString(7, getDescription());
			String date = DateTime.dateNow();
			pstmt.setString(8, date);
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			DbUtil.showErrorMessage(exception);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex) {
			}
		}
	}

	public void updateStock() {
		PreparedStatement pstmt = null;
		try {
			String sql = SqlCommand.updatedStock;
			pstmt = connection.prepareStatement(sql);
			String date = DateTime.dateNow();
			pstmt.setString(1, getStockName());
			pstmt.setInt(2, getStockType());
			pstmt.setString(3, getUnit());
			pstmt.setString(4, getBarcode());
			pstmt.setDouble(5, getVatType());
			pstmt.setString(6, getDescription());
			pstmt.setString(7, date);
			pstmt.setString(8, getStokCode());
			pstmt.executeUpdate();
		} catch (SQLException exception) {
			DbUtil.showErrorMessage(exception);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex) {
			}
		}
	}

	public void deleteStock() {
		PreparedStatement pstmt = null;
		try {
			String sql = SqlCommand.deleteStock;
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, getStokCode());
			pstmt.executeUpdate();
		} catch (SQLException exception) {

			DbUtil.showErrorMessage(exception);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex) {
				DbUtil.showErrorMessage(ex);
			}
		}
	}
	public ArrayList<StockRowModel> getAllStock() {
		stocks= new ArrayList<StockRowModel>();
		try {
			stmt = this.connection.createStatement();
			rs = stmt.executeQuery(SqlCommand.specialAllStocks);
			while (rs.next()) {
				stocks.add(new StockRowModel(rs.getString("StokCode"), rs.getString("StockName"),
						 rs.getString("Barcode"), rs.getDate("DateOfCreation")));
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return stocks;
	}
	public ArrayList<String> getAllHour() {
		ArrayList<String> hours = new ArrayList<String>();
		try {
			stmt = connection.createStatement();
			String sql = SqlCommand.getAllHours;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				hours.add(rs.getTime("Saat").toString());
			}
		} catch (SQLException exception) {
			DbUtil.showErrorMessage(exception);
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hours;

	}

	public StockModel searchStock(String code) {
		StockModel stock = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(SqlCommand.getByStock);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				stock = new StockModel(rs.getString("StokCode"), rs.getString("StockName"), rs.getInt("StockType"),
						rs.getString("Unit"), rs.getString("Barcode"), rs.getDouble("VatType"),
						rs.getString("Description"), rs.getDate("DateOfCreation"));
			}
		} catch (SQLException exception) {
			DbUtil.showErrorMessage(exception);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return stock;

	}
}
