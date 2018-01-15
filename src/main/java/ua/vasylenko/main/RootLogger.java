package ua.vasylenko.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
* Root logger class for saving instance "global" logger.
* @Created by Asus on 13.01.2018
* @version 1.0
*/
public class RootLogger {
	private static final Logger ROOT_LOGGER = LogManager.getRootLogger();
	
	public static Logger getRootLogger() {
		return ROOT_LOGGER;
	}
}
