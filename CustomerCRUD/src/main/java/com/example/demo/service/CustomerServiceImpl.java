package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidMobileNumber;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository cr;

	@Override
	public void add(Customer customer) {
		String mob = customer.getMob();
		if (mob.length() == 10) {
			if (mob.charAt(0) == '0' || mob.charAt(0) == '1' || mob.charAt(0) == '2' || mob.charAt(0) == '3'
					|| mob.charAt(0) == '4' || mob.charAt(0) == '5')
				throw new InvalidMobileNumber("INVALID MOBILE NUMBER");

			for (int i = 0; i < mob.length(); i++) {
				if (!Character.isDigit(mob.charAt(i)))
					throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
			}
		} else {
			throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
		}

		cr.save(customer); // Insert

	}

	@Override
	public List<Customer> display() {
		// TODO Auto-generated method stub
		return cr.findAll(); // Select * from Customer;
	}

	@Override
	public Customer delete(Integer id) {
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			cr.deleteById(id); // Delete
			return temp;
		}
		return null;
	}

	@Override
	public void update(Customer customer, Integer id) {
		customer.setId(id);
		cr.save(customer);

	}

	@Override
	public Customer search(Integer id) {
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			return temp;
		}
		return null;
	}

	@Override
	public void addAll(List<Customer> list) {
		cr.saveAll(list);

	}

	@Override
	public Customer findByMob(String mob) {
		return cr.findByMob(mob);
	}

//	@Override
//	public Customer findMob(String mob) {
//		return cr.findByMob(mob);
//	}

}