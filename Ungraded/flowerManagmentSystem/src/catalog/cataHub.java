package catalog;

import BasicIO.BasicForm;

public class cataHub {
    BasicForm form;
    
    public void setForm() {
        form = new BasicForm("Arrangement Catalog", "Item Catalog", "Exit");
        
        form.setTitle("Catalog Hub");
        
        form.addTextField("desc", 50);
        form.setEditable("desc", false);
        
        form.writeString("desc", "Please select the catalog you would like to open");
    }
    
    public void select() {
        setForm();
        int button;
        while (true) {
            button = form.accept();
            switch (button) {
                case 0:
                    doCataArrangement arrgCata = new doCataArrangement();
                    
                    arrangementCatalog r = new arrangementCatalog("Arrangement Catalog", "Arrangement");
                    arrgCata.openCataArrangement(r);
                    form.hide();
                    break;
                
                case 1:
                    doCataItem itemCata = new doCataItem();
                    itemCatalog q = new itemCatalog("Item Catalog", "Item");
                    
                    itemCata.openCataItem(q);
                    form.hide();
                    break;
                    
                case 2:
                    form.close();
                    return;
            }
        }
    }
}
