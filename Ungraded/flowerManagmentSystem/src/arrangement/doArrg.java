package arrangement;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;
import BasicIO.BasicForm;
import minor.myItem;

public class doArrg {
    
    BasicForm form;
    BasicForm addForm;
    String arrgName;
    double arrgPrice;
    
    public void formSetup() {
        form = new BasicForm("Up", "Down", "Add", "Delete", "Edit", "List", "Find", "Exit");
        
        form.addTextField("arrgName", "Arrangement", 20);
        form.addTextField("name", "Name   ", 20);
        form.addTextField("desc", "Description      ", 20);
        form.addTextField("qty", "Quantity", 10);
        form.addTextField("inv", "Inventory", 10);
        form.addTextField("type", "Type    ", 20);
        form.addTextField("find", "Find", 20);
        
        form.setEditable("arrgName", false);
        form.setEditable("name", false);
        form.setEditable("desc", false);
        form.setEditable("qty", false);
        form.setEditable("inv", false);
        form.setEditable("type", false);
        
        ////////////////////////////////////////////
        
        addForm = new BasicForm("Ok", "Cancel");
        
        addForm.addTextField("name", "Name   ", 20);
        addForm.addTextField("desc", "Description      ", 20);
        addForm.addTextField("qty", "Quantity", 10);
        addForm.addTextField("inv", "Inventory", 10);
        addForm.addTextField("type", "Type    ", 20);
    }
    
    public void loadItems(ASCIIDataFile in, myArrangement r) {
        myItem aItem;
        int itemCount = 0;
        
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
            
            // Create item with basic details
            aItem = new myItem(itemName, itemDesc, itemInv, type);
            
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
            form.writeString("desc", "");
            form.writeString("qty", "");
            form.writeString("inv", "");
            form.writeString("type", "");
            
            return;
        }
        
        String price = "" + r.getPrice();
        form.writeLine("arrgName", r.getName() + "    " + price);
        form.writeString("name", c.getName());
        form.writeString("desc", c.getDescription());
        form.writeInt("qty", r.displayItemQty());
        form.writeInt("inv", c.getInv());
        form.writeString("type", c.getType());
        
    }
    
    public void addForm(myArrangement r) {
        int button = addForm.accept();
        switch (button) {
            case 0:
                String type = addForm.readString("type");
                String name = addForm.readString("name");
                String desc = addForm.readString("desc");
                int qty = addForm.readInt("qty");
                int inv = addForm.readInt("inv");
                
                myItem aItem = new myItem(name, desc, inv, type);
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
        
        addForm.writeString("name", c.getName());
        addForm.writeString("desc", c.getDescription());
        addForm.writeInt("qty", r.displayItemQty());
        addForm.writeInt("inv", c.getInv());
        addForm.writeString("type", c.getType());
        
        int button = addForm.accept();
        switch (button) {
            case 0:
                String type = addForm.readString("type");
                String itemName = addForm.readString("name");
                String itemDesc = addForm.readString("desc");
                int qty = addForm.readInt("qty");
                int inv = addForm.readInt("inv");
                if (itemName == null || itemName.isEmpty()) {
                    return;
                } else {
                    r.changeItemQuantity(c.getName(), qty);
                    c.setName(itemName);
                    c.setDescription(itemDesc);
                    c.setInv(inv);
                    c.setType(type);
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
                    temp = r.search(form.readString("find"));
                    if (temp != null) {
                        current = temp;
                    }
                    break;
                case 7:
                    r.save(out);
                    form.close();
                    break;
            }
            displayItems(current, r);
            
        }
        
    }
}