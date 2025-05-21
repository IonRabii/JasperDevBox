package io.neo.elk.helper;

import java.util.Map;

public class PdfRow {
    private Map<String, String> rowData;

    public PdfRow(Map<String, String> rowData)
    {
        this.rowData = rowData;
    }

    public Map<String, String> getRowData()
    {
        return rowData;
    }

    public void setRowData(Map<String, String> rowData)
    {
        this.rowData = rowData;
    }
}
