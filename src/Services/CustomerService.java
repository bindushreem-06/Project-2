package Services;

import Model.Customer;
import Exception.DuplicateEntryException;
import Exception.ResourceNotFoundException;
import java.util.HashMap;

public class CustomerService {
    private HashMap<Integer, Customer> customerDB = new HashMap<>();

    public void addCustomer(Customer c) throws DuplicateEntryException {
        if (customerDB.containsKey(c.getId())) {
            throw new DuplicateEntryException("Customer ID already exists!");
        }
        customerDB.put(c.getId(), c);
    }

    public HashMap<Integer, Customer> getAllCustomers() {
        return customerDB;
    }

    public void updateCustomer(int id, String name, String phone) throws ResourceNotFoundException {
        Customer c = customerDB.get(id);
        if (c == null) throw new ResourceNotFoundException("Customer not found!");
        c.setName(name);
        c.setPhone(phone);
    }

    public void deleteCustomer(int id) throws ResourceNotFoundException {
        if (!customerDB.containsKey(id)) {
            throw new ResourceNotFoundException("Customer not found!");
        }
        customerDB.remove(id);
    }
}
