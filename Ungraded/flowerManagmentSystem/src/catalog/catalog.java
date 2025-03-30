package catalog;

public interface catalog<T> {
    
    public String getName();
    
    public void setName(String newName);
    
    public String getType();
    
    public void setType(String newType);
    
    public void load();
    
    public void save();
    
    public void listStart();
    
    public T search(String search);
    
    public void addObject(T object);
    
    public void removeObject(String delete);
    
    public T getCurrent();
    
    public T up();
    
    public T down();
    
    public T getFirstObject();
    
    public void list();
}