package com.emc.poc;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import org.apache.commons.lang.RandomStringUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Test for creating organisations
 *
 * @author Simon O'Brien
 */
public class OrganisationTest extends BaseEECloudTest {

    public void createOrganisation() {

        OrientGraph txGraph = getTxGraph();

        String propertyName = RandomStringUtils.randomAlphanumeric(255);
        String propertyValue = RandomStringUtils.randomAlphanumeric(255);

        // create an organisation
        Vertex newOrganisation = txGraph.addVertex("class:Organisation");
        assertThat(newOrganisation, notNullValue());



    }

}
