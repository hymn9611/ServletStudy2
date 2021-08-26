package com.iu.s1.bankbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.iu.s1.util.DBConnector;

public class BankBookDAO {
	
	private DBConnector dbConnector;
	
	public BankBookDAO() {
		dbConnector = new DBConnector();
	}
	
	//setInsert : int로 성공여부를 판단가능
	public int setInsert(BankBookDTO bankBookDTO) {
		Connection con = dbConnector.getConnect();
		PreparedStatement st = null;
		int result = 0;
		
		String sql = "INSERT INTO bankbook (bookNumber, bookName, bookRate, bookSale) "
				+ "values (bankbook_seq.nextval, ?, ?, ?)";
		
		try {
			st = con.prepareStatement(sql);
			st.setString(1, bankBookDTO.getBookName());
			st.setDouble(2, bankBookDTO.getBookRate());
			st.setInt(3, bankBookDTO.getBookSale());
			//INSERT INTO니까 executeQuery가 아니라 executeUpdate
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			dbConnector.disConnect(st, con);
		}
		
		return result;
		
	}
	
	//getList : bankbook의 모든 자료를 출력하는 메서드
	public ArrayList<BankBookDTO> getList() {
		Connection con = dbConnector.getConnect();
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<BankBookDTO> ar = new ArrayList<BankBookDTO>();
		
		String sql = "SELECT * FROM bankbook";
		
		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			//rs에는 bankbook의 자료가 들어있고 이것을 bankBookDTO에 넣고, ar로 배열
			while(rs.next()) {
				BankBookDTO bankBookDTO = new BankBookDTO();
				bankBookDTO.setBookNumber(rs.getLong("bookNumber"));
				bankBookDTO.setBookName(rs.getString("bookName"));
				bankBookDTO.setBookRate(rs.getDouble("bookRate"));
				bankBookDTO.setBookSale(rs.getInt("bookSale"));
				ar.add(bankBookDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			dbConnector.disConnect(rs, st, con);
		}
		
		return ar;
		
	}//getList()메서드 종료
	
	//getSelect()메서드 : 지정받은 booknumber에 해당하는 정보를 모두 출력 
	public BankBookDTO getSelect(BankBookDTO bankBookDTO) {
		
		Connection con = dbConnector.getConnect();
		PreparedStatement st = null;
		ResultSet rs = null;
		BankBookDTO result = null;
		
		String sql = "SELECT * FROM bankbook WHERE booknumber=?";
		
		try {
			st = con.prepareStatement(sql);
			st.setLong(1, bankBookDTO.getBookNumber());
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				result = new BankBookDTO();
				result.setBookNumber(rs.getLong("bookNumber"));
				result.setBookName(rs.getString("bookName"));
				result.setBookRate(rs.getDouble("bookRate"));
				result.setBookSale(rs.getInt("bookSale"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
				
		} finally {
			dbConnector.disConnect(rs, st, con);
		}
		
		return result;
		
	}//getSelect()메서드 종료

}
