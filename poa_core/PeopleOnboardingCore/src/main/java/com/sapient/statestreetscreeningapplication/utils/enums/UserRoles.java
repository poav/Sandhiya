package com.sapient.statestreetscreeningapplication.utils.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum UserRoles.
 */
public enum UserRoles {
	// has to be unique prime numbers
	/** The admin. */
	ADMIN(2), 
 /** The interviewer. */
 INTERVIEWER(3), 
 /** The lead. */
 LEAD(5), 
 /** The operations. */
 OPERATIONS(7),
/** The developer. */
DEVELOPER(11);

	/** The value. */
	private int value;

	/**
	 * Instantiates a new user roles.
	 *
	 * @param value the value
	 */
	private UserRoles(int value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Checks whether a User has a particular role. It can also be checked if
	 * the user has multiple roles.
	 * 
	 * @param currentRole
	 *            The current role of the user. 'int' value.
	 * @param requiredRole
	 *            The role(s) to be checked. Can accept multiple arguments.
	 * @return True if the user has all the roles false if even one of the roles
	 *         does not match.
	 */
	public static boolean isRole(int currentRole, UserRoles... requiredRole) {
		int combinedRoles = 1;
		for (UserRoles role : requiredRole) {
			combinedRoles *= role.value;
		}
		if (currentRole > 0 && (currentRole % combinedRoles) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the combined role.
	 *
	 * @param requiredRole the required role
	 * @return the combined role
	 */
	public static int getCombinedRole(UserRoles... requiredRole) {
		int combinedRoles = 1;
		for (UserRoles role : requiredRole) {
			combinedRoles *= role.value;
		}
		return combinedRoles;
	}

	/**
	 * Adds a role to the existing role of a user.
	 * 
	 * @param currentRole
	 *            is the current role of the user
	 * @param newRole
	 *            is the role that has to be added
	 * @return the new combined role of the user
	 */
	public static int addRole(int currentRole, UserRoles newRole) {
		// Test when currentRole=0, for new users
		currentRole = currentRole <= 0 ? 1 : currentRole;
		if (currentRole % newRole.value != 0) {
			return currentRole * newRole.value;
		}
		return currentRole;
	}

	/**
	 * Remove a role from the existing roles of a user.
	 *
	 * @param currentRole            is the current role of the user
	 * @param roleToBeRemoved            is the role that has to be removed
	 * @return the new role of the user
	 */
	public static int removeRole(int currentRole, UserRoles roleToBeRemoved) {
		if (currentRole > 0 && currentRole % roleToBeRemoved.value == 0) {
			return currentRole / roleToBeRemoved.value;
		}
		return currentRole;
	}

	/**
	 * This method returns all the roles that the user belongs to as comma
	 * separated String values.
	 *
	 * @param userRole            The role of the user (int value)
	 * @return String
	 */
	public static String toString(int userRole) {
		if (userRole == 0) {
			return "No roles defined";
		}
		String roles = "";
		for (UserRoles role : UserRoles.values()) {
			if (userRole % role.value == 0) {
				roles = role.toString() + " ," + roles;
			}
		}
		roles = roles.substring(0, roles.length() - 2);
		return roles;
	}
}
