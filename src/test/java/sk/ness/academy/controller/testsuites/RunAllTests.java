package sk.ness.academy.controller.testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import sk.ness.academy.controller.testcases.BlogControllerTest;

@Suite
@SelectClasses( {BlogControllerTest.class} )
@SuiteDisplayName("Blog service API tests")
public class RunAllTests {
}
