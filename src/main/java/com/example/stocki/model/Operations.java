package com.example.stocki.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity(name="OperationsEntity")
@Table(name = "operations") // This tells Hibernate to make a table out of this class
public class Operations  {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "op_generator")
	@SequenceGenerator(name="op_generator", sequenceName = "op_seq", allocationSize=1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Integer qty;
	private String date;
	private String op;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "productId")
    @JsonBackReference
    private Products products;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "userId")
    @JsonBackReference
    private Users users;
   	 

	public Operations() {}
	public Operations(Users users,Products products,Integer qty,String date,String op)
    	{
    		this.users=users;
    		this.products=products;
    		this.qty=qty;
    		this.date=date;
			this.op=op;
   	 }
    
   	 public Users getUsers() {
       		 return users;
  	  }

   	 public void setUsers(Users users) {
      		  this.users = users;
    	}

  	  public Products getProducts() {
      		  return products;
    	}

   	 public void setProducts(Products products) {
      		  this.products = products;
   	 }
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Operations other = (Operations) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
	@Override
	public String toString() {
		return "Opeations [id=" + id + ", qty=" + qty + ", date=" + date + "op" + op + "]";
	}
	
	
	
}
