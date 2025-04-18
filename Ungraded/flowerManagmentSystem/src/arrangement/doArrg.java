package arrangement;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;
import BasicIO.BasicForm;
import Media.Picture;
import catalog.itemCatalog;
import item.myItem;

public class doArrg {
    
    BasicForm form;
    BasicForm addForm;
    BasicForm findForm;
    BasicForm customerForm;
    BasicForm createForm;
    String arrgName;
    double arrgPrice;
    
    public void formSetup() {
        form = new BasicForm("Previous Item", "Next Item", "Add Item", "Delete Item", "Edit Item", "List Items", "Find Item", "Exit");
        
        form.addTextField("name", "Name   ", 40);
        form.addTextField("price", "Price    ", 10);
        form.addTextField("desc", "Description      ", 40);
        form.addTextField("qty", "Quantity    ", 10);
        form.addTextField("inv", "Inventory    ", 10);
        form.addTextField("type", "Type    ", 20);
        form.addCanvas("pic", 300, 300, 500, 0);
        
        form.setEditable("name", false);
        form.setEditable("price", false);
        form.setEditable("desc", false);
        form.setEditable("qty", false);
        form.setEditable("inv", false);
        form.setEditable("type", false);
        
        ////////////////////////////////////////////
        
        addForm = new BasicForm("Ok", "Cancel");
        
        addForm.setTitle("Add Item");
        
        addForm.addTextField("name", "Name   ", 40);
        addForm.addTextField("qty", "Quantity    ", 10);
        addForm.addTextArea("refList", "Item Catalog    ", 3, 30);
        addForm.setEditable("refList", false);
        
        //////////////////////////////////////////////////////////////////////////
        
        createForm = new BasicForm("Ok", "Cancel");
        
        createForm.setTitle("Create Item");
        
        createForm.addTextField("name", "Name   ", 40);
        createForm.addTextField("price", "Price    ", 10);
        createForm.addTextField("desc", "Description      ", 40);
        createForm.addTextField("qty", "Quantity    ", 10);
        createForm.addTextField("inv", "Inventory    ", 10);
        createForm.addTextField("type", "Type    ", 20);
        createForm.addTextField("file", "File Directory   ", 20);
        
        /////////////////////////////////////////////////////////////////////////
        
        findForm = new BasicForm("Find", "Cancel");
        
        findForm.setTitle("Find Item");
        
        findForm.addTextField("find", "Find   ", 40);
        
        ///////////////////////////////////////////////////////////////
        
        customerForm = new BasicForm("Previous Item", "Next Item", "List Items", "Find Item", "Ok");
        
        customerForm.addTextField("name", "Name   ", 40);
        customerForm.addTextField("price", "Price    ", 10);
        customerForm.addTextField("desc", "Description      ", 40);
        customerForm.addTextField("qty", "Quantity    ", 10);
        customerForm.addTextField("inv", "Inventory    ", 10);
        customerForm.addTextField("type", "Type    ", 20);
        customerForm.addCanvas("pic", 300, 300, 500, 0);
        
        customerForm.setEditable("name", false);
        customerForm.setEditable("price", false);
        customerForm.setEditable("desc", false);
        customerForm.setEditable("qty", false);
        customerForm.setEditable("inv", false);
        customerForm.setEditable("type", false);
    }
    
    public void loadItems(ASCIIDataFile in, myArrangement r) {
        myItem aItem;
        
        arrgName = in.readString();
        arrgPrice = in.readDouble();
        
        itemCatalog cata = new itemCatalog("Item Catalog", "Item");
        cata.listStart();
        cata.load();
        
        while (!in.isEOF()) {
            String name = in.readString();
            int qty = in.readInt();
            
            aItem = cata.search(name);
            
            if (!cata.isFound()) {
                System.out.println("no such item in catalog");
            } else {
                r.addItem(aItem, qty);
            }
        }
        
        cata.save();
        
        in.close();
        if (r.itemHead == null) {
            r.current = null;
        } else {
            r.current = r.itemHead;
        }
    }
    
    
    public void displayItems(myItem c, myArrangement r) {
        if (c == null) {
            System.out.println("DEBUG: No valid item to display.");
            form.writeString("name", "");
            form.writeString("price", "");
            form.writeString("desc", "");
            form.writeString("qty", "");
            form.writeString("inv", "");
            form.writeString("type", "");
            
            return;
        }
        form.setTitle(arrgName + " - Items");
        
        form.writeString("name", c.getName());
        form.writeDouble("price", c.getPrice());
        form.writeString("desc", c.getDescription());
        form.writeInt("qty", r.displayItemQty());
        form.writeInt("inv", c.getInv());
        form.writeString("type", c.getType());
        Picture p = new Picture(c.getFile());
        form.placePicture("pic", p);
        
    }
    
    public void displayItemsCustomer(myItem c, myArrangement r) {
        customerForm.writeString("name", c.getName());
        customerForm.writeDouble("price", c.getPrice());
        customerForm.writeString("desc", c.getDescription());
        customerForm.writeInt("qty", r.displayItemQty());
        customerForm.writeInt("inv", c.getInv());
        customerForm.writeString("type", c.getType());
        Picture p = new Picture(c.getFile());
        customerForm.placePicture("pic", p);
    }
    
    public void addForm(myArrangement r) {
        addForm.clearAll();
        createForm.clearAll();
        
        itemCatalog cata = new itemCatalog("Item Catalog", "Item");
        cata.listStart();
        cata.load();
        
        addForm.writeString("refList", cata.listRef());
        
        int button = addForm.accept();
        switch (button) {
            case 0:
                String name = addForm.readString("name");
                int qty = addForm.readInt("qty");
                
                myItem aItem = cata.search(name);
                
                if (!cata.isFound()) {
                    createForm.writeString("name", name);
                    createForm.writeInt("qty", qty);
                    
                    int createButton = createForm.accept();
                    switch (createButton) {
                        case 0:
                            String desc = createForm.readString("desc");
                            String type = createForm.readString("type");
                            String file = createForm.readString("file");
                            double price = createForm.readDouble("price");
                            int inv = createForm.readInt("inv");
                            
                            myItem create = new myItem(name, desc, inv, type, price, file);
                            
                            cata.addObject(create);
                            
                            aItem = cata.search(name);
                            r.addItem(aItem, qty);
                            cata.save();
                            createForm.hide();
                            break;
                        case 1:
                            createForm.hide();
                            cata.save();
                            return;
                    }
                } else {
                    r.addItem(aItem, qty);
                    cata.save();
                    createForm.hide();
                    addForm.hide();
                }
                createForm.hide();
                addForm.hide();
                break;
            
            case 1:
                cata.save();
                return;
        }
    }
    
    public void delete(String search, myArrangement r) {
        r.removeItem(search);
    }
    
    public void edit(myItem c, myArrangement r) {
        addForm.clearAll();
        
        addForm.writeString("name", c.getName());
        addForm.writeDouble("price", c.getPrice());
        addForm.writeString("desc", c.getDescription());
        addForm.writeInt("qty", r.displayItemQty());
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
                int qty = addForm.readInt("qty");
                int inv = addForm.readInt("inv");
                String file = addForm.readString("file");
                if (itemName == null || itemName.isEmpty()) {
                    return;
                } else {
                    r.changeItemQuantity(c.getName(), qty);
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
    
    public void customer(ASCIIDataFile in, myArrangement r, ASCIIOutputFile out) {
        formSetup();
        
        r.listStart();
        
        loadItems(in, r);
        
        myItem current = r.getFirstItem();
        myItem temp;
        
        if (current == null) {
            System.out.println("No items loaded");
            return;
        }
        
        displayItemsCustomer(current, r);
        int button;
        while (true) {
            button = customerForm.accept();
            switch (button) {
                case 0:
                    current = r.up();
                    break;
                
                case 1: // Down
                    current = r.down();
                    break;
                
                case 2:
                    r.listItems();
                    break;
                
                case 3:
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
                
                case 4:
                    r.saveArrangementItemList(out);
                    form.close();
                    addForm.close();
                    findForm.close();
                    customerForm.close();
                    createForm.close();
                    return;
            }
            displayItemsCustomer(current, r);
            
        }
    }
    
    public void open(ASCIIDataFile in, myArrangement r, ASCIIOutputFile out) {
        formSetup();
        
        r.listStart();
        
        loadItems(in, r);
        
        // Use the new getFirstItem method
        myItem current = r.getFirstItem();
        myItem temp;
        
        if (current == null) {
            System.out.println("No items loaded");
            return;
        }
        
        displayItems(current, r);
        int button;
        while (true) {
            button = form.accept();
            switch (button) {
                case 0: // Up
                    current = r.up();
                    break;
                case 1: // Down
                    current = r.down();
                    break;
                case 2: // Add
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
                    r.listItems();
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
                    r.saveArrangementItemList(out);
                    form.close();
                    addForm.close();
                    findForm.close();
                    customerForm.close();
                    createForm.close();
                    return;
            }
            displayItems(current, r);
            
        }
        
    }
}