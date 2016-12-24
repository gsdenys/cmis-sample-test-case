package com.github.gsdenys;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple class for a test propose
 *
 * @author Denys G. Santos (gsdenys@gmail.com)
 * @since 0.0.1
 * @version 0.0.1
 */
public class HelloWorld {

    /**
     * Get repository
     *
     * @return Session the repository session
     */
    public Session getSession() {
        Map<String, String> parameter = new HashMap<>();

        parameter.put(SessionParameter.USER, "test");
        parameter.put(SessionParameter.PASSWORD, "test");
        parameter.put(SessionParameter.ATOMPUB_URL, "http://localhost:8383/cmis/atom");
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
        parameter.put(SessionParameter.REPOSITORY_ID, "A1");

        SessionFactory factory = SessionFactoryImpl.newInstance();
        return factory.createSession(parameter);
    }


    /**
     * Create a new Folder at Repository
     */
    public void createNewFolder(){
        String name = "this-is-just-a-test-folder";

        Session session = this.getSession();

        Folder f = session.getRootFolder();

        Map<String, String> prop = new HashMap<>();
        prop.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
        prop.put(PropertyIds.NAME, name);

        Folder newFolder = f.createFolder(prop);
    }
}
