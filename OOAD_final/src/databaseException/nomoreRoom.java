package databaseException;
import core.*;
public class nomoreRoom extends hotelException {
	public nomoreRoom (RoomNum order, RoomNum have) {
		super ("Not enough room!!\n[Order] Single : " + order.getSingleNum() +
			   " Double : " + order.getDoubleNum() + " Quad : " + order.getQuadNum() +
			   "\n[Left]  Single : " + have.getSingleNum() + " Double : " + 
			   have.getDoubleNum() + " Quad : " + have.getQuadNum());
	}
}
