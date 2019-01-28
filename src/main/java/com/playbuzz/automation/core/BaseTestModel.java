package com.playbuzz.automation.core;

import com.google.gson.Gson;
import com.playbuzz.automation.core.utils.Config;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class BaseTestModel {

    protected String testName;
    protected String method;
    protected String param;

    public BaseTestModel(String testName, String method, String param) {
        this.testName = testName;
        this.method = method;
        this.param = param;
    }

    private String getFilePath(String fileName) {
        File root = new File(getBasePath());
        Collection<File> files = FileUtils.listFiles(root, null, true);
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                return file.getAbsolutePath();
            }
        }
        return (getBasePath() + fileName);
    }

    private String getBasePath() {
        return new File(Config.create().getTestDataPath()).getAbsolutePath() + "/";
    }

    /**
     * Initializes the object instance located in the heap and represented with the this reference
     * with the data located in the JSON file, using third party library GSON (Deserialization).
     */
    private <T> T deserialize(String fileName, Class<T> clazz) {
        Gson gson = new Gson();
        T object;
        try {
            object = gson.fromJson(com.playbuzz.automation.core.utils.FileUtils.readFile(getFilePath(fileName)), clazz);
        } catch (Exception e) {
            serialize(fileName);
            throw new RuntimeException("Can not read test data from file", e);
        }
        return object;
    }

    protected <T> T deserialize(Class<T> clazz) {
        return deserialize(testName + "_" + method + "_" + param + ".json", clazz);
    }

    /**
     * Serializes object instance located in the heap and represented with the this reference
     * into the JSON file, using third party library GSON.
     */
    private void serialize(String fileName) {
        File file = new File(getBasePath() + fileName);
        try {
            Gson gson = new Gson();
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException(file.getAbsolutePath()
                            + " could not be created!");
                }
            }
            com.playbuzz.automation.core.utils.FileUtils.writeFile(getBasePath() + fileName, gson.toJson(this));
        } catch (IOException e) {
            throw new RuntimeException("Can not create file for test data", e);
        }
    }

}
