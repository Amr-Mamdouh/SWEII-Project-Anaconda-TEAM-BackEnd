package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.attribute.standard.Sides;
import javax.ws.rs.FormParam;

import com.mysql.jdbc.Statement;

public class PlaceModel {

	String name;
	String Description;
	double lat,lng;
	int id=0;
	static int sid=1;
	static Action A;
	
	public PlaceModel() {
		super();
		this.name = "";
		Description = "";
		this.lat = 0.0;
		this.lng = 0.0;
		this.id = 0;
		this.userid = 0;
	}
	int userid=0;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public static PlaceModel addNewPlace(int userid,String name, String description, double lat,double lng) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "insert into places (name,description,lat,`long`) VALUES  (?,?,?,?);";
			// System.out.println(sql);	
			PreparedStatement stmt=null;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.setString(2,  description);
			stmt.setDouble(3, lat);
			stmt.setDouble(4, lng);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				PlaceModel place = new PlaceModel();
				place.id = rs.getInt(1);
				place.name = name;
				place.Description = description;
				place.lat = lat;
				place.lng = lng;
				String s="1 Add new place"+place.id;
				Action.addNewAction(userid, place.id,s);	
				return place;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static PlaceModel SavePlace(int userid, int placeid ) {
		
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "insert into saveplaces (userid,placeid) value(?,?);";
			 System.out.println(sql);
			 PlaceModel place = new PlaceModel();
			PreparedStatement stmt=null;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,userid);
			stmt.setInt(2,placeid);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				
				place.sid = rs.getInt(1);
				System.out.println("saveplace");
				place.userid=userid;
				place.Description="notfound";
				place.lat=0.0;
				place.lng=0.0;
				place.name="notfound";
				String s="2Save Place "+place.sid;
				Action.addNewAction(userid, place.sid,s);
				return place;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<String> ShowPlaces(int id){
		ArrayList<String> places = new ArrayList<>();
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from places;" ;
			PreparedStatement stmt=null;
				stmt = conn.prepareStatement(sql);
				//stmt.setString(1, name);
				
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
				String s=rs.getString(2)+"   -      "+rs.getString(3)+"  -  "+rs.getInt(4)+" -"+rs.getInt(5);
					places.add(s);
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return places;
	}
	private static String getplace(int pid)
	{String s="";
		try{
			Connection  conn1 = DBConnection.getActiveConnection();
			String sql1 ="select * from places where id =?;" ;
			PreparedStatement stmt1=null;
			stmt1 = conn1.prepareStatement(sql1);
			stmt1.setInt(1, pid);
			
			ResultSet rs1 = stmt1.executeQuery();
			while(rs1.next()){
				
				 s=rs1.getString(2)+"   -      "+rs1.getString(3)+"  -  "+rs1.getInt(4)+" -"+rs1.getInt(5);

			return s;
			}
			
			
	}catch(SQLException e){
		e.printStackTrace();
	}
	return s;
	}
	public static ArrayList<String> ShowsavePlaces(int userid){
		ArrayList<String> saveplaces = new ArrayList<>();
		ArrayList<Integer> saveplacesid = new ArrayList<>();
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql ="select placeid from saveplaces where userid =?;" ;
			PreparedStatement stmt=null;
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, userid);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					
				int r=rs.getInt(1);
				saveplacesid.add(r);
				}
				for(int i=0;i<saveplacesid.size();i++)
				{
					String s=getplace(saveplacesid.get(i));
					saveplaces.add(s);
				}
									
		}catch(SQLException e){
			e.printStackTrace();
		}
		return saveplaces;
	}
	public static boolean deleteplace(int pid){
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "delete from places where id=?;" ;
			PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pid);
				stmt.executeUpdate();
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
				return true;
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public static boolean deletsaveeplace(int sid){
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "delete from saveplaces where id=?;" ;
			PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, sid);
				stmt.executeUpdate();
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
				return true;
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
