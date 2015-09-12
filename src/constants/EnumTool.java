package constants;
/**
 * List of known tools.
 * 
 * @author Vasco
 *
 */
public enum EnumTool {
	// Managing actors - create, edit, delete etc
	CLIENT_MANAGEMENT,
	CUSTOMER_MANAGEMENT,
	USER_MANAGEMENT,
	VENDOR_MANAGEMENT,

	// Managing elements - create, edit, delete etc
	BOX_MANAGEMENT,
    INGREDIENT_MANAGEMENT,
    MACHINE_MANAGEMENT,
    RECIPE_MANAGEMENT,

    // Managing orders
    RECIPE_ORDER_MANAGEMENT,
    STOCK_ORDER_MANAGEMENT,

    // Stock receive for when new stock arrives
    RECEIVE_STOCK,

    // Order Dispatchers
    RECIPE_ORDER_DISPATCHER,
    STOCK_ORDER_DISPATCHER,

    // Other tools
    DAILY_PRODUCTION,

    // Main screen
    DASHBOARD,
}
