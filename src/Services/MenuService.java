package Services;

import Model.MenuItem;
import Exception.DuplicateEntryException;
import Exception.ResourceNotFoundException;
import java.util.ArrayList;

public class MenuService {
    private ArrayList<MenuItem> menuList = new ArrayList<>();

    public void addMenuItem(MenuItem item) throws DuplicateEntryException {
        if (menuList.stream().anyMatch(i -> i.getId() == item.getId())) {
            throw new DuplicateEntryException("Menu item with same ID already exists!");
        }
        menuList.add(item);
    }

    public ArrayList<MenuItem> getAllMenuItems() {
        return menuList;
    }

    public MenuItem findById(int id) throws ResourceNotFoundException {
        return menuList.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found!"));
    }

    public void deleteMenuItem(int id) throws ResourceNotFoundException {
        MenuItem item = findById(id);
        menuList.remove(item);
    }
}
