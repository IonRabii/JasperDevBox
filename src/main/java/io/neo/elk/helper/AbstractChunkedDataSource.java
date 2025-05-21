package io.neo.elk.helper;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractChunkedDataSource implements JRDataSource {
    private final int chunkSize;
    private Iterator<Map<String, Object>> iterator;
    private Map<String, Object> currentItem;

    protected AbstractChunkedDataSource(int chunkSize)
    {
        this.chunkSize = chunkSize;
        iterator = fetchNextChunk().iterator(); // first page
    }

    abstract List<Map<String, Object>> fetchNextChunk();

    abstract boolean noMoreData();

    @Override
    public boolean next() throws JRException
    {
        if (iterator == null || noMoreData()) {
            return false;
        }

        if (iterator.hasNext()) {
            currentItem = iterator.next();
            return true;
        } else {
            iterator = fetchNextChunk().iterator();
            return next();
        }
    }

    @Override
    public Object getFieldValue(JRField jrField)
    {
        if ("rowData".equals(jrField.getName())) {
            return currentItem;
        } else {
            return null;
        }
    }
}
