package minor;

public class vase {
    private String name;
    private String desc;
    private int inv;
    
    public vase(String name, String desc, int inv){
        this.name = name;
        this.desc = desc;
        this.inv = inv;
    }
    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }
    public int getInv(){
        return inv;
    }
    
    public void setInv(int inv){
        this.inv = inv;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}