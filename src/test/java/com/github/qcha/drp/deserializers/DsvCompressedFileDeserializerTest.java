package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.CompressType;
import com.github.qcha.drp.model.DsvPreference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class DsvCompressedFileDeserializerTest {
    private DsvCompressedFileDeserializer dsvFileDeserializer;

    @After
    public void tearDown() throws Exception {
        dsvFileDeserializer.close();
    }

    @Test
    public void parseTestFileWithTwoLines() {
        dsvFileDeserializer = new DsvCompressedFileDeserializer(getClass().getResourceAsStream("file_with_2_lines.csv.gz"), new DsvPreference(null, CompressType.GZIP));

        //is not empty
        Assert.assertEquals(dsvFileDeserializer.hasNext(), true);
        List<String> lst = dsvFileDeserializer.next();

        //check first line
        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value11");
        Assert.assertEquals(lst.get(1), "value12");
        Assert.assertEquals(lst.get(2), "value13");

        //check second line
        Assert.assertEquals(dsvFileDeserializer.hasNext(), true);
        lst = dsvFileDeserializer.next();

        //second line
        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), null);
        Assert.assertEquals(lst.get(2), "value23");

        //nothing else
        Assert.assertEquals(dsvFileDeserializer.hasNext(), false);
        Assert.assertEquals(Objects.isNull(dsvFileDeserializer.next()), true);
    }
}
