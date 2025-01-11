package result.agency.result_agency_intern.util;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.dto.MonthlyIncomeReportDTO;
import result.agency.result_agency_intern.dto.MonthlyOutcomeReportDTO;
import result.agency.result_agency_intern.dto.PayTypeReportDTO;
import result.agency.result_agency_intern.dto.TransactionTypeReportDTO;
import result.agency.result_agency_intern.repository.TransactionRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {


    private final TransactionRepository transactionRepository;

    public File genereteMonthlyIncomeReport() throws IOException {

        String fileName = "OylikDaromad.xlsx";
        File file = new File(fileName);
        List<MonthlyIncomeReportDTO> monthlyIncome = transactionRepository.getMonthlyIncome();

        // Create a workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a sheet
        Sheet sheet = workbook.createSheet("Monthly Income Report");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Oy raqami");

        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Daromad miqdori");

        // Fill data rows
        int rowIndex = 1; // Start at row 1 (row 0 is for headers)
        for (MonthlyIncomeReportDTO reportDTO : monthlyIncome) {
            Row row = sheet.createRow(rowIndex++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(reportDTO.getMonth());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(reportDTO.getIncome()+" so'm"); // Assuming income is a BigDecimal or similar
        }

        // Auto-size columns
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        // Write the workbook to a file
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Close the workbook
        workbook.close();

        return file;
    }

    public File genereteMonthlyOutcomeReport() throws IOException {

        String fileName = "OylikXarajat.xlsx";
        File file = new File(fileName);
        List<MonthlyOutcomeReportDTO> monthlyIncome = transactionRepository.getMonthlyOutcome();

        // Create a workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a sheet
        Sheet sheet = workbook.createSheet("Monthly Income Report");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Oy raqami");

        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Daromad miqdori");

        // Fill data rows
        int rowIndex = 1; // Start at row 1 (row 0 is for headers)
        for (MonthlyOutcomeReportDTO reportDTO : monthlyIncome) {
            Row row = sheet.createRow(rowIndex++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(reportDTO.getMonth());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(reportDTO.getOutcome()+" so'm"); // Assuming income is a BigDecimal or similar
        }
        // Auto-size columns
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        // Write the workbook to a file
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Close the workbook
        workbook.close();
        return file;
    }

    public File generateByTransactionReport() throws IOException {

        String fileName = "Tranzaksiya_bo'yicha_hisobot.xlsx";
        File file = new File(fileName);
        List<TransactionTypeReportDTO> reportDTOS = transactionRepository.getReportByTransactionType();
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Transaction Report");

        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Transaksiya turi");

        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Daromad miqdori");
        int rowIndex = 1;
        for (TransactionTypeReportDTO reportDTO : reportDTOS) {
            Row row = sheet.createRow(rowIndex++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(reportDTO.getTransactionType());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(reportDTO.getAmount()+"so'm");
        }
        sheet.autoSizeColumn(1);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        workbook.close();
        return file;

    }

    public File generateByPayType() throws IOException {
        String fileName = "Pulturi_bo'yicha_hisobot.xlsx";
        File file = new File(fileName);
        List<PayTypeReportDTO> reportDTOS =  transactionRepository.getDataByPayType();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Transaction Report");

        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Transaksiya turi");

        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Daromad miqdori");
        int rowIndex = 1;
        for (PayTypeReportDTO reportDTO : reportDTOS) {
            Row row = sheet.createRow(rowIndex++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(reportDTO.getPayType());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(reportDTO.getAmount() + "so'm");
        }
        sheet.autoSizeColumn(1);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        workbook.close();
        return file;
    }
}
