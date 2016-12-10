package CarSaleManagerSystem.Service;


import CarSaleManagerSystem.Bean.Customer;
import CarSaleManagerSystem.DAO.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by googo on 16/8/10.
 */

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    public void createCustomer(Customer customer){ customerDAO.createCustomer(customer);}

    public List<Customer> getAllCustomer(int storefront){
        List<Customer> customers = customerDAO.getAllCustomer();
        List<Customer> result = new ArrayList<>();
        for(Customer customer:customers)
        {
            if(customer.getStorefront_id() == storefront)
            {
                result.add(customer);
            }
        }
        return result;
    }

    public void removeCustomer(Customer customer){customerDAO.removeCustomer(customer);}

    public void updateCustomer(Customer customer){customerDAO.updateCustomer(customer);}

    public Customer findCustomerById(int customerID){return customerDAO.findCustomerById(customerID);}


}
