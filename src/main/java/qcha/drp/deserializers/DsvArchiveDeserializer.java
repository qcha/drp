package qcha.drp.deserializers;

import com.google.common.base.Verify;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import qcha.drp.model.DsvPreference;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Iterator for archived files, but without compression.
 */
public class DsvArchiveDeserializer implements DsvDeserializer {
    private static final Logger logger = LogManager.getLogger();

    private final DsvArchiveIterator iterator;

    public DsvArchiveDeserializer(final InputStream is, final DsvPreference preference) {
        Verify.verify(Objects.nonNull(is), "InputStream can't be null");
        Verify.verify(Objects.nonNull(preference), "Preference for Dsv resource can't be null");

        this.iterator = new DsvArchiveIterator(is, preference);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public List<String> next() {
        return iterator.next();
    }

    @Override
    public void close() throws IOException {
        iterator.close();
    }

    @NotNull
    @Override
    public Iterator<List<String>> iterator() {
        return iterator;
    }
}
