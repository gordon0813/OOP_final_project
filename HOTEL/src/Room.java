public class Room extends RoomType {
	private boolean[] DateIsOccupied;
	Room() {
		DateIsOccupied = new boolean[190];
	}
	Room(Room _Room) {
		DateIsOccupied = new boolean[190];
		for (int i = 0; i < _Room.DateIsOccupied.length; i++)
			DateIsOccupied[i] = _Room.DateIsOccupied[i];
	}
	boolean[] getDateIsOccupied() {
		/*boolean[] nDateIsOccupied = new boolean[190];
		for (int i = 0; i < DateIsOccupied.length; i++)
			nDateIsOccupied[i] = DateIsOccupied[0];
		return nDateIsOccupied;*/
		return DateIsOccupied;
	}
	void setDateIsOccupied(int i) {
		DateIsOccupied[i] = true;
	}
}