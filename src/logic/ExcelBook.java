package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelBook {

	private XSSFWorkbook workbook;
	private ExcelSheet actualSheet;

	public ExcelBook(){
		workbook = new XSSFWorkbook();
		actualSheet = new ExcelSheet();
	}

	public void selectSheet(String sheetName) {
		try {
			actualSheet.setSheet(workbook.getSheet(sheetName));			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();

		}
	}
	
	public void putRowActualSheet(Integer key, Object[] objects ) {
		actualSheet.putRow(key, objects);
	} 
		
	public void  newSheet(String sheetName) {
		actualSheet = new ExcelSheet();
		actualSheet.setSheet(workbook.createSheet(sheetName));
	}
	
	public void  writeActualSheet() {
		actualSheet.writeSheet();
	}
	
	public void  readSheet(String sheetName) {
		actualSheet.readSheet(sheetName);
	}
	
	public String getCellStringValue(int key, int col){
		return actualSheet.getCellStringValue(key, col);
		
	}
	
	public void createExcelFile(String path, String fileName) {
		
		try {
			FileOutputStream out = new FileOutputStream(new File(path+fileName));
			this.workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			
		}
	}
	
	public void readExcelFile(String path, String fileName) {
		try (FileInputStream file = new FileInputStream(new File(path+fileName))) {
			this.workbook = new XSSFWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	//adiciones
	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public ExcelSheet getActualSheet() {
		return actualSheet;
	}
	
	public Map<Integer, Object[]>  getDataMap(){
		return this.actualSheet.getDataMap();
	}
	
	public String getCellType(int key, int col) {
		return this.actualSheet.getCellValue(key, col);
	}
	
	public Double getCellnumeric(int key, int col) {
		return this.actualSheet.getCellNumericValue(key, col);
	}
	
	public ArrayList<String> extractItems(){
		ArrayList<String> subTotals = new ArrayList<String>();
		for (int i = 0; i < this.actualSheet.getDataMap().size(); i++) {
			for (int j = 0; j < this.actualSheet.getDataMap().get(i).length; j++) {
				if(getCellType(i, j).equals("ÍTEM")) {
					subTotals.add(getCellType(i+1, j));
				}
			}
		}
		return subTotals;
	}
    	
	//-----------------------------
	
	//*********************************************************************************
	
	



	private class ExcelSheet {
		
		private Map<Integer, Object[]> dataMap; 
		private XSSFSheet sheet;
			
		private ExcelSheet(){
			dataMap = new TreeMap<Integer, Object[]>();
		}	

		private void setSheet(XSSFSheet sheet) {
			this.sheet = sheet;
		}
		
		private void putRow(Integer key, Object[] objects ) {
		   this.dataMap.put(key, objects);
	    }
		
		private String getCellStringValue(int key, int col){
			Object[] arregloObjetos = dataMap.get(key);
			XSSFCell cell= (XSSFCell) arregloObjetos[col];
			return cell.getStringCellValue();
			
		}
		
		//adicion
		private Double getCellNumericValue(int key, int col){
			Object[] arregloObjetos = dataMap.get(key);
			XSSFCell cell= (XSSFCell) arregloObjetos[col];
			return cell.getNumericCellValue();
		}
		
		private String getCellFormulaValue(int key, int col){
			Object[] arregloObjetos = dataMap.get(key);
			XSSFCell cell= (XSSFCell) arregloObjetos[col];
			return cell.getCellFormula();
		}
		
		public Map<Integer, Object[]> getDataMap() {
			return dataMap;
		}
		
		public String getCellValue(int key, int col) {
			Object[] arregloObjetos = dataMap.get(key);
			XSSFCell cell= (XSSFCell) arregloObjetos[col];
			
			if (cell == null) {
				return "";
			}else if (cell.getCellType()==Constants.CELL_TYPE_NUMERIC) {
				return ""+getCellNumericValue(key,col);
			}else if (cell.getCellType()==Constants.CELL_TYPE_STRING) {
				return getCellStringValue(key,col);
			}else if(cell.getCellType()==Constants.CELL_TYPE_FORMULA) {
				return getCellFormulaValue(key,col);
//						+ " - "+getCellNumericValue(key,col);
			}else
			return " ";
		}
		
		//**
		
		private void writeSheet() {
			
	    	Iterator<Integer>  it = dataMap.keySet().iterator();
	    	while(it.hasNext()){
	    	  Integer key = it.next();
	    	  XSSFRow row = sheet.createRow(key);
	    	  Object[] arregloObjetos = dataMap.get(key);
		      int numeroCelda = 0;
		      for (Object obj : arregloObjetos) {
		    	  XSSFCell cell = row.createCell(numeroCelda++);
		    	 
		    	  if (obj instanceof String) {
		      		  cell.setCellValue((String) obj);
		      		  
		          } else if (obj instanceof Integer) {
		        	  cell.setCellValue((Integer) obj);	        	  
		          }
		        }
		    }
	    }
		
		private void readSheet(String sheetName) {
			selectSheet(sheetName);
			Iterator<Row> rowIterator = sheet.iterator();
 			Row row;
 			Integer rowNumber=0;
 			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell = null;
				Object[] objects = null;
				try {
//					objects = new Object[row.getLastCellNum()];		
					objects = new Object[50];		
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("no sirvio");
				}
				Integer col = 0;
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					objects[col++] = cell;
				}
				putRowActualSheet(rowNumber++, objects);
			}	
		}

		

		
		
	}
}