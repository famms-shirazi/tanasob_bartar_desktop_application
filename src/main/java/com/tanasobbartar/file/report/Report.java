package com.tanasobbartar.file.report;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component
@AllArgsConstructor
public class Report {

    private final DataSource dataSource;


    public void generateReport(Path mainReportPath, Path subReportPath, Path reportStoragePath, Map<String, Object> params) throws JRException, SQLException {
        try(Connection conn = dataSource.getConnection()){
            JasperReport mainReport = JasperCompileManager.compileReport(mainReportPath.toString());
            JasperReport subReport = JasperCompileManager.compileReport(subReportPath.toString());
            params.put("SUB_REPORT", subReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, params, conn);
            ExcelReport.generateExcelReport(jasperPrint, reportStoragePath);
        }
    }

    public void generateReport(Path mainReportPath, Path reportStoragePath, Map<String, Object> params) throws JRException, SQLException {
        try(Connection conn = dataSource.getConnection()){
            JasperReport mainReport = JasperCompileManager.compileReport(mainReportPath.toString());
            JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, params, conn);
            ExcelReport.generateExcelReport(jasperPrint, reportStoragePath);
        }
    }

}
