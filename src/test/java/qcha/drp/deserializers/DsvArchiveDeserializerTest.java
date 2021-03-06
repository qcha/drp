package qcha.drp.deserializers;

import qcha.drp.model.ArchiveType;
import qcha.drp.model.DsvPreference;
import com.google.common.base.VerifyException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class DsvArchiveDeserializerTest {
    private DsvArchiveDeserializer iterator;
    private List<String> lst;

    @After
    public void tearDown() throws Exception {
        if (Objects.nonNull(iterator)) {
            iterator.close();
        }
    }

    @Test
    public void parseArchiveWithOnlyOneFileInside() throws Exception {
        iterator = new DsvArchiveDeserializer(
                getClass().getResourceAsStream("non_compress_archive_with_1_file.zip"),
                new DsvPreference(ArchiveType.ZIP, null)
        );

        //is not empty
        Assert.assertEquals(iterator.hasNext(), true);

        //check first string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value11");
        Assert.assertEquals(lst.get(1), "value12");
        Assert.assertEquals(lst.get(2), "value13");

        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value21");
        Assert.assertEquals(lst.get(1), "value22");
        Assert.assertEquals(lst.get(2), null);

        //more than two strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check third string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value31");
        Assert.assertEquals(lst.get(1), null);
        Assert.assertEquals(lst.get(2), "value33");

        //more than three strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check fourth string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), "value42");
        Assert.assertEquals(lst.get(2), null);

        //more than five strings in file
        Assert.assertEquals(iterator.hasNext(), false);
    }

    @Test
    public void parseArchiveWithTwoFilesInside() throws Exception {
        iterator = new DsvArchiveDeserializer(
                getClass().getResourceAsStream("non_compress_archive_with_2_files.zip"),
                new DsvPreference(ArchiveType.ZIP, null)
        );

        //first file with two stings
        //is not empty
        Assert.assertEquals(iterator.hasNext(), true);

        //check first string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value11");
        Assert.assertEquals(lst.get(1), "value12");
        Assert.assertEquals(lst.get(2), "value13");

        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), null);
        Assert.assertEquals(lst.get(2), "value23");

        //check second file
        //is not empty
        Assert.assertEquals(iterator.hasNext(), true);

        //check first string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value11");
        Assert.assertEquals(lst.get(1), "value12");
        Assert.assertEquals(lst.get(2), "value13");

        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value21");
        Assert.assertEquals(lst.get(1), "value22");
        Assert.assertEquals(lst.get(2), null);

        //more than two strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check third string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value31");
        Assert.assertEquals(lst.get(1), null);
        Assert.assertEquals(lst.get(2), "value33");

        //more than three strings in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check fourth string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), "value42");
        Assert.assertEquals(lst.get(2), null);

        //more than four strings in file
        Assert.assertEquals(iterator.hasNext(), false);
    }

    @Test
    public void parseEmptyArchive() throws Exception {
        iterator = new DsvArchiveDeserializer(
                getClass().getResourceAsStream("empty_archive.zip"),
                new DsvPreference(ArchiveType.ZIP, null)
        );

        Assert.assertEquals(iterator.hasNext(), false);
    }

    @Test
    public void parseArchiveWithOneEmptyFile() throws Exception {
        iterator = new DsvArchiveDeserializer(
                getClass().getResourceAsStream("non_compress_archive_with_1_empty_file.zip"),
                new DsvPreference(ArchiveType.ZIP, null)
        );

        Assert.assertEquals(iterator.hasNext(), false);
    }

    @Test
    public void parseArchiveWithOneFileAndOneEmptyFile() throws Exception {
        iterator = new DsvArchiveDeserializer(
                getClass().getResourceAsStream("non_compress_archive_with_1_empty_file_and_1_non_empty_file.zip"),
                new DsvPreference(ArchiveType.ZIP, null)
        );

        //it not empty
        Assert.assertEquals(iterator.hasNext(), true);

        //check first string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), "value11");
        Assert.assertEquals(lst.get(1), "value12");
        Assert.assertEquals(lst.get(2), "value13");

        //more than one string in file
        Assert.assertEquals(iterator.hasNext(), true);

        //check second string
        lst = iterator.next();

        Assert.assertEquals(Objects.isNull(lst), false);
        Assert.assertEquals(lst.size(), 3);
        Assert.assertEquals(lst.get(0), null);
        Assert.assertEquals(lst.get(1), null);
        Assert.assertEquals(lst.get(2), "value23");

        //more than three strings in file
        Assert.assertEquals(iterator.hasNext(), false);
    }

    @Test(expected = VerifyException.class)
    public void createIteratorWithNullInputStream() throws Exception {
        iterator = new DsvArchiveDeserializer(
                null,
                new DsvPreference(ArchiveType.ZIP, null)
        );
    }

    @Test(expected = VerifyException.class)
    public void createIteratorWithNullDsvPrefetences() throws Exception {
        iterator = new DsvArchiveDeserializer(
                getClass().getResourceAsStream("non_compress_archive_with_1_empty_file_and_1_non_empty_file.zip"),
                null
        );
    }
}
