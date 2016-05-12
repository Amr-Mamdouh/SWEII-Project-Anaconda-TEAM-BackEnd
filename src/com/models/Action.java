package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class Action {
	int userid,actionid;
	static int id=1;
	String describtion;

	private int getUserid() {
		return userid;
	}

	private void setUserid(int userid) {
		this.userid = userid;
	}

	private int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	private int getActionid() {
		return actionid;
	}

	private void setActionid(int actionid) {
		this.actionid = actionid;
	}

	private String getDescribtion() {
		return describtion;
	}

	private void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public static Action addNewAction(int userid ,int actionid,String describtion) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "insert into actions(userid,actionid,description) value (?,?,?)";
			// System.out.println(sql)

			PreparedStatement stmt=null;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, userid);
			stmt.setInt(2, actionid);
			stmt.setString(3, describtion);
			stmt.executeUpdate();
			id++;
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				Action action=new Action();
				action.id = rs.getInt(1);
				action.userid=userid;
				action.describtion=describtion;
			    action.actionid=actionid;
			
				return action;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static boolean undoAction(int actionid,int userid){
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "delete  from actions where userid=? and aid=?;" ;
			PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, userid);
				stmt.setInt(2, actionid);
				stmt.executeUpdate();
				return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public static ArrayList<String> GetActions(int userid){
		ArrayList<String> act = new ArrayList<>();
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql ="select * from actions where userid=?;" ;
			PreparedStatement stmt=null;
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, userid);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					String s="ID-{"+rs.getInt(1)+"   }-Action id   -  { "+rs.getInt(3)+" } -user id -{ "+rs.getInt(2)+" }- Action ->{ "+rs.getString(4)+"}";
					System.out.println(s);
			act.add(s);

				}
				return act;
									
		}catch(SQLException e){
			e.printStackTrace();
		}
		return act;
	}

}
