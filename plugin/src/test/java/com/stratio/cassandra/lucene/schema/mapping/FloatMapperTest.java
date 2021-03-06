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
package com.stratio.cassandra.lucene.schema.mapping;

import org.apache.lucene.document.Field;
import org.apache.lucene.index.DocValuesType;
import org.apache.lucene.search.SortField;
import org.junit.Test;

import static org.junit.Assert.*;

public class FloatMapperTest {

    @Test
    public void testConstructorWithoutArgs() {
        FloatMapper mapper = new FloatMapper("field", null, null, null);
        assertEquals(Mapper.DEFAULT_INDEXED, mapper.isIndexed());
        assertEquals(Mapper.DEFAULT_SORTED, mapper.isSorted());
        assertEquals(DoubleMapper.DEFAULT_BOOST, mapper.getBoost(), 1);
    }

    @Test
    public void testConstructorWithAllArgs() {
        FloatMapper mapper = new FloatMapper("field", false, true, 2.3f);
        assertFalse(mapper.isIndexed());
        assertTrue(mapper.isSorted());
        assertEquals(2.3f, mapper.getBoost(), 1);
    }

    @Test()
    public void testSortField() {
        FloatMapper mapper = new FloatMapper("field", null, null, 2.3f);
        SortField sortField = mapper.sortField(true);
        assertNotNull(sortField);
        assertTrue(sortField.getReverse());
    }

    @Test()
    public void testValueNull() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", null);
        assertNull(parsed);
    }

    @Test()
    public void testValueString() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", "3.4");
        assertEquals(Float.valueOf(3.4f), parsed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueStringInvalid() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        mapper.base("test", "error");
    }

    @Test
    public void testValueInteger() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", 3);
        assertEquals(Float.valueOf(3), parsed);
    }

    @Test
    public void testValueLong() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", 3l);
        assertEquals(Float.valueOf(3), parsed);
    }

    @Test
    public void testValueFloatWithoutDecimal() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", 3f);
        assertEquals(Float.valueOf(3), parsed);
    }

    @Test
    public void testValueFloatWithDecimalFloor() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", 3.5f);
        assertEquals(Float.valueOf(3.5f), parsed);

    }

    @Test
    public void testValueFloatWithDecimalCeil() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", 3.6f);
        assertEquals(Float.valueOf(3.6f), parsed);

    }

    @Test
    public void testValueDoubleWithoutDecimal() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", 3d);
        assertEquals(Float.valueOf(3), parsed);
    }

    @Test
    public void testValueDoubleWithDecimalFloor() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", 3.5d);
        assertEquals(Float.valueOf(3.5f), parsed);

    }

    @Test
    public void testValueDoubleWithDecimalCeil() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", 3.6d);
        assertEquals(Float.valueOf(3.6f), parsed);

    }

    @Test
    public void testValueStringWithoutDecimal() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", "3");
        assertEquals(Float.valueOf(3), parsed);
    }

    @Test
    public void testValueStringWithDecimalFloor() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", "3.2");
        assertEquals(Float.valueOf(3.2f), parsed);
    }

    @Test
    public void testValueStringWithDecimalCeil() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        Float parsed = mapper.base("test", "3.6");
        assertEquals(Float.valueOf(3.6f), parsed);

    }

    @Test
    public void testIndexedField() {
        FloatMapper mapper = new FloatMapper("field", true, true, 1f);
        Field field = mapper.indexedField("name", 3.2f);
        assertNotNull(field);
        assertEquals(3.2f, field.numericValue());
        assertEquals("name", field.name());
        assertEquals(false, field.fieldType().stored());
    }

    @Test
    public void testSortedField() {
        FloatMapper mapper = new FloatMapper("field", true, true, 1f);
        Field field = mapper.sortedField("name", 3.2f, false);
        assertNotNull(field);
        assertEquals(DocValuesType.NUMERIC, field.fieldType().docValuesType());
    }

    @Test
    public void testSortedFieldCollection() {
        FloatMapper mapper = new FloatMapper("field", true, true, 1f);
        Field field = mapper.sortedField("name", 3.2f, true);
        assertNotNull(field);
        assertEquals(DocValuesType.NUMERIC, field.fieldType().docValuesType());
    }

    @Test
    public void testExtractAnalyzers() {
        FloatMapper mapper = new FloatMapper("field", null, null, 1f);
        String analyzer = mapper.getAnalyzer();
        assertEquals(Mapper.KEYWORD_ANALYZER, analyzer);
    }

    @Test
    public void testToString() {
        FloatMapper mapper = new FloatMapper("field", false, false, 0.3f);
        assertEquals("FloatMapper{indexed=false, sorted=false, boost=0.3}", mapper.toString());
    }
}
