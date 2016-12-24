package com.github.gsdenys;

import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * A simple Test Case form {@link HelloWorld} class
 *
 * @author Denys G. Santos (gsdenys@gmail.com)
 * @since 0.0.1
 * @version 0.0.1
 */
@RunWith(CmisInMemoryRunner.class)
@Configure(port = 8383)
public class HelloWorldTest {

    @Test
    public void createNewFolder() throws Exception {

        //call create new folder method
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.createNewFolder();

        //connect with cmis
        Map<String, String> parameter = new HashMap<>();
        parameter.put(SessionParameter.USER, "test");
        parameter.put(SessionParameter.PASSWORD, "test");
        parameter.put(SessionParameter.ATOMPUB_URL, "http://localhost:8383/cmis/atom");
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
        parameter.put(SessionParameter.REPOSITORY_ID, "A1");

        SessionFactory factory = SessionFactoryImpl.newInstance();
        Session session = factory.createSession(parameter);

        //execute query searching by the created folder
        String query = "select * from cmis:folder where cmis:name = 'this-is-just-a-test-folder'";
        ItemIterable<QueryResult> items = session.query(query, false);


        Assert.assertEquals("The number of result should be 1", items.getPageNumItems(), 1);
    }

}