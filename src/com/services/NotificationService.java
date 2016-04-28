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

import com.models.Notifications;
import com.models.PlaceModel;
import com.models.UserModel;
import java.sql.Connection;
@Path("/NotificationService")
public class NotificationService {
	@POST
	@Path("/addnotifcation")
	@Produces(MediaType.TEXT_PLAIN)
	public String addnotifcation(@FormParam("userid") int userid,@FormParam("checkinid") int checkinid,
			@FormParam("description") String description) {
		Notifications n=Notifications.addNewNotifications(userid, checkinid, description);
		JSONObject json = new JSONObject();
		json.put("nid", n.getNid());
		json.put("userid", n.getUserid());
		json.put("description",n.getDescription());
		json.put("checkinid",n.getActionid());
		
		return json.toJSONString();
	}

	@POST
	@Path("/getnoitfication")
	@Produces(MediaType.TEXT_PLAIN)
	public String Follower(@FormParam("userid") int userid /*, @FormParam("pass") String pass*/) throws SQLException {
		ArrayList<String> not = new ArrayList<>();
		JSONObject object = new JSONObject();
		
		not= Notifications.GetNotifications(userid);
		int x=0;
			for(int i = not.size()-1 ; i >=0 ; i--){
				object.put("notification#" +(x+1), not.get(i));
				x++;
			}
			
			
		//}
		return object.toJSONString();
	}
}
