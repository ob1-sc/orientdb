package com.emc.poc;

import com.orientechnologies.orient.core.exception.OConfigurationException;
import com.orientechnologies.orient.core.exception.OSecurityAccessException;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * OrientDB POC Testing and Investigation.
 *
 * @author Simon O'Brien
 */
public class OrientDBTest {

    public static final String DB_URL = "remote:localhost:2424/";
    public static final String DB_NAME = "testing";
    public static final String DB_USERNAME = "admin";
    public static final String DB_PASSWORD = "admin";
    public static final String DB_ROOT_USERNAME = "root";
    public static final String DB_ROOT_PASSWORD = "dev";

    /**
     * Test that user can connect to db
     */
    @Test
    public void connectionTest(){

        OrientGraph txGraph = null;

        try {

            OrientGraphFactory factory = new OrientGraphFactory(DB_URL + DB_NAME, DB_USERNAME, DB_PASSWORD);
            txGraph = factory.getTx();

            assertThat(txGraph, notNullValue());

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }

        }
    }

    /**
     * Test that root user can connect to db
     */
    @Test
    public void connectionTestRootUser(){

        OrientGraph txGraph = null;

        try {

            OrientGraphFactory factory = new OrientGraphFactory(DB_URL + DB_NAME, DB_ROOT_USERNAME, DB_ROOT_PASSWORD);
            txGraph = factory.getTx();

            assertThat(txGraph, notNullValue());

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }

        }
    }

    /**
     * Test if invalid user can connect to db
     */
    @Test(expected=OSecurityAccessException.class)
    public void connectionTestInvalidUser() {

        OrientGraphFactory factory = new OrientGraphFactory(DB_URL + DB_NAME, "invaliduser", DB_PASSWORD);
        factory.getTx();
    }

    /**
     * Test if root user can connect to db that doesn't exist
     */
    @Test(expected=OConfigurationException.class)
    public void connectionTestInvalidDB(){

        OrientGraphFactory factory = new OrientGraphFactory(DB_URL + "invaliddb", DB_ROOT_USERNAME, DB_ROOT_PASSWORD);
        factory.getTx();
    }


}
