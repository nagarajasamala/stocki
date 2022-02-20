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
   

	@RequestMapping(path="/") // Map ONLY POST Requests
	public  @ResponseBody String  Index(Model model) 
	{  	
	
		return "hello";

		
	}
	@RequestMapping(path="/UserSignUp") // Map ONLY POST Requests
	public  @ResponseBody ResBean  UserSignUp(@RequestParam String name , @RequestParam String pd,Model model) 
	{  			ResBean r=new ResBean();
				Users u = usersRepository.findByName(name);
				if(u!=null)
				{

					r.setResult("user exists");
				}
				if(u==null)
				{
				//Check user alreday existing
				u = new Users();
				u.setName(name);
				u.setPd(pd);
				usersRepository.save(u);
				r.setResult("user created");
				}
				return r;
	}
	@RequestMapping(path="/UserLogin") // Map ONLY POST Requests
	public  @ResponseBody ResBean  UserLogin(@RequestParam String name , @RequestParam String pd,Model model) 
	{  	
				Users u = usersRepository.findByName(name);
				ResBean r = new ResBean();
				if(u==null)
				{
					r.setResult("fail");
					return r;
				}
				else if(u.getName().equals("rajusam")&&u.getPd().equals(pd))
				{
					r.setResult("ok");
					return r;
				}
				else if(u.getName().equals(name)&&u.getPd().equals(pd))
				{
					r.setResult("*");
					return r;
				}
				else
				{
					r.setResult("incorrect password");
					return r;
				}
	}
	@RequestMapping(path="/addProduct") // Map ONLY POST Requests
	public  @ResponseBody ResBean  addProduct(@RequestParam String name ,Model model) 
	{  			ResBean r=new ResBean();
				Products p = productsRepository.findByName(name);
				if(p!=null)
				{
					r.setResult("product exists");
				}
				if(p==null)
				{
				//Check user alreday existing
				p = new Products();
				p.setName(name);
				p.setQty(0);
				productsRepository.save(p);
				r.setResult("product created");
				}
				return r;
	}


}
