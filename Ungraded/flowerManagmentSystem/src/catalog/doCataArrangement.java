package catalog;

import BasicIO.BasicForm;
import Media.Picture;
import arrangement.myArrangement;

public class doCataArrangement {
    BasicForm form;
    BasicForm addForm;
    BasicForm findForm;
    BasicForm createForm;
    
    public void formSetup() {
        form = new BasicForm("Previous Item", "Next Item", "Add Item", "Delete Item", "Edit Item", "List Items", "Find Item", "Open Item List", "Exit");
        
        form.addTextField("name", "Name   ", 40);
        form.addTextField("price", "Price    ", 10);
        form.addCanvas("pic", 300, 300, 500, 0);
        
        form.setEditable("name", false);
        form.setEditable("price", false);
        
        //////////////////////////////////////////////////////////////////////
        
        addForm = new BasicForm("Ok", "Cancel");
        
        addForm.setTitle("Add Arrangement");
        
        addForm.addTextField("name", "Name   ", 40);
        addForm.addTextField("price", "Price    ", 10);
        addForm.addTextField("picFile", "Picture Dir      ", 20);
        
        /////////////////////////////////////////////////////////////////////////
        
        findForm = new BasicForm("Find", "Cancel");
        
        findForm.setTitle("Find Item");
        
        findForm.addTextField("find", "Find   ", 40);
        
        //////////////////////////////////////////////////////////////
        
        createForm = new BasicForm("Create", "Cancel");
        
        createForm.setTitle("Create Arrangement");
        
        createForm.addTextField("name", "Name   ", 40);
        createForm.addTextField("price", "Price    ", 10);
        createForm.addTextField("picFile", "Picture Dir      ", 20);
    }
    
    public void display(myArrangement c, arrangementCatalog r) {
        if (c == null) {
            System.out.println("DEBUG: No valid item to display.");
            form.writeString("name", "");
            form.writeString("price", "");
            return;
        }
        
        form.setTitle(r.getName());
        form.writeString("name", c.getName());
        form.writeDouble("price", c.getPrice());
        Picture p = new Picture(c.getPicFile());
        form.placePicture("pic", p);
    }
    
    public void addForm(arrangementCatalog r) {
        addForm.clearAll();
        int button = addForm.accept();
        switch (button) {
            case 0:
                String name = addForm.readString("name");
                double price = addForm.readDouble("price");
                String picFile = addForm.readString("picFile");
                
                r.createArrangement(name, price, picFile);
                addForm.hide();
            
            case 1:
                return;
        }
    }
    
    public void delete(String search, arrangementCatalog r) {
        r.removeObject(search);
    }
    
    public void edit(myArrangement c, arrangementCatalog r) {
        addForm.clearAll();
        addForm.writeString("name", c.getName());
        addForm.writeDouble("price", c.getPrice());
        addForm.writeString("picFile", c.getPicFile());
        
        int button = addForm.accept();
        switch (button) {
            case 0:
                String arrangementName = addForm.readString("name");
                double price = addForm.readDouble("price");
                String picFile = addForm.readString("picFile");
                
                if (arrangementName == null || arrangementName.isEmpty()) {
                    return;
                } else {
                    c.setName(arrangementName);
                    c.setPrice(price);
                    c.setPicFile(picFile);
                    addForm.hide();
                }
            case 1:
                return;
        }
    }
    
    public void openCataArrangement(arrangementCatalog r) {
        formSetup();
        r.listStart();
        r.load();
        
        myArrangement current = r.getFirstObject();
        myArrangement temp;
        
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
                    r.openItemList(current);
                    break;
                
                case 8:
                    r.save();
                    form.close();
                    addForm.close();
                    findForm.close();
                    createForm.close();
                    return;
            }
            display(current, r);
        }
    }
}
