package xml_reader;

import reporting.strategy.ResultWriter;

public interface ResultWritterGetter {
	ResultWriter getResultWriter(String testSuiteName); //testSuiteName: enclosing class name for tests.
}
