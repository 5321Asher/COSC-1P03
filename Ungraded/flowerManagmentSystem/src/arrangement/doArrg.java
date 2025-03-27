package arrangement;

import BasicIO.ASCIIDataFile;
import BasicIO.BasicForm;
import minor.myItem;

public class doArrg {
    
    BasicForm form;
    String arrgName;
    double arrgPrice;
    
    public void formSetup() {
        form = new BasicForm("Up", "Down", "Add", "Delete", "List", "Find", "Exit");
        
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
    }
    
    public void loadItems(ASCIIDataFile in, myArrangement r) {
        myItem aItem;
        int itemCount = 0;
        
        arrgName = in.readString();
        arrgPrice = in.readDouble();
        
        
        while (!in.isEOF()) {
            aItem = new myItem(in);
            
            if (aItem.getName() == null || aItem.getName().isEmpty()) {
                //System.out.println("DEBUG: Skipping invalid item at line " + (itemCount + 1));
                continue;
            }
            r.addItem(aItem, r.getItemQty(aItem.getName(), in));
        }
        in.close();
        if (r.itemHead == null) {
            r.current = null;
        } else {
            r.current = r.itemHead;
        }
    }
    
    
    public void displayItems(myItem c, myArrangement r, ASCIIDataFile in) {
        if (c == null) {
            System.out.println("DEBUG: No valid item to display.");
            form.writeString("name", "");
            form.writeString("desc", "");
            form.writeString("qty", "");
            form.writeString("inv", "");
            form.writeString("type", "");
            
            return;
        }
        
        String price = "" + arrgPrice;
        form.writeLine("arrgName", arrgName + "    " + price);
        form.writeString("name", c.getName());
        form.writeString("desc", c.getDescription());
        form.writeInt("qty", r.displayItemQty());
        form.writeInt("inv", c.getInv());
        form.writeString("type", c.getType());
        
        
    }
    
    public void open(ASCIIDataFile in, myArrangement r) {
        formSetup();
        
        r.listStart();
        
        loadItems(in, r);
        myItem current = r.getCurrent();
        
        if (current == null) {
            System.out.println("No items loaded");
            return;
        }
        
        displayItems(current, r, in);
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
                    // Implement add item functionality
                    break;
                case 3: // Delete
                    // Implement delete item functionality
                    break;
                case 4: // List
                    r.listItems();
                    break;
                case 5: // Find
                    // Implement search functionality
                    break;
                case 6: // Exit
                    form.close();
                    return;
            }
            displayItems(current, r, in);
            
        }
        
    }
}