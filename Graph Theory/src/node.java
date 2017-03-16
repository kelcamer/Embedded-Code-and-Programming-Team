import java.util.ArrayList;

class node{
	int num;
	ArrayList<edge> edgelist;
	int target;
	public node(int n, ArrayList<edge> list){
		this.num = n;
		this.edgelist = list;
		edgelist = new ArrayList<edge>();
	}
	public node(int n){
		this.num = n;
		edgelist = new ArrayList<edge>();

	}
	public void add(edge e){
		edgelist.add(e);
	}
	public void setList(ArrayList<edge> list){
		this.edgelist = list;
	}
	public void setTarget(int nod){
		this.target = nod;
	}
	
}