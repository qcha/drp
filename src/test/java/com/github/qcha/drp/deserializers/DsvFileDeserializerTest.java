package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.DsvPreference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class DsvFileDeserializerTest {
    private DsvFileDeserializer dsvFileDeserializer;

    @After
    public void closeResource() throws Exception {
        dsvFileDeserializer.close();
    }

    @Test
    public void parseTestFileWithTwoLines(){
        dsvFileDeserializer = new DsvFileDeserializer(getClass().getResourceAsStream("test_2_lines.csv"), new DsvPreference());

        Assert.assertEquals(dsvFileDeserializer.hasNext(), true);

        List<String> lst = dsvFileDeserializer.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value11");
        Assert.assertEquals(lst.get(1), "value12");
        Assert.assertEquals(lst.get(2), "value13");

        Assert.assertEquals(dsvFileDeserializer.hasNext(), true);

        lst = dsvFileDeserializer.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), null);
        Assert.assertEquals(lst.get(2), "value23");

        Assert.assertEquals(dsvFileDeserializer.hasNext(), false);

        lst = dsvFileDeserializer.next();

        Assert.assertEquals(Objects.isNull(lst), true);
    }
}
