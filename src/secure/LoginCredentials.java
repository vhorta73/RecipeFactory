package secure;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import mySQL.ConnectDB;
import web.impl.SessionImpl;
import web.interfaces.Session;
import core.UserPrivilege;
import core.UserPrivilegeImpl;
import core.db.impl.DBUserImpl;
import core.db.interfaces.DBUser;
import core.tables.interfaces.User;

/**
 * Login Credentials manager.
 * 
 * @author Vasco
 *
 */
public class LoginCredentials {
	/**
	 * ***************************** WARNING !!! *************************
	 * Changing any of the following will result in all passwords to fail.
	 * ***************************** WARNING !!! *************************
	 */
	private final String PBKDF2_SHA1 = "PBKDF2WithHmacSHA1";
	private final String SPECIAL_SALT_CHARS = "@£";
	private final int SIZE           = 8 * 6 * 64;
	private final int TOTAL_LENGTH   = SIZE;
	private final int ITERACTIONS    = 10000;
	private final int WORD_LENGTH    = 5 * SIZE / 6;
	private final int SALT_LENGTH    = TOTAL_LENGTH - WORD_LENGTH;

	/**
	 * The Session to be.
	 */
	private Session session;

	/**
	 * The temporary session to be used internally only.
	 */
	private Session temporarySession;
	
	/**
	 * Cached generated hash
	 */
	private byte[] generatedHash;
	
	/**
	 * cached generated initial hash
	 */
	private byte[] initialHash;
	
	/**
	 * generated salt starting index in the hash.
	 */
	private int saltStartIndext;
	
	/**
	 * The tired username
	 */
	private String username;
	
	/**
	 * The tried password
	 */
	private String password;
	
	/**
	 * The User data from the database
	 */
	private User user;

	/**
	 * The User Privilege data from the database
	 */
	private UserPrivilege userPrivilege;

	/**
	 * Logging users in.
	 * This is the one and only entry point in the code to check if the username
	 * and password supplied do match up.
	 * 
	 * @param username
	 * @param password
	 */
	public void logIn(String username, String password) {
		// Validate input
		if (username == null) throw new IllegalArgumentException("Username cannot be null");
		if (password == null) throw new IllegalArgumentException("Password cannot be null");
		
		// Keep these at bay
		this.username = username;
		this.password = password;
		this.temporarySession = new SessionImpl();
		this.temporarySession.setDB(new ConnectDB());
		this.temporarySession.setLoggedIn(true);
		this.session = new SessionImpl();
		
		// Lets load the user data.. maybe there is no user...
		loadUser();

		// The generated hash for the given username and password, including the new salt
		generatedHash = generateNewHash();

		// Set the session logging status to the outcome of this action.
		this.session.setLoggedIn(isSamePass(user.getPassword()));

		// Grant DB access only if logged in.
        if ( this.session.isLoggedIn() ) {
        	// Grant DB access
        	this.session.setDB(new ConnectDB());
        	
        	// Set the user to the session.
        	this.session.setUser(user);
        	
        	// Load all user privileges.
    		loadUserPrivileges();
    		this.session.setUserPrivileges(userPrivilege);
        }
	}

	/**
	 * The logic to save the password for this user must be set outside.
	 * It will only return a generatedHash if username and passwords were
	 * previously attempted.
	 * 
	 * @return
	 */
	public byte[] getHash() {
		return generatedHash;
	}

	/**
	 * Compare the given hash with the generated and return true if match.
	 * The salt point is calculated and ignores throughout its length 
	 * as it is totally random generated.
	 * 
	 * @param saved
	 * @return true if match
	 */
	private boolean isSamePass(byte[] saved) {
		// They must have the same length.
		if ( generatedHash.length != saved.length ) return false;

		// Get the index where the salt starts for this user.
		int saltStart = getSaltStartIndex();
		int saltEnd   = saltStart + SALT_LENGTH;

		for ( int i = 0 ; i < generatedHash.length; i++ ) {
			// Before the salt, indexes must match up
			if ( i < saltStart ) if ( generatedHash[i] != saved[i] ) return false;
			else if ( i < saltEnd ) continue; // ignore the salt
			else if ( saved[i] != saved[i] ) return false;
		}
		return true;
	}

	/**
	 * Load User data
	 */
	private void loadUser() {
		// Get the db User handler for the user supplied at log in time.
		DBUser db = new DBUserImpl(this.temporarySession, username);
		user = db.getUser();

		// End problems here
		if ( user == null ) throw new IllegalArgumentException("User not found."); 
	}
	
	/**
	 * Load User Privilege data
	 */
	private void loadUserPrivileges() {
		// Get the UserPrivilege details after a successful log in.
		userPrivilege = new UserPrivilegeImpl(this.session);

		// End problems here
		if ( userPrivilege == null ) throw new IllegalArgumentException("User Privileges given."); 		
	}
	
	/**
	 * Generate the final hash to be compared with the one on the database.
	 * 
	 * @return byte[]
	 */
	private byte[] generateNewHash() { 
		// Build the final hash to be compared with the one on the database.
		byte[] firstHash = getGeneratedHash();
		byte[] randomSalt = getRandomSalt();
		byte[] finalHash = new byte[TOTAL_LENGTH];

		// initial index where the salt will start
		int j = getSaltStartIndex(); 

		for( int i = 0; i < TOTAL_LENGTH; i++ ) {
			// First index for the salt is not yet here
			if ( j > i ) finalHash[i] = firstHash[i];
			else {
				if ( (i - j) < SALT_LENGTH ) finalHash[i] = randomSalt[i-j];
				else finalHash[i] = firstHash[i-SALT_LENGTH];
			}
		}
		return finalHash;
	}
	
	/**
	 * Generate the random salt of specified length.
	 * 
	 * @return byte[]
	 */
	private byte[] getRandomSalt() {
		byte[] randomSalt = new byte[SALT_LENGTH];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(randomSalt);
		return randomSalt;
	}

	/**
	 * Generate a seed int from username and given hash, which will be 
	 * the same for the same username and hash, but totally random if
	 * username changes.
	 * 
	 * @param hash
	 * @param passKey String
	 * @return int seed
	 */
	private int getSaltStartIndex() {
		// Add the username to the password and shuffle things a bit.
		// Build the final hash to be compared with the one on the database.
		String strPassKey = "";
		List<Character> passKey = getPassKey();
		for( Character c : passKey ) strPassKey += c.toString();
		byte[] hash = getGeneratedHash();

		// Get int value for the middle char in username ensuring it is positive.
		int passIndex = (int)strPassKey.length()/2;
		Character passChar = strPassKey.charAt(passIndex);
		int passMidInt = Math.abs(Character.getNumericValue(passChar));
		// Trim the int value to the word length using mod.
		int hashIndex    = passMidInt % WORD_LENGTH;

		// Get the absolute value from the hash char at hashIndex.
		char hashChar = (char) hash[hashIndex];
		int hashInt = (int)hashChar;

		// Return the value within word length to be the seed.
		saltStartIndext = ( hashInt % WORD_LENGTH );
		return saltStartIndext;
	}

	/**
	 * The generated hash.
	 * 
	 * @return byte[]
	 */
	private byte[] getGeneratedHash() { 
		// Use cached hash if exists.
		if ( initialHash == null ) {
			// Get the new username mingled with the password.
			List<Character> passKey = getPassKey();
			
			// Add the the username some special characters to make it more difficult.
			byte[] passSalt = (username + SPECIAL_SALT_CHARS).getBytes(); 
			char[] key = new char[passKey.size()];
			for( int i = 0; i < passKey.size(); i++ ) key[i] = passKey.get(i);

			PBEKeySpec c = new PBEKeySpec(key,passSalt,ITERACTIONS,8 * WORD_LENGTH);
			SecretKeyFactory skf;
			try {
				skf = SecretKeyFactory.getInstance(PBKDF2_SHA1);
				initialHash = skf.generateSecret(c).getEncoded();
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
				return null;
			}
		}
		return initialHash;
	}
	
	/**
	 * This aims to make any readable password and username less readable,
	 * for the cases when the hacker tries to use Dictionary words to gain access.
	 * 
	 * @return List of characters
	 */
	private List<Character> getPassKey() {
		List<Character> key = new LinkedList<Character>();
		List<Character> initial = new LinkedList<Character>();

		for( char c : username.toCharArray() ) initial.add(c);
		for( char c : password.toCharArray() ) initial.add(c);

		for ( ; initial.size() > 0 ; ) {
			// Add the first
			key.add(initial.remove(0));
			// Add the last
			if ( initial.size() > 0 ) key.add(initial.remove(initial.size()-1));
			// Add the middle one
			if ( initial.size() > 1 ) key.add(initial.remove((int)initial.size()/2));
		}
		return key;
	}

	/**
	 * The loaded session.
	 * 
	 * @return
	 */
	public Session getSession() {
		return session;
	}
}