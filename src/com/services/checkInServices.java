package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.checkInModel;
@Path("/checkInServices")
public class checkInServices {
	@POST
	@Path("/checkIn")
	@Produces(MediaType.TEXT_PLAIN)
	public String CheckIn(@FormParam("userId")int userId , @FormParam("placeId") int placeId ,
											@FormParam("description")String description){
		checkInModel checkIn = null;
		checkIn = checkInModel.checkIn(userId, placeId, description);
		JSONObject json = new JSONObject();
		json.put("checkIn_id", checkIn.getId());
		json.put("user_id", checkIn.getUserId());
		json.put("place_id", checkIn.getPlaceId());
		json.put("description", checkIn.getDiscription());
		return json.toJSONString();
	}
	
	@POST
	@Path("/like")
	@Produces(MediaType.TEXT_PLAIN)
	public void like(@FormParam("user_id") int user_id , @FormParam("checkIn_id")int checkIn_id)
			throws SQLException{
		checkInModel.like( user_id , checkIn_id);
	}
	
	@POST
	@Path("/comment")
	@Produces(MediaType.TEXT_PLAIN)
	public void comment(@FormParam("user_id") int user_id , @FormParam("checkIn_id")int checkIn_id , @FormParam("comment") String comment)
			throws SQLException{
		checkInModel.comment(user_id, checkIn_id, comment);
	}
	
	@POST
	@Path("/myCheckIns")
	@Produces(MediaType.TEXT_PLAIN)
	public String myCheckIns(@FormParam("user_id") int user_id)
			throws SQLException, CloneNotSupportedException{
		ArrayList<checkInModel> checkIns = checkInModel.checkIns(user_id);
		JSONObject json = new JSONObject();
		JSONArray return_json = new JSONArray();
		int counter = 1;
		for(checkInModel checkIn : checkIns){
			json.put("checkIn_id", checkIn.getId());
			json.put("user_id", checkIn.getUserId());
			json.put("description", checkIn.getDiscription());
			json.put("place_id", checkIn.getPlaceId());
			json.put("like_num", checkIn.getLike_num());
			return_json.add(json);
			json = new JSONObject();
		}
		return return_json.toJSONString();
		
	}

	@POST
	@Path("followersCheckIns")
	@Produces(MediaType.TEXT_PLAIN)
	public String followersCheckIns(@FormParam("user_Id")int user_id)
			throws SQLException, CloneNotSupportedException{
		ArrayList<checkInModel> checkIns = checkInModel.followers_checkIns(user_id);
		JSONArray jsons = new JSONArray();
		JSONObject json = new JSONObject();
		for(checkInModel checkIn : checkIns){
			json.put("checkIn_id", checkIn.getId());
			json.put("user_id", checkIn.getUserId());
			json.put("description", checkIn.getDiscription());
			json.put("place_id", checkIn.getPlaceId());
			json.put("", checkIn.getLike_num());
			jsons.add(json);
			json = new JSONObject();
		}
		return jsons.toJSONString();
	}
}
