import com.google.gson.annotations.SerializedName;

public class RoomType {
	@SerializedName("RoomType")
	private String Type;
	@SerializedName("RoomPrice")
	private int Price;
	private int Number;
	RoomType() {
		Type = "Single";
		Price = 0;
		Number = 0;
	}
	RoomType(String _Type, int _Price, int _Number) {
		Type = _Type;
		Price = _Price;
		Number = _Number;
	}	
	RoomType(RoomType _RoomType) {
		Type = _RoomType.Type;
		Price = _RoomType.Price;
		Number = _RoomType.Number;
	}
	public String getType() {
		return Type;
	}
	public int getPrice() {
		return Price;
	}
	public int getNumber() {
		return Number;
	}
	public String toString() {
		return "[" + Type + " " + Price + " " + Number + "]";
	}
}