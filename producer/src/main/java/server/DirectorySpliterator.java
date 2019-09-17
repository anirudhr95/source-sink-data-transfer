package server;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectorySpliterator implements Spliterator<Path> {
    Iterator<Path> iterator;
    long est;

    private DirectorySpliterator(Iterator<Path> iterator, long est) {
        this.iterator = iterator;
        this.est = est;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Path> action) {
        if (iterator == null) {
            return false;
        }
        Path path;
        try {
            synchronized (iterator) {
                if (!iterator.hasNext()) {
                    iterator = null;
                    return false;
                }
                path = iterator.next();
            }
        } catch (DirectoryIteratorException e) {
            throw new UncheckedIOException(e.getCause());
        }
        action.accept(path);
        return true;
    }

    @Override
    public Spliterator<Path> trySplit() {
        if (iterator == null || est == 1)
            return null;
        long e = this.est >>> 1;
        this.est -= e;
        return new DirectorySpliterator(iterator, e);
    }

    @Override
    public long estimateSize() {
        return est;
    }

    @Override
    public int characteristics() {
        return DISTINCT | NONNULL;
    }

    public static Stream<Path> list(Path parent) throws IOException {
        DirectoryStream<Path> ds = Files.newDirectoryStream(parent);
        int splitSize = Runtime.getRuntime().availableProcessors() * 20;
        DirectorySpliterator spltr = new DirectorySpliterator(ds.iterator(), splitSize);
        return StreamSupport.stream(spltr, false).onClose(() -> {
            try {
                ds.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
}
