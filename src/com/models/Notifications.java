package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class Notifications {
	int nid,actionid,userid;
	String description;
	
	public Notifications() {
		
		this.nid = 0;
		this.actionid = 0;
		this.userid = 0;
		this.description =" ";
	}
	public  int getNid() {
		return nid;
	}
	public  void setNid(int nid) {
		this.nid = nid;
	}
	public int getActionid() {
		return actionid;
	}
	public void setActionid(int actionid) {
		this.actionid = actionid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static Notifications addNewNotifications(int userid,int checkinid,String description) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "insert into notification (userid,checkinid,description) value (?,?,?)";
			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, userid);
			stmt.setInt(2, checkinid);
			stmt.setString(3, description);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
			Notifications n=new Notifications();
				n.nid = rs.getInt(1);
				n.actionid=checkinid;
				n.description=description;
				n.userid=userid;
				return n;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<String> GetNotifications(int userid){
		ArrayList<String> notifi = new ArrayList<>();
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql ="select * from notification where userid=?;" ;
			PreparedStatement stmt=null;
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, userid);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					String s="nID-{"+rs.getInt(1)+"   }-UserId   -  { "+rs.getInt(2)+" } -Checkin id -{ "+rs.getInt(3)+" }- notification ->{ "+rs.getString(4)+"}";

			notifi.add(s);

				}
									
		}catch(SQLException e){
			e.printStackTrace();
		}
		return notifi;
	}


}
