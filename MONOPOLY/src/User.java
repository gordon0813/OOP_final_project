/**
 * This is the class of User in Monopoly game.
 * 
 * @author momo, enting, catherine, sophia
 * @version 1.0
 * @since 2019-05-31
 */

public class User {
	private String name;
	private long assets;
	/**
	 * Constructor of User
	 * @param _name user's name
	 * @param _assets user's assets
	 */
	public User(String _name, long _assets) {
		this.name = new String(_name);
		this.assets = _assets;
	}

	/**
	 * Constructor of User
	 * 
	 * @param _name user's name
	 */
	public User(String _name) {
		this.name = new String(_name);
	}
	
	/**
	 * Constructor of User
	 * 
	 */
	public User() {
		this.name = "no name";
	}
	
	/**
	 * get User's name
	 * @return return the user's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * get User's Assets
	 * @return User's assets
	 */
	public long getAssets() {
		return this.assets;
	}
	
	/**
	 * set User's Assets
	 * @param newAssets new Assets
	 */
	public void setAssets(long newAssets) {
		this.assets = newAssets;
	}
}
