package catalog;

import BasicIO.BasicForm;
import Media.Picture;
import item.myItem;

public class doCataItem {
    BasicForm form;
    BasicForm addForm;
    BasicForm findForm;
    BasicForm customerForm;
    
    public void formSetup() {
        form = new BasicForm("Previous Item", "Next Item", "Add Item", "Delete Item", "Edit Item", "List Items", "Find Item", "Exit");
        
        form.addTextField("name", "Name   ", 40);
        form.addTextField("price", "Price    ", 10);
        form.addTextField("desc", "Description      ", 40);
        form.addTextField("inv", "Inventory    ", 10);
        form.addTextField("type", "Type    ", 20);
        form.addCanvas("pic", 300, 300, 500, 0);
        
        form.setEditable("name", false);
        form.setEditable("price", false);
        form.setEditable("desc", false);
        form.setEditable("inv", false);
        form.setEditable("type", false);
        
        //////////////////////////////////////////////////////////////////////
        
        addForm = new BasicForm("Ok", "Cancel");
        
        addForm.setTitle("Add Item");
        
        addForm.addTextField("name", "Name   ", 40);
        addForm.addTextField("price", "Price    ", 10);
        addForm.addTextField("desc", "Description      ", 40);
        addForm.addTextField("inv", "Inventory    ", 10);
        addForm.addTextField("type", "Type    ", 20);
        addForm.addTextField("file", "File Directory   ", 20);
        
        /////////////////////////////////////////////////////////////////////////
        
        findForm = new BasicForm("Find", "Cancel");
        
        findForm.setTitle("Find Item");
        
        findForm.addTextField("find", "Find   ", 40);
        
        ///////////////////////////////////////////////////////////////
        
        customerForm = new BasicForm("Previous Item", "Next Item", "Find Item", "Ok");
        
        customerForm.addTextField("name", "Name   ", 40);
        customerForm.addTextField("price", "Price    ", 10);
        customerForm.addTextField("desc", "Description      ", 40);
        customerForm.addTextField("inv", "Inventory    ", 10);
        customerForm.addTextField("type", "Type    ", 20);
        customerForm.addCanvas("pic", 300, 300, 500, 0);
        
        customerForm.setEditable("name", false);
        customerForm.setEditable("price", false);
        customerForm.setEditable("desc", false);
        customerForm.setEditable("inv", false);
        customerForm.setEditable("type", false);
    }
    
    public void display(myItem c, itemCatalog r) {
        if (c == null) {
            System.out.println("DEBUG: No valid item to display.");
            form.writeString("name", "");
            form.writeString("price", "");
            form.writeString("desc", "");
            form.writeString("inv", "");
            form.writeString("type", "");
            return;
        }
        
        form.setTitle(r.getName());
        form.writeString("name", c.getName());
        form.writeDouble("price", c.getPrice());
        form.writeString("desc", c.getDescription());
        form.writeInt("inv", c.getInv());
        form.writeString("type", c.getType());
        Picture p = new Picture(c.getFile());
        form.placePicture("pic", p);
    }
    
    public void customerDisplay(myItem c, itemCatalog r) {
        customerForm.setTitle(r.getName());
        customerForm.writeString("name", c.getName());
        customerForm.writeDouble("price", c.getPrice());
        customerForm.writeString("desc", c.getDescription());
        customerForm.writeInt("inv", c.getInv());
        customerForm.writeString("type", c.getType());
        Picture p = new Picture(c.getFile());
        customerForm.placePicture("pic", p);
    }
    
    public void addForm(itemCatalog r) {
        addForm.clearAll();
        int button = addForm.accept();
        switch (button) {
            case 0:
                String type = addForm.readString("type");
                String name = addForm.readString("name");
                double price = addForm.readDouble("price");
                String desc = addForm.readString("desc");
                int inv = addForm.readInt("inv");
                String file = addForm.readString("file");
                
                myItem aItem = new myItem(name, desc, inv, type, price, file);
                if (aItem.getName() == null || aItem.getName().isEmpty()) {
                    return;
                } else {
                    r.addObject(aItem);
                    addForm.hide();
                }
            
            case 1:
                return;
        }
    }
    
    public void delete(String search, itemCatalog r) {
        r.removeObject(search);
    }
    
    public void edit(myItem c, itemCatalog r) {
        addForm.clearAll();
        addForm.writeString("name", c.getName());
        addForm.writeDouble("price", c.getPrice());
        addForm.writeString("desc", c.getDescription());
        addForm.writeInt("inv", c.getInv());
        addForm.writeString("type", c.getType());
        addForm.writeString("file", c.getFile());
        
        int button = addForm.accept();
        switch (button) {
            case 0:
                String type = addForm.readString("type");
                String itemName = addForm.readString("name");
                double price = addForm.readDouble("price");
                String itemDesc = addForm.readString("desc");
                int inv = addForm.readInt("inv");
                String file = addForm.readString("file");
                if (itemName == null || itemName.isEmpty()) {
                    return;
                } else {
                    c.setName(itemName);
                    c.setDescription(itemDesc);
                    c.setInv(inv);
                    c.setType(type);
                    c.setPrice(price);
                    c.setFile(file);
                    addForm.hide();
                }
            case 1:
                return;
        }
    }
    
    public void customer(itemCatalog r) {
        formSetup();
        r.listStart();
        r.load();
        
        myItem current = r.getFirstObject();
        myItem temp;
        
        if (current == null) {
            System.out.println("No Items Loaded");
            return;
        }
        
        customerDisplay(current, r);
        int button;
        while (true) {
            button = customerForm.accept();
            switch (button) {
                case 0:
                    current = r.up();
                    break;
                
                case 1:
                    current = r.down();
                    break;
                
                case 2:
                    int findButton = findForm.accept();
                    switch (findButton) {
                        case 0:
                            temp = r.search(findForm.readString("find"));
                            if (temp != null) {
                                current = temp;
                            }
                            findForm.hide();
                        case 1:
                            break;
                    }
                    break;
                    
                case 3:
                    r.save();
                    form.close();
                    addForm.close();
                    findForm.close();
                    customerForm.close();
                    return;
            }
            customerDisplay(current, r);
            
        }
    }
    
    public void openCataItem(itemCatalog r) {
        formSetup();
        r.listStart();
        r.load();
        
        myItem current = r.getFirstObject();
        myItem temp;
        
        if (current == null) {
            System.out.println("No Items Loaded");
            return;
        }
        
        display(current, r);
        int button;
        while (true) {
            button = form.accept();
            switch (button) {
                case 0:
                    current = r.up();
                    break;
                
                case 1:
                    current = r.down();
                    break;
                    
                case 2:
                    addForm(r);
                    current = r.getCurrent();
                    break;
                    
                case 3:
                    delete(current.getName(), r);
                    current = r.getCurrent();
                    break;
                    
                case 4:
                    edit(current, r);
                    break;
                    
                case 5:
                    r.list();
                    break;
                    
                case 6:
                    int findButton = findForm.accept();
                    switch (findButton) {
                        case 0:
                            temp = r.search(findForm.readString("find"));
                            if (temp != null) {
                                current = temp;
                            }
                            findForm.hide();
                        case 1:
                            break;
                    }
                    break;
                    
                case 7:
                    r.save();
                    form.close();
                    addForm.close();
                    findForm.close();
                    customerForm.close();
                    return;
            }
            display(current, r);
        }
    }
}
