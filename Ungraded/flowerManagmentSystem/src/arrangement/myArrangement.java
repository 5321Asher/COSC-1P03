package arrangement;

import BasicIO.ASCIIDataFile;
import BasicIO.ASCIIOutputFile;
import minor.myFlower;
import minor.myVase;

public class myArrangement implements arrangement {
	
	flowerNode floHead, floTail;
	vaseNode vaseHead, vaseTail;
	
	ASCIIOutputFile out;
	
	String name;
	double price;
	
	public myArrangement(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public void load(ASCIIDataFile in) {
		name = in.readString();
		price = in.readDouble();
		
		String type;
		while (!in.isEOF()) {
			try {
				type = in.readString();
				
				if (type.equalsIgnoreCase("flower")) {
					String floName = in.readString();
					String floDesc = in.readString();
					
					int floQty = in.readInt();
					int floInv = in.readInt();
					
					myFlower aflower = new myFlower(floName, floDesc, floInv, "flower");
					addFlower(aflower, floQty);
					
				} else if (type.equalsIgnoreCase("vase")) {
					// Read vase details
					String vaseName = in.readString();
					String vaseDesc = in.readString();
					
					int vaseQty = in.readInt();
					int vaseInv = in.readInt();
					
					myVase aVase = new myVase(vaseName, vaseDesc, vaseInv, "vase");
					addVase(aVase, vaseQty);
				}
			} catch (Exception e) {
				System.err.println("Error reading data: " + e.getMessage());
				e.printStackTrace();
				break;
			}
		}
	}
	
	public void save(ASCIIOutputFile out) {
		out.writeString(name);
		out.writeDouble(price);
		
		flowerNode p = floHead;
		vaseNode q = vaseHead;
		while (p.next != null) {
			if (p.qty != 0) {
				out.writeString(p.c.getName());
				out.writeString(p.c.getDescription());
				out.writeInt(p.qty);
				out.writeInt(p.c.getInv());
			}
			p = p.next;
		}
		
		while (q.next != null) {
			if (q.qty != 0) {
				out.writeString(q.c.getName());
				out.writeString(q.c.getDescription());
				out.writeInt(q.qty);
				out.writeInt(q.c.getInv());
			}
			q = q.next;
		}
	}
		
		public void list () {
			myFlower aflower;
			flowerNode fptr;
			aflower = new myFlower("zzzzzz", "zzzzzz", 0, "flower");
			floHead = new flowerNode(null, aflower, 0, null);
			aflower = new myFlower("zzzzzz", "zzzzzz", 0, "flower");
			fptr = new flowerNode(null, aflower, 0, floHead);
			floHead.prev = fptr;
			floHead = fptr;
			floTail = floHead.next;
			
			myVase aVase;
			vaseNode vptr;
			aVase = new myVase("zzzzzz", "zzzzzz", 0, "vase");
			vaseHead = new vaseNode(null, aVase, 0, null);
			aVase = new myVase("zzzzzz", "zzzzzz", 0, "vase");
			vptr = new vaseNode(null, aVase, 0, vaseHead);
			vaseHead.prev = vptr;
			vaseHead = vptr;
			vaseTail = vaseHead.next;
		}
		
		public void search(String serach) {
			flowerNode p = floHead;
			vaseNode q = vaseHead;
			
			while (p.next != null) {
				if (p.c.getName().equals(serach)) {
					System.out.println(p.c.getName());
					System.out.println(p.c.getDescription());
					System.out.println(p.c.getType());
					System.out.println("qty: " + p.qty);
					System.out.println("inv: " + p.c.getInv());
					break;
				}
				p = p.next;
			}
			
			while (q.next != null) {
				if (q.c.getName().equals(serach)) {
					System.out.println(q.c.getName());
					System.out.println(q.c.getDescription());
					System.out.println(q.c.getType());
					System.out.println("qty: " + q.qty);
					System.out.println("inv: " + q.c.getInv());
					break;
				}
				q = q.next;
			}
		}
		
		@Override public void addFlower (myFlower c,int qty){
			flowerNode ptr, add;
			ptr = floHead;
			
			while (ptr.next != null) {
				ptr = ptr.next;
			}
			
			add = new flowerNode(ptr.prev, c, qty, ptr);
			add.prev.next = add;
			ptr.prev = add;
		}
		
		@Override public void removeFlower (String search){
			if (floHead == null || floHead.next == null) {
				return;
			}
			
			if (floHead.next == floTail) {
				floHead = null;
				floTail = null;
				return;
			}
			
			flowerNode deleted = floHead;
			
			while (true) {
				if (search.equalsIgnoreCase(deleted.c.getName())) {
					break;
				}
				deleted = deleted.next;
			}
			
			flowerNode prevNode = deleted.prev;
			flowerNode nextNode = deleted.next;
			
			if (prevNode != null) {
				prevNode.next = nextNode;
			} else {
				floHead = nextNode;
			}
			
			if (nextNode != null) {
				nextNode.prev = prevNode;
			} else {
				floTail = prevNode;
			}
		}
		
		@Override public void addVase (myVase c,int qty){
			vaseNode ptr, add;
			ptr = vaseHead;
			
			while (ptr.next != null) {
				ptr = ptr.next;
			}
			
			add = new vaseNode(ptr.prev, c, qty, ptr);
			add.prev.next = add;
			ptr.prev = add;
		}
		
		@Override public void removeVase (String search){
			if (vaseHead == null || vaseHead.next == null) {
				return;
			}
			
			if (vaseHead.next == vaseTail) {
				vaseHead = null;
				vaseTail = null;
				return;
			}
			
			vaseNode deleted = vaseHead;
			
			while (true) {
				if (search.equalsIgnoreCase(deleted.c.getName())) {
					break;
				}
				deleted = deleted.next;
			}
			
			vaseNode prevNode = deleted.prev;
			vaseNode nextNode = deleted.next;
			
			if (prevNode != null) {
				prevNode.next = nextNode;
			} else {
				vaseHead = nextNode;
			}
			
			if (nextNode != null) {
				nextNode.prev = prevNode;
			} else {
				vaseTail = prevNode;
			}
		}
		
		@Override public void changeFlowerQuantity (String search,int newQty){
			flowerNode p = floHead;
			
			while (true) {
				if (p.c.getName().equalsIgnoreCase(search)) {
					p.qty = newQty;
					break;
				}
				p = p.next;
			}
		}
		
		@Override public void changeVaseQuantity (String search,int newQty){
			vaseNode p = vaseHead;
			
			while (true) {
				if (p.c.getName().equalsIgnoreCase(search)) {
					p.qty = newQty;
					break;
				}
				p = p.next;
			}
		}
		
		@Override public void setName (String newName){
			name = newName;
		}
		
		@Override public String getName () {
			return name;
		}
		
		@Override public void setPrice ( double newPrice){
			price = newPrice;
		}
		
		@Override public double getPrice () {
			return price;
		}
		
		@Override public void listItems () {
			flowerNode p = floHead;
			vaseNode q = vaseHead;
			
			while (p.next != null) {
				if (p.qty != 0) {
					System.out.println(p.c.getName() + "    " + p.qty + "    " + p.c.getInv());
				}
				p = p.next;
			}
			while (q.next != null) {
				if (q.qty != 0) {
					System.out.println(q.c.getName() + "    " + q.qty + "    " + q.c.getInv());
				}
				q = q.next;
			}
		}
	}