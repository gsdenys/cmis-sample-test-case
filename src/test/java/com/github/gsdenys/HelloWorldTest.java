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
//init cmis server at port 8383
@RunWith(CmisInMemoryRunner.class)
@Configure(port = 8383)
public class HelloWorldTest {

    @Test
    public void getSession() throws Exception {
        HelloWorld helloWorld = new HelloWorld();
        Session session = helloWorld.getSession();

        Assert.assertNotNull("The object should not be null", session);
    }

    @Test
    public void createNewFolder() throws Exception {

        HelloWorld helloWorld = new HelloWorld();
        Session session = helloWorld.getSession();

        helloWorld.createNewFolder();

        //execute query searching by the created folder
        String query = "select * from cmis:folder where cmis:name = 'this-is-just-a-test-folder'";
        ItemIterable<QueryResult> items = session.query(query, false);

        Assert.assertEquals("The number of result should be 1", items.getPageNumItems(), 1);
    }
}