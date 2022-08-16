package io.agileinfra.dsched.scheduler.e2e.tests;

import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasspathResource("features")
public class E2ESchedulerCucumberTest {}
