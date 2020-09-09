package business.utilities;

public class SqlCommand {

	public static String getByUserName="select * from users where UserName = ?";
	public static String updateUser="update users set Password=? where UserName = ?";
	public static String deleteStock= "delete from stocks where StokCode = ?";
	public static String getByStock="select * from stocks where StokCode = ?";
	public static String getAllHours="SELECT *,CONVERT(DateOfCreation, TIME) as Saat from stocks";
	public static String getAllStocks="select * from stocks";
	public static String specialAllStocks="Select StokCode,StockName,Barcode,DateOfCreation from stocks";
	public static String insertStock="insert into stocks (StokCode,StockName,StockType,Unit,Barcode,VatType,Description,DateOfCreation) values(?,?,?,?,?,?,?,?)";
	public static String updatedStock="update stocks set StockName=?,StockType=?,Unit=?,Barcode=?,VatType=?,Description=?,DateOfCreation=? where StokCode = ?";
}
