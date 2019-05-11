public class User {
	private String Username;
	private String Password;
	public User() {
		Username = "";
		Password = "";
	}
	public User(String _Username, String _Password) {
		Username = _Username;
		Password = _Password;
	}
	public boolean equals(String _Username) {
		return Username.equals(_Username);
	}
}
