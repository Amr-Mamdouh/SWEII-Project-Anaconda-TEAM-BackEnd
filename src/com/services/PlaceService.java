package com.services;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.PlaceModel;
@Path("/PlaceService")
public class PlaceService {
	@POST
	@Path("/addnewplace")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("decription") String decription, @FormParam("lat") double lat,@FormParam("lng") double lng) {
		PlaceModel place = PlaceModel.addNewPlace(name, decription, lat, lng);
		JSONObject json = new JSONObject();
		json.put("id", place.getId());
		json.put("name", place.getName());
		json.put("decription", place.getDescription());
		json.put("lat",place.getLat());
		json.put("lng", place.getLng());
		return json.toJSONString();
	}
	@POST
	@Path("/saveplace")
	@Produces(MediaType.TEXT_PLAIN)
	public String SavePlace(@FormParam("userid") int userid,
			@FormParam("placeid") int  placeid) {
		PlaceModel place = PlaceModel.SavePlace(userid, placeid);
		if(place==null)
		{return "Can't save This Place";}
		JSONObject json = new JSONObject();
		json.put("placeid", place.getId());
		json.put("userid",place.getUserid());
//		json.put("DESCRIPTION","");
//		json.put("LAT",0.0);
//		json.put("LNG",0.0);
//		json.put("NAME","");
		System.out.println(json.toJSONString());
		return json.toJSONString();
	}
	@POST
	@Path("/showplcaes")
	@Produces(MediaType.TEXT_PLAIN)
	public String showplaces(@FormParam("userid") int  userid) throws SQLException {
		ArrayList<String> places = new ArrayList<>();
		JSONObject object = new JSONObject();
			places = PlaceModel.ShowPlaces(userid);
			for(int i = 0 ; i < places.size() ; i++){
				object.put("places #" +(i+1), places.get(i));
			}
			System.out.println(object.toJSONString());
		return object.toJSONString();
	}
	@POST
	@Path("/showsaveplcaes")
	@Produces(MediaType.TEXT_PLAIN)
	public String showsaveplaces(@FormParam("userid") int  userid) throws SQLException {
		ArrayList<String> places = new ArrayList<>();
		JSONObject object = new JSONObject();
			places = PlaceModel.ShowsavePlaces(userid);
			for(int i = 0 ; i < places.size() ; i++){
				object.put("places #" +(i+1), places.get(i));
			}
			System.out.println(object.toJSONString());
		return object.toJSONString();
	}

}
