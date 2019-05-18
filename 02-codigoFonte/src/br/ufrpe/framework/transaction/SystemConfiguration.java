package br.ufrpe.framework.transaction;

public class SystemConfiguration {

	private static ThreadLocal<String> currentUser = new ThreadLocal<String>();
	private static ThreadLocal<String> currentUg = new ThreadLocal<String>();

	private static SystemConfiguration instance = new SystemConfiguration();

	private SystemConfiguration() {
	}

	public static SystemConfiguration getInstance() {
		return instance;
	}

	public void setCurrentUser(String user) {
		currentUser.set(user);
	}

	public static String getCurrentUser() {
		return (String) currentUser.get();
	}

	public void setCurrentUg(String user) {
		currentUg.set(user);
	}

	public static String getCurrentUg() {
		return (String) currentUg.get();
	}
}