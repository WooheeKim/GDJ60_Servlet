package com.woo.home.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.woo.home.util.DBConnection;

public class ProductDAO {
	
	public int getProductNum() throws Exception {
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCT_SEQ.NEXTVAL FROM DUAL";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		int num =  rs.getInt(1);
		
		DBConnection.disConnect(rs, st, connection);
		
		return num;
		
		
	}
	
	
	public List<ProductOptionDTO> getProductOptionList() throws Exception {
		List<ProductOptionDTO> ar = new ArrayList<ProductOptionDTO>();
		
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT * FROM PRODUCTOPTION";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductOptionDTO productOptionDTO = new ProductOptionDTO();
			productOptionDTO.setOptionNum(rs.getInt("OPTIONNUM"));
			productOptionDTO.setProductNum(rs.getInt("PRODUCTNUM"));
			productOptionDTO.setOptionName(rs.getString("OPTIONNAME"));
			productOptionDTO.setOptionPrice(rs.getInt("OPTIONPRICE"));
			productOptionDTO.setOptionStock(rs.getDouble("OPTIONSTOCK"));
			
		}
		DBConnection.disConnect(st, con);
		
		return ar;
		
	}
	public int setAddProductOption(ProductOptionDTO productOptionDTO) throws Exception {
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO PRODUCTOPTION (OPTIONNUM, PRODUCTNUM, OPTIONNAME, OPTIONPRICE, OPTIONSTOCK) "
				+ "VALUES (?, ?, ?, 0, 0)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, productOptionDTO.getOptionNum());
		st.setInt(2, productOptionDTO.getProductNum());
		st.setString(3, productOptionDTO.getOptionName());
		st.setInt(4, productOptionDTO.getOptionPrice());
		st.setDouble(5, productOptionDTO.getOptionStock());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnect(st, connection);
		
		return result;
	}
	
	
	public List<ProductDTO> getProductList() throws Exception{
		
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT PRODUCTNUM, PRODUCTNAME, PRODUCTDETAIL, PRODUCTJUMSU "
				+ "FROM PRODUCT ORDER BY PRODUCTJUMSU DESC";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductNum(rs.getInt("PRODUCTNUM"));
			productDTO.setProductName(rs.getString("PRODUCTNAME"));
			productDTO.setProductDetail(rs.getString("PRODUCTDETAIL"));
			productDTO.setProductJumsu(rs.getDouble("PRODUCTJUMSU"));
			ar.add(productDTO);
			
		}
		DBConnection.disConnect(st, connection);
		
		return ar;
	}
	
	
	public int setAddProduct(ProductDTO productDTO) throws Exception {
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO BANKBOOK (BANKBOOK_ACCOUNT, BANKBOOK_NAME, MEMBER_ID, BANKBOOK_DATE, BANKBOOK_BALANCE) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setInt(1, productDTO.getProductNum());
		st.setString(2, productDTO.getProductName());
		st.setString(3, productDTO.getProductDetail());
		st.setDouble(4, productDTO.getProductJumsu());
		
		int result = st.executeUpdate();
		
		DBConnection.disConnect(st, connection);
		
		return result;
		
	}
	
	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		try {
			List<ProductDTO> ar = productDAO.getProductList();
			
			System.out.println(ar.size() != 0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
