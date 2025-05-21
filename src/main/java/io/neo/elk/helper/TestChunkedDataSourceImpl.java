package io.neo.elk.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestChunkedDataSourceImpl extends AbstractChunkedDataSource {
    int total = 0;
    boolean noMoreData = false;

    protected TestChunkedDataSourceImpl()
    {
        super(1000);
    }

    @Override
    List<Map<String, Object>> fetchNextChunk()
    {
        if (total == 10_000_000) {
            noMoreData = true;
            return Collections.emptyList();
        }

        List<Map<String, Object>> items = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            items.add(Map.of("c1", i, "c2", i));
        }

        total += 1000;
        System.out.println(total);

        return items;
    }

    @Override
    boolean noMoreData()
    {
        return noMoreData;
    }
}
