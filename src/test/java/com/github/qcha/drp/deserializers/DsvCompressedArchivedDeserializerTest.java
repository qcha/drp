package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.ArchiveType;
import com.github.qcha.drp.model.CompressType;
import com.github.qcha.drp.model.DsvPreference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

//fixme
@Ignore
public class DsvCompressedArchivedDeserializerTest {
    private DsvCompressedFileDeserializer iterator;
    private List<String> lst;

    @After
    public void tearDown() throws Exception {
        iterator.close();
    }

    @Test
    public void parseArchiveWithOnlyOneFileInside() throws Exception {
        iterator = new DsvCompressedFileDeserializer(
                getClass().getResourceAsStream("compressed_archive_with_1_file.gz"),
                new DsvPreference(ArchiveType.ZIP, CompressType.GZIP)
        );

        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check first string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value11");
        Assert.assertEquals(lst.get(1), "value12");
        Assert.assertEquals(lst.get(2), "value13");

        //more than two strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value21");
        Assert.assertEquals(lst.get(1), "value22");
        Assert.assertEquals(lst.get(2), null);

        //more than three strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check third string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value31");
        Assert.assertEquals(lst.get(1), null);
        Assert.assertEquals(lst.get(2), "value33");

        //more than four strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check forth string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), "value42");
        Assert.assertEquals(lst.get(2), null);

        //more than five strings in file
        Assert.assertEquals(iterator.hasNext(), false);
    }
}
