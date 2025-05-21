package io.neo.elk.helper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws JRException
    {

        JasperDesign jasperDesign = JRXmlLoader.load(JRLoader.getResourceInputStream("list.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        JRVirtualizer virtualizer = new JRFileVirtualizer(10, tempDir.getAbsolutePath());
//        JRVirtualizer virtualizer = new DebugVirtualizer(10, tempDir.getAbsolutePath());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ROW_DATA_LIST", new TestChunkedDataSourceImpl());
//        parameters.put("ROW_DATA_LIST", new JREmptyDataSource());
        parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//        JasperExportManager.exportReportToPdfFile(jasperPrint, "list.pdf");

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("outputPdf.pdf"));
        exporter.exportReport();
//
        // Clean up
        virtualizer.cleanup();
    }
}
