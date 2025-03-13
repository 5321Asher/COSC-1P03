/**
 * @(#)node.java
 *
 *
 * @author 
 * @version 1.00 2010/2/24
 */


class node {
	protected node  prev;
	protected Contact c;
	protected node next;
	
    public node(node p, Contact c, node n) {
    	prev = p;
    	this.c = c;
    	next = n;   	
    }
       
}