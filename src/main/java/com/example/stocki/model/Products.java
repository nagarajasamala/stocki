package com.example.stocki.model;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.*;

@Entity
@Table(name = "products")// This tells Hibernate to make a table out of this class
public class Products {
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_generator")
	@SequenceGenerator(name="products_generator", sequenceName = "products_seq", allocationSize=1)
	@Column(name = "pid", updatable = false, nullable = false)
	private Integer pid;
	@Column(unique = true)
	private String name;
	private Integer qty;

	
	@OneToMany(mappedBy = "products",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
   	Set<Operations> operationsregistrations;

	public Set<Operations> getOperationsregistrations() {
        return operationsregistrations;
  	  }
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pid == null) ? 0 : pid.hashCode());
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
        Products other = (Products) obj;
        if (pid == null) {
            if (other.pid != null)
                return false;
        } else if (!pid.equals(other.pid))
            return false;
        return true;
    }
	@Override
	public String toString() {
		return "Products [pid=" + pid + ", name=" + name + ", qty=" + qty + "]";
	}
	
}
