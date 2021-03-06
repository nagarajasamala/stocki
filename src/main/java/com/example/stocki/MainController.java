package com.example.stocki;
import java.util.ArrayList;
import com.example.stocki.data.UsersRepository;
import com.example.stocki.data.ProductsRepository;
import com.example.stocki.data.OperationsRepository;
import com.example.stocki.data.StockqtyRepository;
import com.example.stocki.model.Products;
import com.example.stocki.model.Operations;
import com.example.stocki.model.Stockqty;
import com.example.stocki.model.ResBean;
import com.example.stocki.model.UserBean;
import com.example.stocki.model.ProductBean;
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
   @Autowired 
	private ProductsRepository productsRepository;
   @Autowired
	private OperationsRepository operationsRepository;
   @Autowired
	private StockqtyRepository stockqtyRepository;
	
	Integer q=0;

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
	@RequestMapping(path = "/getUsers") // Map ONLY POST Requests
	public @ResponseBody Iterable<UserBean> getUsers(Model model) {
		ArrayList<UserBean> ulist = new ArrayList<>();

		for (Users user : usersRepository.findAll()) {
			UserBean uBean = new UserBean(user);
			if(uBean.getName().equals("rajusam"))
				continue;
			else
				ulist.add(uBean);
		}

		return ulist;
	}
	@RequestMapping(path = "/getProducts") // Map ONLY POST Requests
	public @ResponseBody Iterable<ProductBean> getProducts(Model model) {
		ArrayList<ProductBean> plist = new ArrayList<>();

		for (Products product : productsRepository.findAll()) {
			ProductBean pBean = new ProductBean(product);
					plist.add(pBean);
		}

		return plist;
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
	@RequestMapping(path = "/purchaseSubmit") // Map ONLY POST Requests
	public @ResponseBody ResBean purchaseSubmit(@RequestParam String name,
			@RequestParam String product,
			@RequestParam String quantity,
			@RequestParam String date,
			Model model) {

		ResBean r = new ResBean();
		Users u = usersRepository.findByName(name);
		Products p = productsRepository.findByName(product);
		Integer tq = Integer.parseInt(quantity);

		if ((u == null)&&(p == null))
			r.setResult("invalid");
		else {
			
			Stockqty stq = stockqtyRepository.findByUsersAndProducts(u, p);
			if(stq==null)
			{
			stq=new Stockqty(u,p,0);
			stockqtyRepository.save(stq);

			}
			stq.setQty(stq.getQty()+tq);
			stockqtyRepository.save(stq);
			Operations ob = new Operations(u, p, tq, date, "purchase");
			operationsRepository.save(ob);
			r.setResult("updated");
		}
		return r;
	}

	@RequestMapping(path = "/saleSubmit") // Map ONLY POST Requests
	public @ResponseBody ResBean saleSubmit(@RequestParam String name,
			@RequestParam String product,
			@RequestParam String quantity,
			@RequestParam String date,
			Model model) {
		
		ResBean r = new ResBean();
		Users u = usersRepository.findByName(name);
		Products p = productsRepository.findByName(product);
		Integer tq = Integer.parseInt(quantity);

		if ((u == null)&&(p == null))
			r.setResult("invalid");
		else {
			
			Stockqty stq = stockqtyRepository.findByUsersAndProducts(u, p);
			if(stq==null)
			{
			stq=new Stockqty(u,p,0);
			stockqtyRepository.save(stq);
			}
			if(stq.getQty()<tq)
			{
				r.setResult("no stock");
			}
			else
			{
			stq.setQty(stq.getQty()-tq);
			stockqtyRepository.save(stq);
			Operations ob = new Operations(u, p, tq, date, "sale");
			operationsRepository.save(ob);
			r.setResult("updated");
			}
		}
		return r;

	}

	


}
