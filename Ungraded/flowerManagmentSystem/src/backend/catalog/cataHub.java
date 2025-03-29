package backend.catalog;

import BasicIO.BasicForm;

public class cataHub {
    BasicForm cataSelForm;
    BasicForm adminQForm;
    BasicForm signIn;
    
    public void setForm() {
        cataSelForm = new BasicForm("Arrangement Catalog", "Item Catalog", "Exit");
        
        cataSelForm.setTitle("Catalog Hub");
        
        cataSelForm.addTextField("desc", 50);
        cataSelForm.setEditable("desc", false);
        
        cataSelForm.writeString("desc", "Please select the catalog you would like to open");
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        
        adminQForm = new BasicForm("Admin", "Customer", "Exit");
        
        adminQForm.setTitle("Personnel");
        
        adminQForm.addTextField("desc", 50);
        adminQForm.setEditable("desc", false);
        
        adminQForm.writeString("desc", "How would you like to browse today?");
        
        //////////////////////////////////////////////////////////////////////////////////
        
        signIn = new BasicForm("Sign In", "Cancel");
        
        signIn.setTitle("Sign In");
        
        signIn.addTextField("user", "Username", 50);
        signIn.addTextField("pass", "Password", 50);
        signIn.addTextField("msg", 50);
        signIn.setEditable("msg", false);
    }
    
    public void select() {
        setForm();
        
        int signButton;
        while (true) {
            signButton = adminQForm.accept();
            switch (signButton) {
                case 0:
                    signIn.clearAll();
                    adminEntry();
                    break;
                case 1:
                    customerEntry();
                    break;
                case 2:
                    adminQForm.close();
                    cataSelForm.close();
                    signIn.close();
                    return;
            }
        }
    }
    
    public void adminEntry() {
        int signInButton;
        while(true) {
            signInButton = signIn.accept();
            switch (signInButton) {
                case 0:
                    String user = signIn.readString("user");
                    String pass = signIn.readString("pass");
                    
                    if (user.equalsIgnoreCase("asher") && pass.equals("Punch")) {
                        int adminButton;
                        while (true) {
                            adminButton = cataSelForm.accept();
                            switch (adminButton) {
                                case 0:
                                    doCataArrangement arrgCata = new doCataArrangement();
                                    
                                    arrangementCatalog r = new arrangementCatalog("Arrangement Catalog", "Arrangement");
                                    arrgCata.openCataArrangement(r);
                                    break;
                                
                                case 1:
                                    doCataItem itemCata = new doCataItem();
                                    itemCatalog q = new itemCatalog("Item Catalog", "Item");
                                    itemCata.openCataItem(q);
                                    break;
                                
                                case 2:
                                    cataSelForm.hide();
                                    return;
                            }
                        }
                        
                        
                    } else {
                        signIn.writeString("msg", "invalid credentials");
                        signIn.clear("user");
                        signIn.clear("pass");
                    }
                    break;
                
                case 1:
                    signIn.hide();
                    return;
            }
        }
    }
    
    public void customerEntry() {
        int customerButton;
        while (true) {
            customerButton = cataSelForm.accept();
            switch (customerButton) {
                case 0:
                    doCataArrangement arrgCata = new doCataArrangement();
                    
                    arrangementCatalog r = new arrangementCatalog("Arrangement Catalog", "Arrangement");
                    arrgCata.customer(r);
                    break;
                
                case 1:
                    doCataItem itemCata = new doCataItem();
                    itemCatalog q = new itemCatalog("Item Catalog", "Item");
                    itemCata.customer(q);
                    break;
                
                case 2:
                    cataSelForm.hide();
                    return;
            }
        }
    }
}


