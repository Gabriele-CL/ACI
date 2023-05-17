package com.aci.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

    public static Properties getPropertyObject() throws IOException{
        FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"//src/test/resources/config.properties");

        Properties prop = new Properties();
        prop.load(fp);

        return prop;
    }

    public static String getUrl() throws IOException {
        return getPropertyObject().getProperty("url");
    }

    public static String getUsername() throws IOException{
        return getPropertyObject().getProperty("username");
    }

    public static String getPassword() throws IOException{
        return getPropertyObject().getProperty("password");
    }

    public static String getUsername2() throws IOException{
        return getPropertyObject().getProperty("username2");
    }

    public static String getPassword2() throws IOException{
        return getPropertyObject().getProperty("password2");
    }

    public static String getUsername3() throws IOException{
        return getPropertyObject().getProperty("username3");
    }

    public static String getPassword3() throws IOException{
        return getPropertyObject().getProperty("password3");
    }

    public static String getUsername4() throws IOException{
        return getPropertyObject().getProperty("username4");
    }

    public static String getPassword4() throws IOException{
        return getPropertyObject().getProperty("password4");
    }
}
