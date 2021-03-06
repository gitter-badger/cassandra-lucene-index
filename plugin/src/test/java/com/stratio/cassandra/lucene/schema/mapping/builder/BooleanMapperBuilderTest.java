/*
 * Copyright 2014, Stratio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.cassandra.lucene.schema.mapping.builder;

import com.stratio.cassandra.lucene.schema.mapping.BooleanMapper;
import com.stratio.cassandra.lucene.schema.mapping.Mapper;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class for testing {@link BooleanMapperBuilder}.
 *
 * @author Andres de la Pena {@literal <adelapena@stratio.com>}
 */
public class BooleanMapperBuilderTest extends AbstractMapperBuilderTest {

    @Test
    public void testBuild() {
        BooleanMapperBuilder builder = new BooleanMapperBuilder().indexed(false).sorted(false);
        BooleanMapper mapper = builder.build("field");
        assertNotNull(mapper);
        assertFalse(mapper.isIndexed());
        assertFalse(mapper.isSorted());
        assertEquals("field", mapper.getName());
    }

    @Test
    public void testBuildDefaults() {
        BooleanMapperBuilder builder = new BooleanMapperBuilder();
        BooleanMapper mapper = builder.build("field");
        assertNotNull(mapper);
        assertEquals(Mapper.DEFAULT_INDEXED, mapper.isIndexed());
        assertEquals(Mapper.DEFAULT_SORTED, mapper.isSorted());
        assertEquals("field", mapper.getName());
    }

    @Test
    public void testJsonSerialization() {
        BooleanMapperBuilder builder = new BooleanMapperBuilder().indexed(false).sorted(false);
        testJsonSerialization(builder, "{type:\"boolean\",indexed:false,sorted:false}");
    }

    @Test
    public void testJsonSerializationDefaults() {
        BooleanMapperBuilder builder = new BooleanMapperBuilder();
        testJsonSerialization(builder, "{type:\"boolean\"}");
    }
}
