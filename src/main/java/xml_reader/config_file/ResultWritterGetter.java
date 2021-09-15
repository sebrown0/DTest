package xml_reader.config_file;

import reporting.strategy.ResultWriter;

public interface ResultWritterGetter {
	ResultWriter getResultWriter(String testSuiteName); //testSuiteName: enclosing class name for tests.
}
