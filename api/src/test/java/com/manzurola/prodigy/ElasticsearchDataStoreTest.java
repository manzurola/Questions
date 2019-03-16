package com.manzurola.prodigy;

import com.manzurola.prodigy.common.DataStore;
import com.manzurola.prodigy.common.ElasticsearchDataStore;
import com.manzurola.prodigy.common.Entity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ElasticsearchDataStoreTest {

    @Autowired
    private DataStore dataStore;

    @Autowired
    private ElasticsearchDataStore.Mappings mappings;


    @Test
    public void add() {
        dataStore.add(new DummyEntity("bla bla"));
    }

    @Test
    public void get() {
        dataStore.add(new DummyEntity("bla bla"));
    }

    @Test
    public void indexMapping() {
        String indexName = mappings.indexOf(DummyEntity.class);
        Assert.assertEquals("dummy_entity",indexName);
    }

    @Test
    public void mappingTypeMapping() {
        String indexName = mappings.mappedTypeOf(DummyEntity.class);
        Assert.assertEquals("dummy_entity",indexName);
    }

    public class DummyEntity extends Entity {
        private final String testData;

        public DummyEntity(String testData) {
            this.testData = testData;
        }

        public String getTestData() {
            return testData;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DummyEntity)) return false;
            DummyEntity that = (DummyEntity) o;
            return Objects.equals(testData, that.testData);
        }

        @Override
        public int hashCode() {
            return Objects.hash(testData);
        }

        @Override
        public String toString() {
            return "DummyDomainObject{" +
                    "testData='" + testData + '\'' +
                    '}';
        }
    }
}
