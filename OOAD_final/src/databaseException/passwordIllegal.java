package databaseException;

public class passwordIllegal extends userException {
	public passwordIllegal () {
		super ("Password must longer than 6 and shoter than 16 (a~z or 0~9)");
	}
}
