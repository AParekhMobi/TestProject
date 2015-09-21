package com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models;

import java.io.Serializable;

/**
 * Created by amitparekh on 18/09/15.
 */
public class DataModelSingleton implements Serializable {

    private static DataModelSingleton singletonInstance = null;
     private DataModels dataModels = new DataModels();

    public static DataModelSingleton getDataModelInstance() {
        if (singletonInstance == null) {
            singletonInstance = new DataModelSingleton();
        }
        return singletonInstance;
    }

    public DataModels getDataModels() {
        return dataModels;
    }

    public void setDataModels(DataModels dataModels) {
        this.dataModels = dataModels;
    }
}
