package org.mockito.testng;

import static org.mockito.internal.util.reflection.Fields.*;

import java.util.List;
import java.util.WeakHashMap;

import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.CaptorAnnotationProcessor;
import org.mockito.internal.util.reflection.Fields;
import org.mockito.internal.util.reflection.InstanceField;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;

public class MockitoBeforeTestNGMethod {

    private WeakHashMap<Object, Boolean> initializedInstances = new WeakHashMap<Object, Boolean>();

    /**
     * Initialize mocks.
     * 
     * @param method
     *            Invoked method.
     * @param testResult
     *            TestNG Test Result
     */
    public void applyFor(IInvokedMethod method, ITestResult testResult) {
	initMocks(testResult);
	reinitCaptors(method, testResult);
    }

    private void reinitCaptors(IInvokedMethod method, ITestResult testResult) {
	if (method.isConfigurationMethod()) {
	    return;
	}
	initializeCaptors(testResult.getInstance());
    }

    private void initMocks(ITestResult testResult) {
	if (alreadyInitialized(testResult.getInstance())) {
	    return;
	}
	MockitoAnnotations.initMocks(testResult.getInstance());
	markAsInitialized(testResult.getInstance());
    }

    private void initializeCaptors(Object instance) {
	List<InstanceField> instanceFields = Fields.allDeclaredFieldsOf(instance).filter(annotatedBy(Captor.class))
		.instanceFields();
	for (InstanceField instanceField : instanceFields) {
	    new CaptorAnnotationProcessor().process(instanceField.annotation(Captor.class), instanceField.jdkField());
	}
    }

    private void markAsInitialized(Object instance) {
	this.initializedInstances.put(instance, true);
    }

    private boolean alreadyInitialized(Object instance) {
	return this.initializedInstances.containsKey(instance);
    }

}