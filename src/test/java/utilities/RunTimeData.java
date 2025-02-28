package utilities;

import java.util.Optional;

public class RunTimeData {

	private static final ThreadLocal<ScenarioContext> scenarioContextThreadLocal = ThreadLocal.withInitial(ScenarioContext::new);

	public static void setData(String key, Object value) {
	    scenarioContextThreadLocal.get().setData(key, value);
	}

	// Directly return the value instead of using Optional
	public static Object getData(String key) {
	    return scenarioContextThreadLocal.get().getData(key);
	}

	public static void clearScenarioContext() {
	    scenarioContextThreadLocal.remove(); // Fully removes the instance from ThreadLocal
	}

	public static void emptyDataMap() {
	    clearScenarioContext(); // Ensures all scenario data is removed
	}
}
