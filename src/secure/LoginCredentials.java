package secure;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import core.DBUser;
import core.User;

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
	private final int SIZE           = 8 * 3 * 64;
	private final int TOTAL_LENGTH   = SIZE;
	private final int ITERACTIONS    = 10000;
	private final int WORD_LENGTH    = 2 * SIZE / 3;
	private final int SALT_LENGTH    = TOTAL_LENGTH - WORD_LENGTH;

	/**
	 * Cached generated hash
	 */
	private byte[] generatedHash;
	
	/**
	 * cached generated hash
	 */
	private byte[] initialHash;

	/**
	 * Logging users in.
	 * 
	 * @param username
	 * @param password
	 * @return true if logged in
	 */
	public boolean logIn(String username, String password) {
		// The generated hash for the given username and password, including the new salt
		generatedHash = generateNewHash(username, password);

		// The initially saved hash on the database with the random salt.
		byte[] savedHash = getUserSavedHash(username);

		return isSamePass(username, password, savedHash);
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

	private boolean isSamePass(String username, String password, byte[] saved) {
		// They must have the same length.
		if ( generatedHash.length != saved.length ) return false;
long start = System.currentTimeMillis();		
		// Get the index where the salt starts for this user.
		int saltStart = getSeed(username, password);
		int saltEnd   = saltStart + SALT_LENGTH;
long end = System.currentTimeMillis();
System.out.println(" getSeed: " +(end-start));
		for ( int i = 0 ; i < generatedHash.length; i++ ) {
			// Before the salt, indexes must match up
			if ( i < saltStart ) if ( generatedHash[i] != saved[i] ) return false;
			else if ( i < saltEnd ) continue; // ignore the salt
			else if ( saved[i] != saved[i] ) return false;
		}
		return true;
	}

	/**
	 * Return the hashed password from the database.
	 * 
	 * @param username
	 * @return byte[]
	 */
	private byte[] getUserSavedHash(String username) {
		// Validate arguments
		if ( username == null ) throw new IllegalArgumentException("Username cannot be null.");

		DBUser db = new DBUser();
		User user = db.getUser(username);
		return user.getPassword();
	}
	
	/**
	 * Generate the final hash to be compared with the one on the database.
	 * 
	 * @param passKey List of Character
	 * @param firstHash byte[]
	 * @return byte[]
	 */
	private byte[] generateNewHash(String username, String password) { 
		// Build the final hash to be compared with the one on the database.
		byte[] firstHash = getHash(username, password);
		byte[] randomSalt = getRandomSalt();
		byte[] finalHash = new byte[TOTAL_LENGTH];

		// initial index where the salt will start
		int j = getSeed(username, password); 

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
	private int getSeed(String username, String password) {
		// Validate parameters.
		if ( username == null ) throw new IllegalArgumentException("Username cannot be null.");
		if ( password == null ) throw new IllegalArgumentException("Password cannot be null.");

		// Add the username to the password and shuffle things a bit.
		// Build the final hash to be compared with the one on the database.
		String strPassKey = "";
		List<Character> passKey = getPassKey(username, password);
		for( Character c : passKey ) strPassKey += c.toString();
		byte[] hash = getHash(username, password);

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
		return ( hashInt % WORD_LENGTH );
	}

	/**
	 * The Hash.
	 * 
	 * @param password char[]
	 * @param salt byte[]
	 * @param length int
	 * @return byte[]
	 */
	private byte[] getHash(String username, String password) { 
		// This hash should only be generated once at the first time it is called.
		if ( initialHash == null ) {
			// Add the username to the password and shuffle things a bit.
			List<Character> passKey = getPassKey(username, password);
			// Using the username as a salt
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
	 * for the cases when the hacker tries to use the Dictionary to gain access.
	 * 
	 * @param username
	 * @param password
	 * @return List of characters
	 */
	private List<Character> getPassKey(String username, String password) {
		if ( username == null ) throw new IllegalArgumentException("Username cannot be null.");
		if ( password == null ) throw new IllegalArgumentException("Password cannot be null.");
		
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
}
