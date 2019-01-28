package com.playbuzz.automation.core;

import com.google.common.base.Preconditions;
import com.playbuzz.automation.core.annotations.DataProviderConstructor;
import com.playbuzz.automation.core.annotations.TestData;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseTest {

    /**
     * Method for providing multiple objects as a test data to the test class.
     * That will run the testNG test method sequentially for each single data object.
     *
     * @param testMethod - String, name of the test method, that is currently being run
     * @param context - ITestContext, testNG context
     * @return Object[][], single object/instance of the test model, that has test data
     */
    @DataProvider(name = "GenericDataProvider")
    public Object [][] testDataProvider(Method testMethod, ITestContext context) {
        return getDataCollection(testMethod, context);
    }

    private Object [][] getDataCollection(Method testMethod, ITestContext context) {
        Class<?>[] params = testMethod.getParameterTypes();
        String testName = context.getName();
        String methodName = testMethod.getName();
        Object[][] objects = null;
        for (int j = 0; j < params.length; j++) {
            Object[] dataCollection;
            try {

                dataCollection  = (Object[]) getTestDataFactoryMethod(params[j], TestData.class)
                        .invoke(getTestDataInstance(params[j], testName, methodName, params[j].getSimpleName()
                                        + j));
            } catch (IllegalAccessException
                    | InvocationTargetException
                    | NullPointerException e) {

                throw new RuntimeException(e.getMessage(), e.getCause());
            }
            Preconditions.checkNotNull(dataCollection);
            if (objects == null) {
                objects = new Object[dataCollection.length][params.length];
            }
            for (int i = 0; i < dataCollection.length; i++) {
                objects[i][j] = dataCollection[i];
            }
        }
        return objects;
    }

    private Method getTestDataFactoryMethod(Class<?> clazz,
                                            Class<? extends Annotation> testDataAnnotation) {
        Method[] methods = clazz.getDeclaredMethods();
        Method methodToInvoke;
        for (Method method : methods) {
            if (method.isAnnotationPresent(testDataAnnotation)) {
                methodToInvoke = method;
                return methodToInvoke;
            }
        }
        return null;
    }

    private Object getTestDataInstance(Class<?> paramClass, String testName, String methodName,
                                       String param) {
        Constructor<?>[] constructors = paramClass.getConstructors();
        Object obj;

        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(DataProviderConstructor.class)) {
                try {
                    obj = constructor.newInstance(testName, methodName, param);
                    return Preconditions.checkNotNull(obj);
                } catch (IllegalAccessException
                        | InvocationTargetException
                        | InstantiationException e) {

                    throw new RuntimeException(e.getMessage(), e.getCause());
                }
            }
        }

        return null;
    }

}
