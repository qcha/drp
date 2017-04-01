package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.ArchiveType;
import com.github.qcha.drp.model.DsvPreference;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DsvArchivedFileDeserializerTest {
    private DsvArchivedFileDeserializer iterator;
    private List<String> lst;

    @Test
    public void parseFileArchiveWithOnlyOneFileInside() throws Exception {
        iterator = new DsvArchivedFileDeserializer(getClass().getResourceAsStream("FileTest.zip"), new DsvPreference(ArchiveType.ZIP, null));
        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check first string
        lst = iterator.next();
        Assert.assertEquals(lst.size(), 2);
        Assert.assertEquals(lst.get(0), "value1");
        Assert.assertEquals(lst.get(1), "value2");

        //more than two strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second string
        lst = iterator.next();
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value1");
        Assert.assertEquals(lst.get(1), "value2");
        Assert.assertEquals(lst.get(2), "value3");

        //more than three strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check third string
        lst = iterator.next();
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "testing");
        Assert.assertEquals(lst.get(1), "88");
        Assert.assertEquals(lst.get(2), "14");

        //more than four strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check fourth string
        lst = iterator.next();
        Assert.assertEquals(lst.size(), 2);
        Assert.assertEquals(lst.get(0), "hi-fi");
        Assert.assertEquals(lst.get(1), "sci-lab");
    }

    @Test
    public void parseFileArchiveWithTwoFilesInside() throws Exception {
        iterator = new DsvArchivedFileDeserializer(getClass().getResourceAsStream("2files.zip"), new DsvPreference(ArchiveType.ZIP, null));
        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //first file

        //check first string
        lst = iterator.next();
        Assert.assertEquals(lst.size(), 2);
        Assert.assertEquals(lst.get(0), "value1");
        Assert.assertEquals(lst.get(1), "value2");

        //more than two strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second string
        lst = iterator.next();
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value1");
        Assert.assertEquals(lst.get(1), "value2");
        Assert.assertEquals(lst.get(2), "value3");

        //more than three strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check three string
        lst = iterator.next();
        Assert.assertEquals(lst.size(), 2);
        Assert.assertEquals(lst.get(0), "test");
        Assert.assertEquals(lst.get(1), "file_1");

        //second file

        //check first string
        lst = iterator.next();
        Assert.assertEquals(lst.size(), 2);
        Assert.assertEquals(lst.get(0), "hello");
        Assert.assertEquals(lst.get(1), "world");

        //more than two strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second string
        lst = iterator.next();
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "14");
        Assert.assertEquals(lst.get(1), "testing");
        Assert.assertEquals(lst.get(2), "file_2");

        //end
        Assert.assertEquals(iterator.hasNext(), false);
    }
}
