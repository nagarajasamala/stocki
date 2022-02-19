package com.example.stocki;

import com.example.stocki.data.UsersRepository;
import com.example.stocki.model.ResBean;
import com.example.stocki.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MainController {
    @Autowired 
	private UsersRepository usersRepository;
   

	@RequestMapping(path="/Index") // Map ONLY POST Requests
	public  @ResponseBody String  Index(Model model) 
	{  	
	
		return "hello";

		
	}
	@RequestMapping(path="/UserSignUp") // Map ONLY POST Requests
	public  @ResponseBody Users  UserSignUp(@RequestParam String name , @RequestParam String pd,Model model) 
	{  	
				Users u = usersRepository.findByName(name);
				if(u==null)
				{
				//Check user alreday existing
				u = new Users();
				u.setName(name);
				u.setPd(pd);
				usersRepository.save(u);
				}
				return u;
	}
	@RequestMapping(path="/UserLogin") // Map ONLY POST Requests
	public  @ResponseBody ResBean  UserLogin(@RequestParam String name , @RequestParam String pd,Model model) 
	{  	
				Users u = usersRepository.findByName(name);
				ResBean r = new ResBean();
				if(u==null)
					r.setResult("fail");
				else if(u.getName().equals("uadmin")&&u.getPd().equals(pd))
				{
					r.setResult("ok");
				}
				else if(u.getName().equals(name)&&u.getPd().equals(pd))
				{
					r.setResult("*");
				}
				else
					r.setResult("fail");
		
				return r;
	}


}
