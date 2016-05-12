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

import com.models.Action;
import com.models.PlaceModel;
@Path("/ActionService")
public class ActionServices {
	@POST
	@Path("/undoaction")
	@Produces(MediaType.TEXT_PLAIN)
	public String UndoAction(@FormParam("userid")int userid,@FormParam("Actionid") int  id) throws SQLException {
			boolean UNdo=false;
			UNdo=Action.undoAction(id, userid);
			if(UNdo==false)
				return "Fail To Undo This Action";
			
			return "Successful To Undo This Action";
	}
	
	@POST
	@Path("/getactions")
	@Produces(MediaType.TEXT_PLAIN)
	public String showplaces(@FormParam("userid") int  userid) throws SQLException {
		ArrayList<String> actions = new ArrayList<>();
		JSONObject object = new JSONObject();
			actions= Action.GetActions(userid);
			for(int i = 0 ; i < actions.size() ; i++){
				object.put("Action #" +(i+1), actions.get(i));
			}
			System.out.println(object.toJSONString());
		return object.toJSONString();
	}

	

}
