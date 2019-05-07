
public class Location {
	String name; //the location name
	int price;//the price of the location
	int fee;// the fee of the location
	int xcoordinate;// store the x coordinate
	int ycoordinate;// store the y coordinate
	int owner;// the owner who owns the location = -1, 0, 1, 2, 3. (-1 means no one owns it. 0,1,2,3 means player)
	String iconpath;//the stored path of the icon
	boolean canbuy;//whether the location can be bought or not. For example, library, start...etc cannot be bought

	public Location() {
		name = " ";
		price = 0;
		fee = 0;
		xcoordinate = 0;
		yccordinate = 0;
		owner = -1;
		iconpath = " ";
		canbuy = true;
	}

	public Location(String name,int price, int x, int y, String iconpath, boolean a) {
		this.name = name;
		this.price = price;
		this.fee = price / 2;
		this.xcoordinate = x;
		this.ycoordinate = y;
		this.owner = -1;
		this.iconpath = iconpath;
		this.canbuy = a;
	}

}
