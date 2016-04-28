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
import org.json.simple.JSONObject;

import com.models.DBConnection;
import com.models.PlaceModel;
import com.models.UserModel;

@Path("/startingServices")
public class Services {

	/*
	 * @GET
	 * 
	 * @Path("/signup")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response signUp(){ return
	 * Response.ok(new Viewable("/Signup.jsp")).build(); }
	 */

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		UserModel user = UserModel.addNewUser(name, email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.login(email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
	


	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
	

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
		JSONObject json = new JSONObject();
		json.put("placeid", place.getId());
		json.put("userid",place.getUserid());
//		json.put("DESCRIPTION","");
//		json.put("LAT",0.0);
//		json.put("LNG",0.0);
//		json.put("NAME","");
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
}
