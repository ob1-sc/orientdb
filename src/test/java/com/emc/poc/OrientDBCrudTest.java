package com.emc.poc;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * OrientDB POC Testing and Investigation:
 *
 * @author Simon O'Brien
 */
public class OrientDBCrudTest extends BaseEECloudTest {

    /**
     * TestA to create a simple Vertex
     */
    @Test
    public void simpleVertexCreationTest() {

        OrientGraph txGraph = getTxGraph();

        String propertyName = RandomStringUtils.randomAlphanumeric(255);
        String propertyValue = RandomStringUtils.randomAlphanumeric(255);

        // create a vertex with a random property name/value
        Vertex setVertex = txGraph.addVertex(null);
        assertThat(setVertex, notNullValue());
        setVertex.setProperty(propertyName, propertyValue);

        // retrieve the vertex and check is property was correctly set
        Vertex getVertex = txGraph.getVertex(setVertex.getId());
        assertThat(getVertex, notNullValue());
        assertThat((String)getVertex.getProperty(propertyName), equalTo(propertyValue));
    }

    /**
     * TestA to create a simple Edge between two Vertices
     */
    @Test
    public void simpleEdgeCreationTest() {

        OrientGraph txGraph = getTxGraph();

        // create two vertices
        Vertex vertex1 = txGraph.addVertex(null);
        assertThat(vertex1, notNullValue());
        Vertex vertex2 = txGraph.addVertex(null);
        assertThat(vertex2, notNullValue());

        // create an edge between the vertices
        Edge setEdge = txGraph.addEdge(null, vertex1, vertex2, "tested");
        assertThat(setEdge, notNullValue());

        // retrieve the edge
        Edge getEdge = txGraph.getEdge(setEdge.getId());
        assertThat(getEdge, notNullValue());

        Vertex vertexIn = getEdge.getVertex(Direction.IN);
        assertThat(vertexIn, notNullValue());
        assertThat(vertexIn.getId(), equalTo(vertex2.getId()));

        Vertex vertexOut = getEdge.getVertex(Direction.OUT);
        assertThat(vertexOut, notNullValue());
        assertThat(vertexOut.getId(), equalTo(vertex1.getId()));
    }
}
