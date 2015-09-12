package core.db;

import core.tables.interfaces.Access;
/**
 * The DBAccess interface.
 * 
 * @author Vasco
 *
 */
public interface DBAccess {
	/**
	 * Get an Access row by id.
	 * 
	 * @param accessId
	 * @return Access
	 */
	public Access getAccess(int accessId);

	/**
	 * Get and Access row by access code.
	 * 
	 * @param accessCd
	 * @return Access
	 */
	public Access getAccess(String accessCd);
}
