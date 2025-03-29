package arrangement;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;
import BasicIO.BasicForm;
import Media.Picture;
import item.myItem;

public class doArrg {
    
    BasicForm form;
    BasicForm addForm;
    BasicForm findForm;
    String arrgName;
    double arrgPrice;
    
    public void formSetup() {
        form = new BasicForm("Previous Item", "Next Item", "Add Item", "Delete Item", "Edit Item", "List Items", "Find Item", "Exit");
        
        
        form.addTextField("name", "Name   ",40);
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
        addForm.addTextField("price", "Price    ", 10);
        addForm.addTextField("desc", "Description      ", 40);
        addForm.addTextField("qty", "Quantity    ", 10);
        addForm.addTextField("inv", "Inventory    ", 10);
        addForm.addTextField("type", "Type    ", 20);
        addForm.addTextField("file", "File Directory   ", 20);
        
        /////////////////////////////////////////////////////////////////////////
        
        findForm = new BasicForm("Find", "Cancel");
        
        findForm.setTitle("Find Item");
        
        findForm.addTextField("find", "Find   ", 40);
    }
    
    public void loadItems(ASCIIDataFile in, myArrangement r) {
        myItem aItem;
        
        arrgName = in.readString();
        arrgPrice = in.readDouble();
        
        while (!in.isEOF()) {
            // Read item details first
            String type = in.readString();
            String itemName = in.readString();
            String itemDesc = in.readString();
            
            // Read arrangement-specific quantity
            int arrangeQty = in.readInt();
            
            // Read inventory
            int itemInv = in.readInt();
            double price = in.readDouble();
            String file = in.readString();
            
            // Create item with basic details
            aItem = new myItem(itemName, itemDesc, itemInv, type, price, file);
            
            if (aItem.getName() == null || aItem.getName().isEmpty()) {
                continue;
            }
            
            // Add item with its specific arrangement quantity
            r.addItem(aItem, arrangeQty);
        }
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
    
    public void addForm(myArrangement r) {
        addForm.clearAll();
        
        int button = addForm.accept();
        switch (button) {
            case 0:
                String type = addForm.readString("type");
                String name = addForm.readString("name");
                double price = addForm.readDouble("price");
                String desc = addForm.readString("desc");
                int qty = addForm.readInt("qty");
                int inv = addForm.readInt("inv");
                String file = addForm.readString("file");
                
                
                myItem aItem = new myItem(name, desc, inv, type, price, file);
                if (aItem.getName() == null || aItem.getName().isEmpty()) {
                    return;
                } else {
                    r.addItem(aItem, qty);
                    addForm.hide();
                }
            
            case 1:
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
                    form.hide();
                    addForm.hide();
                    findForm.hide();
                    return;
            }
            displayItems(current, r);
            
        }
        
    }
}