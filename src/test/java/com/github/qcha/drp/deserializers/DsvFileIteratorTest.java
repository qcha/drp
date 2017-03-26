package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class DsvFileIteratorTest {
    private DsvFileIterator dsvFileIterator;

    @Test
    public void parseFirstLineOfThePlainFile() throws Exception {
        dsvFileIterator = new DsvFileIterator(getClass().getResourceAsStream("FileTest.csv"), new DsvPreference());
        Assert.assertEquals(dsvFileIterator.hasNext(), true);
        final List<String> lst = dsvFileIterator.next();
        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 2);
        Assert.assertEquals(lst.get(0), "value1");
        Assert.assertEquals(lst.get(1), "value2");
    }

    @Test
    public void parseSecondLineOfThePlainFile() throws Exception {
        dsvFileIterator = new DsvFileIterator(getClass().getResourceAsStream("FileTest.csv"), new DsvPreference());
        Assert.assertEquals(dsvFileIterator.hasNext(), true);
        // skip first line
        dsvFileIterator.next();
        final List<String> lst = dsvFileIterator.next();
        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        //todo
    }
}
