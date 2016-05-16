package com.emc.poc;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import org.testng.annotations.BeforeSuite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Base EE Cloud Test that handles setting up required Edge/Vertex Types
 *
 * @author Simon O'Brien
 */
public class BaseEECloudTest extends BaseOrientDBTest {

    @BeforeSuite
    public void setupTypes() {

        // get a non-transactional graph
        OrientGraphFactory factory = new OrientGraphFactory(SERVER_URL + getDbName(), DB_USERNAME, DB_PASSWORD);
        OrientGraphNoTx graph = factory.getNoTx();

        try {

            // create Vertex Types
            graph.createVertexType("Person");
            assertThat(graph.getVertexType("Person"), notNullValue());

            graph.createVertexType("Discipline");
            assertThat(graph.getVertexType("Discipline"), notNullValue());

            graph.createVertexType("Organisation");
            assertThat(graph.getVertexType("Organisation"), notNullValue());

            graph.createVertexType("Document");
            assertThat(graph.getVertexType("Document"), notNullValue());

            // create Edge Types
            graph.createEdgeType("member-of");
            assertThat(graph.getEdgeType("member-of"), notNullValue());

            graph.createEdgeType("has-access-to");
            assertThat(graph.getEdgeType("has-access-to"), notNullValue());

        } finally {

            if(graph != null) {
                graph.shutdown();
            }
        }
    }

}
