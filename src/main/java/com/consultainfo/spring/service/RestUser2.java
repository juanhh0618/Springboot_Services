package com.consultainfo.spring.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.consultainfo.spring.vo.VOUser2;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



@RestController //Le decimos que es un rest controller
@RequestMapping(path = "/loginService/JC") //Ruta para encontrar el servicio
public class RestUser2 {

	@RequestMapping(
			method = RequestMethod.POST, //indicamos que el servicio es POST
			path = "/validateUser", //Ruta del metodo
			//consumes = "application/json", //Indicamos que recibe json
			//produces = "application/json") //Indicamos que produce json
			consumes = "application/xml",
			produces = "application/xml")
	public @ResponseBody VOUser2 validateUser(@RequestBody VOUser2 user) throws Exception {
		VOUser2 response = new VOUser2();
		
		response.setStatus("Invalid User");
		System.out.println("User: " + user.getUser());
		System.out.println("Password: " + user.getPassword());
		
		
		try {		
				JSONParser parser = new JSONParser();			
				Object obj = parser.parse(new FileReader("C:\\Users\\JUANCARLOSHERNANDEZH\\Desktop\\usuarios.json"));				
				JSONObject jsonObject = (JSONObject) obj;
			    JSONArray listUsers =  (JSONArray) jsonObject.get("usuarios");
			    
			    listUsers.get(1);
				
			   for (int i = 0; i < listUsers.size(); i++) {	
				   
				jsonObject = (JSONObject) listUsers.get(i);   
				if (user.getUser().equals(jsonObject.get("user").toString()) && user.getPassword().equals(jsonObject.get("password").toString())) {
				response.setName(jsonObject.get("name").toString());
				response.setUser(jsonObject.get("user").toString());
				response.setLastAccess(jsonObject.get("lastAccess").toString());
				response.setPassword("******");
				response.setStatus(jsonObject.get("status").toString());
				}
		    }
				
			return response;
			
		} catch (Exception ex) {
			
			return response;
		}
	}

}