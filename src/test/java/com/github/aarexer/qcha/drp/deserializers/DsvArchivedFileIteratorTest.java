package com.github.aarexer.qcha.drp.deserializers;

import com.github.aarexer.qcha.drp.model.ArchiveType;
import com.github.aarexer.qcha.drp.model.DsvPreference;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DsvArchivedFileIteratorTest {
    private DsvArchivedFileIterator iterator;
    private List<String> lst;

    @Test
    public void parseFirstLineInFileArchive() throws Exception {
        iterator = new DsvArchivedFileIterator(getClass().getResourceAsStream("FileTest.zip"), new DsvPreference(ArchiveType.ZIP, null));
        lst = iterator.next();
        Assert.assertEquals(iterator.hasNext(), true);
        Assert.assertEquals(lst.size(), 2);
        Assert.assertEquals(lst.contains("value2"), true);
        Assert.assertEquals(lst.get(0), "value1");
    }
}
