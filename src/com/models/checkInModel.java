package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.jdbc.Statement;

import jersey.repackaged.com.google.common.base.Objects.ToStringHelper;

public class checkInModel {
	private int userId , placeId , id ,  like_num ;
	private String discription ;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
		
	public static checkInModel checkIn(int userId ,int placeId , String discription){
		Connection conn = DBConnection.getActiveConnection();
		String sql = "insert into checkIn (user_id , place_id , description , like_num) values (?,?,?,?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, userId);
			stmt.setInt(2, placeId);
			stmt.setString(3, discription);
			stmt.setInt(4, 0);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				checkInModel checkIn = new checkInModel();
				checkIn.id = rs.getInt(1);
				checkIn.userId = userId;
				checkIn.placeId = placeId;
				checkIn.discription = discription;
				checkIn.like_num = 0;
				String s="3Make checkin"+checkIn.id;
				Action.addNewAction(checkIn.userId,checkIn.id,s);
				return checkIn;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
				
	}
	public static void like(int user_id , int checkIn_id ) throws SQLException{
	
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "insert into like_checkIn (userid , checkinid) values (?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, user_id);
			stmt.setInt(2, checkIn_id);
			stmt.executeUpdate();
			ResultSet rs=stmt.getGeneratedKeys();
			if(rs.next())
			{int id=rs.getInt(1);
				String s="4Make like  to checkin num "+checkIn_id;
				Action.addNewAction(user_id, id,s);
				s="Check in num "+checkIn_id+" Has Like";
				Notifications.addNewNotifications(user_id, checkIn_id, s);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
					
	}
				
	public static void comment(int userId ,int checkid, String comment){
		
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "insert into comment_checkin (userid,checkinid,comment) value(?,?,?);";
			PreparedStatement stmt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, userId);
			stmt.setInt(2, checkid);
			stmt.setString(3,comment);
			//stmt.setInt(4, 0);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				int id=rs.getInt(1); 
				String s="5Make Comment to checkin "+checkid;
				Action.addNewAction(userId,id,s);
				s="Check in num  " +checkid+  " has Comment";
				Notifications.addNewNotifications(userId, checkid, s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	public static ArrayList<checkInModel> checkIns(int user_id) throws SQLException, CloneNotSupportedException{
		ArrayList<checkInModel> checkIns = new ArrayList<>();
		checkInModel checkIn = new checkInModel();
		Connection conn = DBConnection.getActiveConnection();
		String sql = "select * from checkIn where user_id = ?;";
		PreparedStatement stmt = conn.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, user_id);
		
		ResultSet rs = stmt.executeQuery();//= stmt.getGeneratedKeys();
		while(rs.next()){
			checkIn.setDiscription(rs.getString("description"));
			checkIn.setId(rs.getInt("id"));
			checkIn.setLike_num(rs.getInt("like_num"));
			checkIn.setPlaceId(rs.getInt("place_id"));
			checkIn.setUserId(rs.getInt("user_id"));
			checkIns.add(checkIn);
			checkIn = new checkInModel();
		}
		return checkIns;
	}
	public static ArrayList<checkInModel> followers_checkIns(int user_id) throws SQLException, CloneNotSupportedException{
		Connection conn = DBConnection.getActiveConnection();
		String user_email = null;
		//getting the user's email.
		String sql = "select * from users where id = ?;";
		PreparedStatement stmt = conn.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, user_id);
		
		ResultSet rs = stmt.executeQuery() /*stmt.getGeneratedKeys()*/ , followersIdsRS;
		if(rs.next())
			user_email = rs.getString("email");
		sql = "select * from follow where followed = ?";
		stmt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, user_email);
		
		rs = stmt.executeQuery(); //stmt.getGeneratedKeys();
		ArrayList<Integer> followersIds = new ArrayList<>();
		ArrayList<checkInModel> follower_checkIns = new ArrayList<>();
		ArrayList<checkInModel> AllcheckIns = new ArrayList<>();
		//ResultSet followers;
		while(rs.next()){
			sql = "select * from users where email = ?;";
			stmt = conn.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, rs.getString("follower"));
			
			followersIdsRS = stmt.executeQuery(); //stmt.getGeneratedKeys();
			if(followersIdsRS.next())
				followersIds.add(followersIdsRS.getInt("id"));
		}
		for(int followerId : followersIds){
			follower_checkIns = checkIns(followerId);
			for(checkInModel checkIn : follower_checkIns){
				AllcheckIns.add(checkIn);
			}
		}
		return AllcheckIns;
	}
		
}

