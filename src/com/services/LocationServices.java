package com.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.UserModel;

@Path("/locationServices")
public class LocationServices {
	@POST
	@Path("/updatePosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePosition(@FormParam("id") String id,
			@FormParam("lat") String lat, @FormParam("long") String lon) {
		Boolean status = UserModel.updateUserPosition(Integer.parseInt(id), Double.parseDouble(lat), Double.parseDouble(lon));
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}

    @POST
	@Path("/Location")
	@Produces(MediaType.TEXT_PLAIN)
	public String Location(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.GetLocation(email, pass);
		JSONObject json = new JSONObject();
		json.put("name", user.getName());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
}
