package com.tanasobbartar.file.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.type.RunDirectionEnum;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import java.nio.file.Path;

public class ExcelReport {

    static void generateExcelReport(JasperPrint JasperPrint, Path reportStoragePath) throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(JasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportStoragePath.toString()));
        SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
        config.setDetectCellType(true);
        config.setRemoveEmptySpaceBetweenRows(true);
        config.setWhitePageBackground(false);
        config.setSheetDirection(RunDirectionEnum.RTL);
        exporter.setConfiguration(config);
        exporter.exportReport();
    }

}
