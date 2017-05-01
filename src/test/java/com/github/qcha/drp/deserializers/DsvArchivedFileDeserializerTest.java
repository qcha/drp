package com.github.qcha.drp.deserializers;

import com.github.qcha.drp.model.ArchiveType;
import com.github.qcha.drp.model.DsvPreference;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class DsvArchivedFileDeserializerTest {
    private DsvArchivedFileDeserializer iterator;
    private List<String> lst;

    @After
    public void tearDown() throws Exception {
        iterator.close();
    }

    @Test
    public void parseArchiveWithOnlyOneFileInside() throws Exception {
        iterator = new DsvArchivedFileDeserializer(getClass().getResourceAsStream("testArchiveWithoutCompressWithOneFiles.zip"), new DsvPreference(ArchiveType.ZIP, null));
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

    //todo fix ArchiveDsvDeserializer
    @Test
    public void parseArchiveWithTwoFilesInside() throws Exception {
        iterator = new DsvArchivedFileDeserializer(getClass().getResourceAsStream("testArchiveWithoutCompressWithTwoFiles.zip"), new DsvPreference(ArchiveType.ZIP, null));

        //first file with four string
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

        //check fourth string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), "value42");
        Assert.assertEquals(lst.get(2), null);

        //second file with two stings
        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check first string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value11");
        Assert.assertEquals(lst.get(1), "value12");
        Assert.assertEquals(lst.get(2), "value13");

        //more than two string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second file
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), null);
        Assert.assertEquals(lst.get(2), "value23");

        //more than three strings in file
        Assert.assertEquals(iterator.hasNext(), false);
    }

    @Test
    public void parseEmptyArchive() throws Exception {
        iterator = new DsvArchivedFileDeserializer(getClass().getResourceAsStream("testEmptyArchive.zip"), new DsvPreference(ArchiveType.ZIP, null));

        Assert.assertEquals(iterator.hasNext(), false);
    }

    @Test
    public void parseArchiveWithOneEmptyFile() throws Exception {
        iterator = new DsvArchivedFileDeserializer(getClass().getResourceAsStream("testArchiveWithoutCompressWithOneEmptyFile.zip"), new DsvPreference(ArchiveType.ZIP, null));

        Assert.assertEquals(iterator.hasNext(), false);
    }

    //todo fix ArchiveDsvDeserializer
    @Test
    public void parseArchiveWithOneFileAndOneEmptyFile() throws Exception {
        iterator = new DsvArchivedFileDeserializer(getClass().getResourceAsStream("testArchiveWithoutCompressWithOneFileAndOneEmptyFile.zip"), new DsvPreference(ArchiveType.ZIP, null));

        //first file
        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), false);

        //second file with two stings
        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check first string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value11");
        Assert.assertEquals(lst.get(1), "value12");
        Assert.assertEquals(lst.get(2), "value13");

        //more than two string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second file
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), null);
        Assert.assertEquals(lst.get(2), "value23");

        //more than three strings in file
        Assert.assertEquals(iterator.hasNext(), false);
    }
}
