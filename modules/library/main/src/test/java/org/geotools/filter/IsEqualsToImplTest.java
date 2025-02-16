/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.filter;

import java.math.BigDecimal;
import org.geotools.api.filter.PropertyIsEqualTo;
import org.geotools.api.filter.expression.Expression;
import org.geotools.factory.CommonFactoryFinder;
import org.junit.Assert;
import org.junit.Test;

public class IsEqualsToImplTest {

    org.geotools.api.filter.FilterFactory filterFactory =
            CommonFactoryFinder.getFilterFactory(null);

    @Test
    public void testOperandsSameType() {
        Expression e1 = filterFactory.literal(1);
        Expression e2 = filterFactory.literal(1);

        PropertyIsEqualTo equal = filterFactory.equals(e1, e2);
        Assert.assertTrue(equal.evaluate(null));
    }

    @Test
    public void testOperandsShort() {
        Expression literalShort42 = filterFactory.literal((short) 42);
        Expression literalString42 = filterFactory.literal("42");
        Expression literalDouble42 = filterFactory.literal(42.0);
        Expression literalLong42 = filterFactory.literal((long) 42);
        Expression literalFloat42 = filterFactory.literal((float) 42);
        Expression literalBig42 = filterFactory.literal(new BigDecimal(42));

        Assert.assertTrue(filterFactory.equals(literalShort42, literalShort42).evaluate(null));
        Assert.assertTrue(filterFactory.equals(literalShort42, literalString42).evaluate(null));
        Assert.assertTrue(filterFactory.equals(literalShort42, literalDouble42).evaluate(null));
        Assert.assertTrue(filterFactory.equals(literalShort42, literalLong42).evaluate(null));
        Assert.assertTrue(filterFactory.equals(literalShort42, literalFloat42).evaluate(null));
        Assert.assertTrue(filterFactory.equals(literalShort42, literalBig42).evaluate(null));
        Assert.assertTrue(filterFactory.equals(literalShort42, literalDouble42).evaluate(null));
    }

    @Test
    public void testOperandsIntString() {
        Expression e1 = filterFactory.literal(1);
        Expression e2 = filterFactory.literal("1");

        PropertyIsEqualTo equal = filterFactory.equals(e1, e2);
        Assert.assertTrue(equal.evaluate(null));
    }

    @Test
    public void testOperandsIntFloatString() {
        Expression e1 = filterFactory.literal(1);
        Expression e2 = filterFactory.literal("1.2");

        PropertyIsEqualTo equal = filterFactory.equals(e1, e2);
        Assert.assertFalse(equal.evaluate(null));
    }

    @Test
    public void testOperandsLongInt() {
        Expression e1 = filterFactory.literal(1);
        Expression e2 = filterFactory.literal(1l);

        PropertyIsEqualTo equal = filterFactory.equals(e1, e2);
        Assert.assertTrue(equal.evaluate(null));
    }

    @Test
    public void testOperandsFloatInt() {
        Expression e1 = filterFactory.literal(1.0f);
        Expression e2 = filterFactory.literal(1);

        PropertyIsEqualTo equal = filterFactory.equals(e1, e2);
        Assert.assertTrue(equal.evaluate(null));
    }

    @Test
    public void testOperandsDoubleLong() {
        Expression e1 = filterFactory.literal(1.0);
        Expression e2 = filterFactory.literal(1l);

        PropertyIsEqualTo equal = filterFactory.equals(e1, e2);
        Assert.assertTrue(equal.evaluate(null));
    }

    @Test
    public void testOperandsDoubleLongOutOfRange() {
        Expression e1 = filterFactory.literal(Long.MAX_VALUE + 10000d);
        Expression e2 = filterFactory.literal(Long.MAX_VALUE);

        PropertyIsEqualTo equal = filterFactory.equals(e1, e2);
        Assert.assertFalse(equal.evaluate(null));
    }

    @Test
    public void testCaseSensitivity() {
        Expression e1 = filterFactory.literal("foo");
        Expression e2 = filterFactory.literal("FoO");

        PropertyIsEqualTo caseSensitive = filterFactory.equal(e1, e2, true);
        Assert.assertFalse(caseSensitive.evaluate(null));

        PropertyIsEqualTo caseInsensitive = filterFactory.equal(e1, e2, false);
        Assert.assertTrue(caseInsensitive.evaluate(null));
    }
}
