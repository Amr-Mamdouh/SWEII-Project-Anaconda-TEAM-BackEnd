package com.services;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.UserModel;

@Path("/followingServices")
public class followServices {
	@POST
	@Path("/unfollow")
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean unfollow(@FormParam("Follower_email") String follower_email , @FormParam("pass") String pass ,
			@FormParam("Followed_email") String followed_email){
				return UserModel.unfollow(follower_email, pass, followed_email);
	}	
	
	
	@POST
	@Path("/follow")
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean follow(@FormParam("Follower_email") String follower_email , @FormParam("pass") String pass ,
			@FormParam("Followed_email") String followed_email){
				return UserModel.follow(follower_email, pass, followed_email);
				
	}
	

	@POST
	@Path("/followers")
	@Produces(MediaType.TEXT_PLAIN)
	public String Follower(@FormParam("email") String email /*, @FormParam("pass") String pass*/) throws SQLException {
		ArrayList<String> followers = new ArrayList<>();
		JSONObject object = new JSONObject();
		//if(UserModel.login(email, pass) != null){
			followers = UserModel.get_followers(email);
			for(int i = 0 ; i < followers.size() ; i++){
				object.put("follower #" +(i+1), followers.get(i));
			}
			
			
		//}
		return object.toJSONString();
	}

}
