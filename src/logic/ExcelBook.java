package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entities.TypeElement;


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
//		this.actualSheet.getCellFormulaValue(key, col)
		return this.actualSheet.getCellValue(key, col);
//		return this.actualSheet.getCellValue(key, col);
	}
	
	public Double getCellnumeric(int key, int col) {
		return this.actualSheet.getCellNumericValue(key, col);
	}
	
	public Map<String, APU> extractItems(String hoja){
		Map<String, APU> map=new HashMap<>();
		
		ArrayList<String> items = new ArrayList<String>();
		for (int i = 0; i < this.actualSheet.getDataMap().size(); i++) {
			for (int j = 0; j < this.actualSheet.getDataMap().get(i).length; j++) {
				if(getCellType(i, j).equals("ÍTEM")) {
					APU apu = new APU();
					if(hoja.contains("APU")) {
						apu.setCodigo(this.actualSheet.getCellValue(i+1, j));
						apu.setCodigo(apu.getCodigo().replaceAll("\\.", "-"));
						apu.setNombre(this.actualSheet.getCellValue(i+1, j+2));
						apu.setNombre(apu.getNombre().replaceAll(";", ":"));
						apu.setUnidad(this.actualSheet.getCellValue(i+1, 'T'-'A'));
						apu.setValor(getCostoDirecto(i));
						apu.setValor(apu.getValor().replaceAll("\\.", ","));
						map.put(apu.getCodigo(), apu);
					}
				}
			}
		}
		return map;
	}
	
	public void extractCantidad(String hoja) {
		for (int i = 0; i < this.actualSheet.getDataMap().size(); i++) {
			for (int j = 0; j < this.actualSheet.getDataMap().get(i).length; j++) {
				if(getCellType(i, j).equals("ÍTEM")) {
					//se va a extraer la cantidad
				}
			}
		}
	}
	
	public String getCostoDirecto(int i) {
		for (int ii = i; ii < this.actualSheet.getDataMap().size(); ii++) {
			for (int jj = 0; jj < this.actualSheet.getDataMap().get(ii).length; jj++) {
				if(getCellType(ii, jj).equals("TOTAL COSTO DIRECTO")) {
					return this.actualSheet.getCellValue(ii, 'T'-'A');
					
//					for (int k = j+1; k < this.actualSheet.getDataMap().get(i).length; k++) {
//						if (getCellType(i, k)!="" && this.actualSheet.getCellNumericValue(i, k)!=0) {
//							apu.setValor(this.actualSheet.getCellNumericValue(i, k));
//						}
//					}
				}
			}
		}
		return null;
		
	}
	
	
	public ArrayList<String> extractNamesItems(){
		ArrayList<String> itemsNames = new ArrayList<String>();
		for (int i = 0; i < this.actualSheet.getDataMap().size(); i++) {
			for (int j = 0; j < this.actualSheet.getDataMap().get(i).length; j++) {
//				System.out.print(getCellType(i,j)+" - ");
				if(getCellType(i, j).equals("ÍTEM")) {
//					System.out.println("*********  "+getCellType(i+1,j+1));
					itemsNames.add(this.actualSheet.getCellFormulaValueString(i+1, j+2));
				}
			}
//			System.out.println();
		}
		return itemsNames;
	}
	
	public ArrayList<String> extractItemsTotalCosts(){
		ArrayList<String> itemsTotalCosts = new ArrayList<String>();
		for (int i = 0; i < this.actualSheet.getDataMap().size(); i++) {
			for (int j = 0; j < this.actualSheet.getDataMap().get(i).length; j++) {
				if(getCellType(i, j).equals("TOTAL COSTO DIRECTO")) {
					for (int k = j+1; k < this.actualSheet.getDataMap().get(i).length; k++) {
						if (getCellType(i, k)!="" && this.actualSheet.getCellNumericValue(i, k)!=0) {
							itemsTotalCosts.add(this.actualSheet.getCellValue(i, k));
						}
					}
				}
			}
		}
		return itemsTotalCosts;
	}
	
	public Vector<String> extractTypesMaterials(){
		Vector<String> typesElements = new Vector<String>();
		for (int i = 0; i < this.actualSheet.getDataMap().size(); i++) {
			try {
				if(getCellType(i, 0)!="" && ((getCellType(i, 0).charAt(0)=='I')
								|| (getCellType(i, 0).charAt(0)=='V')
								|| (getCellType(i, 0).charAt(0)=='X')
								|| (getCellType(i, 0).charAt(0)=='L') ) && ((getCellType(i, 0).charAt(1)=='I')
										|| (getCellType(i, 0).charAt(1)=='V')
										|| (getCellType(i, 0).charAt(1)=='X')
										|| (getCellType(i, 0).charAt(1)=='L')
										||	(getCellType(i, 0).charAt(1)=='.')) ) {
					if(!typesElements.contains(getCellType(i, 0))){
						typesElements.add(getCellType(i, 0));
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return typesElements;
	}
	
	public Vector<TypeElement> createTypesMaterials(){
		Vector<TypeElement> typesElements = new Vector<TypeElement>();
		Vector<String> typesElementsAux = this.extractTypesMaterials();
		for (int i = 0; i < typesElementsAux.size(); i++) {
			TypeElement typeElement = new TypeElement();
			typeElement.setName(typesElementsAux.get(i));
			typesElements.add(typeElement);
		}
		return typesElements;
	}
    	
	//-----------------------------
	
	//*********************************************************************************
	
	



	public class ExcelSheet {
		
		private Map<Integer, Object[]> dataMap; 
		private XSSFSheet sheet;
			
		private ExcelSheet(){
			dataMap = new TreeMap<Integer, Object[]>();
		}	

		public void setSheet(XSSFSheet sheet) {
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
			switch (cell.getCachedFormulaResultType()) {
			case Cell.CELL_TYPE_NUMERIC:
				return String.valueOf(cell.getNumericCellValue());
			case Cell.CELL_TYPE_STRING:
				return String.valueOf(cell.getRichStringCellValue());
			default:
				break;
			}
			return cell.getCellFormula();
		}
		
		private String getCellFormulaValueString(int key, int col){
			Object[] arregloObjetos = dataMap.get(key);
			XSSFCell cell= (XSSFCell) arregloObjetos[col];
			return cell.getStringCellValue();
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