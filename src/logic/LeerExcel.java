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
	
	private static int identificadorFila;
	private static int identificadorFilaQuantity;
	private static String itemActual="";

	public static void main(String[] args) {
		ExcelBook libro = new ExcelBook();
    	ExcelBook crearLibro = new ExcelBook();
    	crearLibro.newSheet("ppp");
    	crearLibro.selectSheet("ppp");
		identificadorFila = 0;
		identificadorFilaQuantity = 0;
		crearLibro.putRowActualSheet(identificadorFila, new Object[]{"Item", "Descripcion", "Unidad","Vr. Unitario","Cantidad"});
//    	libro.readExcelFile("E:\\seta_projects\\testExcel\\", "PRESUPUESTO Y APU-ELECTRICOS-VR2.xlsx");
//		libro.readExcelFile("F:\\UPTC\\", "RED CONTRA INCENDIO PARA SISTEMAS.xlsx");
//		libro.readExcelFile("C:\\Users\\LENOVO PC\\Documents\\", "RCI MEM Y APU (Autoguardado).xlsx");
		libro.readExcelFile("C:\\Users\\LENOVO PC\\Documents\\APUS\\", "MEMORIAS DE CANTIDADES Y APUS HS -PARA SISTEMAS.xlsx");
    	int num = libro.getWorkbook().getNumberOfSheets();
    	for (int i = 0; i < num; i++) {
    		XSSFSheet hoja = libro.getWorkbook().getSheetAt(i);
    		if (hoja.getSheetName().contains("APU")) {
    			leerLibro(libro, crearLibro, hoja.getSheetName());	
    			itemActual = crearLibro.getRowActualSheet(1)[0].toString();
			}else
				leerLibroNoNameApu(libro, crearLibro, hoja.getSheetName());
		}    
    	crearLibro.writeActualSheet();
    	crearLibro.createExcelFile("C:/Users/LENOVO PC/Documents/", "PresupuestoGeneral.xlsx");
		
//		crearLibro.putRowActualSheet(identificadorFila, new Object[]{"Item", "Descripcion", "Unidad","Vr. Unitario","Cantidad"});
//		libro.readExcelFile("E:\\seta_projects\\testExcel\\", "PRESUPUESTO Y APU-ELECTRICOS-VR2.xlsx");
//		crearLibro.newSheet("Electricos");
//    	crearLibro.selectSheet("Electricos");
//		identificadorFila = 0;
//		crearLibro.putRowActualSheet(identificadorFila, new Object[]{"Item", "Descripcion", "Unidad","Vr. Unitario","Cantidad"});
//		num = libro.getWorkbook().getNumberOfSheets();
//    	for (int i = 0; i < num; i++) {
//    		XSSFSheet hoja = libro.getWorkbook().getSheetAt(i);
//    		if (hoja.getSheetName().contains("APU")) {
//    			leerLibro(libro, crearLibro, hoja.getSheetName());	
//			}
//		}    
//    	crearLibro.writeActualSheet();
//		crearLibro.createExcelFile("C:/Users/LENOVO PC/Documents/", "PresupuestoGeneral.xlsx");
		
   	}
	
	public static void leerLibro(ExcelBook libro, ExcelBook crearLibro, String hoja) {
    	libro.readSheet(hoja);
   		Map<String, APU> apus = libro.extractItemsAPU(hoja);
    	for(String codigo:apus.keySet()) {
    		identificadorFila++;
    		crearLibro.putRowActualSheet(identificadorFila, new Object[]{codigo, apus.get(codigo).getNombre(), apus.get(codigo).getUnidad(),apus.get(codigo).getValor()});
    	}
    }
	
    public static void leerLibroNoNameApu(ExcelBook libro, ExcelBook crearLibro, String hoja) {
    	libro.readSheet(hoja);
    	Map<String, APU> items = libro.extractItems(hoja);
    	//Imprime solo cantidades
    	for(String codigo:items.keySet()) {
    		identificadorFilaQuantity++;
    		if (!hoja.contains(itemActual)) {
    			itemActual = crearLibro.getRowActualSheet(identificadorFilaQuantity)[0].toString();
    			System.out.println(itemActual+" es el item actual");
    		}else 
    			identificadorFilaQuantity--;
    		System.out.println("***********************************    "+itemActual);
//    		System.out.println(hoja+" * "+crearLibro.getRowActualSheet(identificadorFilaQuantity)[0]);
    		if (identificadorFilaQuantity <= identificadorFila) {
    			Object[] objectVectorAux = crearLibro.addToVector(items.get(codigo).getCantidad().toString(), crearLibro.getRowActualSheet(identificadorFilaQuantity));
    			crearLibro.putRowActualSheet(identificadorFilaQuantity, new Object[] {objectVectorAux[0],objectVectorAux[1],objectVectorAux[2],objectVectorAux[3],objectVectorAux[4]});				
			}
    	}
    }
}