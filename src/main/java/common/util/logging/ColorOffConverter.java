package common.util.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import common.util.console.ConsoleColorer;

public class ColorOffConverter extends ClassicConverter {
	
	private static final String COLOR_RESET = ConsoleColorer.restore();
	
	@Override
	public String convert(ILoggingEvent e) {
		return COLOR_RESET;
	}

}
