package com.springbootwebmvc.employee.controller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbootwebmvc.employee.controller.constant.ConstantVariable;
import com.springbootwebmvc.employee.controller.model.Customer;
import com.springbootwebmvc.employee.controller.repository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    /* List added customers. */
    @RequestMapping(value="/listCustomer", method=RequestMethod.GET)
    public String listCustomerList(Model model) {
        List<Customer> customerList = this.customerRepository.findAll();

        model.addAttribute(ConstantVariable.CUSTOMER_LIST, customerList);

        return "listCustomer";
    }

    /* Display add customer form page. */
    @RequestMapping(value="/addCustomerPage", method=RequestMethod.GET)
    public String addCustomerPage() {
        return "addCustomer";
    }

    /* Add user submitted customer info to h2 databae and redirect to list employees page. */
    @RequestMapping(value="/addCustomer", method=RequestMethod.POST)
    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer/listCustomer";
    }
}