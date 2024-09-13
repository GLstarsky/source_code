package QuanLyCuaHangSach.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NhapXuatExcel {
	 public static String exportDataToExcel(JTable table, String filePath, String tenSheet) {
		    String message;
		    try (Workbook workbook = new XSSFWorkbook()) {
		        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("tenSheet");

		        for (int i = 0; i < table.getRowCount(); i++) {
		            Row row = sheet.createRow(i);
		            for (int j = 0; j < table.getColumnCount(); j++) {
		                Object value = table.getValueAt(i, j);
		                Cell cell = row.createCell(j);
		                if (value != null) {
		                    cell.setCellValue(value.toString());
		                }
		            }
		        }

		        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
		            workbook.write(outputStream);
		        }
		        message = "Xuất dữ liệu ra file Excel thành công!";
		    } catch (IOException e) {
		        message = "Lỗi khi xuất dữ liệu ra file Excel: " + e.getMessage();
		    }
		    return message;
		}

	 public static void readExcelToTable(File file, JTable table) {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

	        try (FileInputStream fis = new FileInputStream(file);
	             Workbook workbook = new XSSFWorkbook(fis)) {

	        	XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0); // Lấy sheet đầu tiên

	            for (Row row : sheet) {
	                Object[] rowData = new Object[row.getLastCellNum()];
	                int cellIndex = 0;
	                for (Cell cell : row) {
	                    switch (cell.getCellType()) {
	                        case STRING:
	                            rowData[cellIndex++] = cell.getStringCellValue();
	                            break;
	                        case NUMERIC:
	                            rowData[cellIndex++] = cell.getNumericCellValue();
	                            break;
	                        case BOOLEAN:
	                            rowData[cellIndex++] = cell.getBooleanCellValue();
	                            break;
	                        default:
	                            rowData[cellIndex++] = null;
	                            break;
	                    }
	                }
	                model.addRow(rowData); // Thêm hàng vào bảng
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
