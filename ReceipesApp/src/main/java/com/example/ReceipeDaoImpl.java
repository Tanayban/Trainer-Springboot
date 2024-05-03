package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.bean.CountofReceipes;
import com.example.bean.Receipe;

@Repository
public class ReceipeDaoImpl implements ReceipeDaoInterface {
	
	@Autowired
	JdbcTemplate jt;
	
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	
	//insert Data
	@Override
	public String insertReceipe(int id, String foodname, String description, String ingredient, String preparation,
			String history, String category, String type, String imagename) {
		
		jt.update("INSERT INTO Receipe(id, foodname, description, ingredient, preparation, history, category, type, imagename) VALUES(?,?,?,?,?,?,?,?,?)",
				id, foodname, description, ingredient, preparation, history, category, type, imagename);
		
		return "Successful Added the Record With Id: ";
		
	}

	
	//GetAll Data with Images
	@Override
	public CountofReceipes getAllReceipes(int page, int size, String sortBy, String orderBy) {
		String countQuery = "SELECT COUNT(*) AS row_count FROM Receipe";
	    Long rowCount = jt.queryForObject(countQuery, Long.class);
		int startRow = page * size;
        int endRow = (page + 1) * size;
        
        //"SELECT * FROM (SELECT u.*, ROWNUM AS rnum FROM (SELECT id, foodname, description, ingredient, preparation, history, category, type, imagename FROM Receipe ORDER BY " + sortBy + " " + orderBy + ") AS u WHERE ROWNUM <= ?) AS d WHERE rnum > ?"
        
        String q = "SELECT * FROM (SELECT u.*, @rownum := @rownum + 1 AS row_num FROM (SELECT id, foodname, description, ingredient, preparation, history, category, type, imagename FROM Receipe ORDER BY " + sortBy + " " + orderBy + " " + ") u CROSS JOIN (SELECT @rownum := 0) r) AS d WHERE row_num <= ? AND row_num > ?";
		
		 List<Map<String, String>> receipeList = jt.query(
	                q,
	                (rs, rowNum) -> {
	                    Map<String, String> receipeDetail = new HashMap<>();
	                    receipeDetail.put("url", "https://receipe-springboot-api.up.railway.app/images/" + rs.getString("imagename"));
	                    receipeDetail.put("id", String.valueOf(rs.getInt("id")));
	                    receipeDetail.put("foodname", rs.getString("foodname"));
	                    receipeDetail.put("description", rs.getString("description"));
	                    receipeDetail.put("ingredient", rs.getString("ingredient"));
	                    receipeDetail.put("preparation", rs.getString("preparation"));
	                    receipeDetail.put("history", rs.getString("history"));
	                    receipeDetail.put("category", rs.getString("category"));
	                    receipeDetail.put("type", rs.getString("type"));
	                    return receipeDetail;
	                }, endRow, startRow
	            );
		 
		CountofReceipes receipe = new CountofReceipes();
		receipe.setCount(rowCount);
		receipe.setReceipes(receipeList);
		return receipe;
	}


	
	//Get All data with type
	@Override
	public CountofReceipes getAllReceipesByCategory(int page, int size, String sortBy, String orderBy,
			String category) {
		String countQuery = "SELECT COUNT(*) AS row_count FROM Receipe";
	    Long rowCount = jt.queryForObject(countQuery, Long.class);
		int startRow = page * size;
        int endRow = (page + 1) * size;
        
        //SELECT * FROM (SELECT u.*, ROWNUM AS rnum FROM (SELECT id, foodname, description, ingredient, preparation, history, category, type, imagename FROM Receipe WHERE category = ? ORDER BY " + sortBy + " " + orderBy + ") AS u WHERE ROWNUM <= ?) AS d WHERE rnum > ?
		
		 List<Map<String, String>> receipeList = jt.query(
	                "SELECT * FROM (SELECT u.*, @rownum := @rownum + 1 AS row_num FROM (SELECT id, foodname, description, ingredient, preparation, history, category, type, imagename FROM Receipe  WHERE category = ? ORDER BY " + sortBy + " " + orderBy + " " + ") u CROSS JOIN (SELECT @rownum := 0) r) AS d WHERE row_num <= ? AND row_num > ?",
	                (rs, rowNum) -> {
	                    Map<String, String> receipeDetail = new HashMap<>();
	                    receipeDetail.put("url", "http://localhost:8083/images/" + rs.getString("imagename"));
	                    receipeDetail.put("id", String.valueOf(rs.getInt("id")));
	                    receipeDetail.put("foodname", rs.getString("foodname"));
	                    receipeDetail.put("description", rs.getString("description"));
	                    receipeDetail.put("ingredient", rs.getString("ingredient"));
	                    receipeDetail.put("preparation", rs.getString("preparation"));
	                    receipeDetail.put("history", rs.getString("history"));
	                    receipeDetail.put("category", rs.getString("category"));
	                    receipeDetail.put("type", rs.getString("type"));
	                    return receipeDetail;
	                }, category, endRow, startRow
	            );
		 
		CountofReceipes receipe = new CountofReceipes();
		receipe.setCount(rowCount);
		receipe.setReceipes(receipeList);
		return receipe;
	}


	@Override
	public String UpdateReceipeById(int id, Receipe receipe) {
		String query = "UPDATE Receipe SET foodname = ?, description = ?, ingredient =?, preparation =?, history = ?, category = ?, type = ?, imagename = ? where id = ?";
		jt.update(query,  receipe.getFoodname(), receipe.getDescription(), receipe.getIngredient(), receipe.getPreparation(), receipe.getHistory(), receipe.getCategory(), receipe.getType(), receipe.getImagename(), receipe.getId());
		return "successfully Saved";
	}

}
