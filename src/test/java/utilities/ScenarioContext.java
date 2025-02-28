package utilities;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ScenarioContext {

	/*
	 * The ScenarioContext class is used to store and manage data that is shared across different steps in a test scenario. 
	 * This is particularly useful in automated testing frameworks like Cucumber, 
	 * where you may need to pass data between different step definitions
	 * Data Sharing: It allows sharing of data between different steps in a scenario without using global variables.
Thread Safety: Using a ConcurrentHashMap ensures that the data is thread-safe, which is important when running tests in parallel.
Encapsulation: It encapsulates the scenario-specific data, making the code cleaner and more maintainable.
Flexibility: It provides a flexible way to store and retrieve data using key-value pairs.
	 */
	
	private final Map<String, Object> scenarioData = new ConcurrentHashMap<>();

	public void setData(String key, Object value) {
		scenarioData.put(key, value);
	}

	// Return the value directly (null if not present)
	public Object getData(String key) {
		return scenarioData.get(key);
	}

	public void clear() {
		scenarioData.clear();
	}
	    
}
