package logic;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import entities.TypeElement;

/**
 *
 * @author jeol
 */
public class LeerExcel {

	public static void main(String[] args) {
		ExcelBook libro = new ExcelBook();
    	
    	//libro.readExcelFile("E:\\seta_projects\\testExcel\\", "PRESUPUESTO Y APU-ELECTRICOS-VR2.xlsx");
		libro.readExcelFile("C://Users/LENOVO PC/Documents/", "RCI MEM Y APU (Autoguardado).xlsx");
    	int num = libro.getWorkbook().getNumberOfSheets();
    	for (int i = 0; i < num; i++) {
    		XSSFSheet hoja = libro.getWorkbook().getSheetAt(i);
//    		System.out.println(hoja.getSheetName());
    		
    		leerLibro(libro, hoja.getSheetName());	
    		
//    		else 
//    			if(!hoja.getSheetName().contains("PRESUPUESTO"))
//    			System.out.println(hoja.getSheetName());
		}    
	}
	
    public static void leerLibro(ExcelBook libro, String hoja) {
       
    		
//    	int colum = -1;
//    	double acum = 0;
//    	double acumTotal = 0;
//    	boolean isSubtotal = false;
    	libro.readSheet(hoja);
    	
//    	Vector<TypeElement> typeElements = libro.createTypesMaterials();
    	
    		Map<String, APU> items = libro.extractItems(hoja);
//    	ArrayList<String> totalCosts = libro.extractItemsTotalCosts();
//    	Map<String,String> cantidades;
    	for(String codigo:items.keySet()) {
    		System.out.println(codigo+";"+items.get(codigo).getNombre()+";"+items.get(codigo).getUnidad()+";"+items.get(codigo).getValor()+";"+items.get(codigo).getCantidad());
    	}
    	
//    	ArrayList<Double> subTotals = new ArrayList<Double>();
    	
//    			if(libro.getCellType(i, j).equals("Vr Unitario")||libro.getCellType(i, j).equals("Vr. Unitario")) {
//    				colum = j;
//    			}
//    			if(libro.getCellType(i, j).equals("SUBTOTAL")||libro.getCellType(i, j).equals("TOTAL COSTO DIRECTO")) {
//    				isSubtotal = true;
//    				if(acum!=0) {
//    					subTotals.add(acum);
//    				}
//    				acum = 0;
//    			}
			
//    		try {
//    			if (!isSubtotal && libro.getCellnumeric(i, colum)!=0) {
//    				acum += libro.getCellnumeric(i, colum);
//				}
//    			isSubtotal = false;
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
    }
}