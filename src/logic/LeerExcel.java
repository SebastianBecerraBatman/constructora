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

	public static void main(String[] args) {
		ExcelBook libro = new ExcelBook();
    	ExcelBook crearLibro = new ExcelBook();
    	crearLibro.newSheet("RCI MEM Y APU (Autoguardado)");
    	crearLibro.selectSheet("RCI MEM Y APU (Autoguardado)");
		identificadorFila = 0;
		identificadorFilaQuantity = 0;
		crearLibro.putRowActualSheet(identificadorFila, new Object[]{"Item", "Descripcion", "Unidad","Vr. Unitario","Cantidad"});
//    	libro.readExcelFile("E:\\seta_projects\\testExcel\\", "PRESUPUESTO Y APU-ELECTRICOS-VR2.xlsx");
//		libro.readExcelFile("F:\\UPTC\\", "RED CONTRA INCENDIO PARA SISTEMAS.xlsx");
		libro.readExcelFile("C:\\Users\\LENOVO PC\\Documents\\", "RCI MEM Y APU (Autoguardado).xlsx");
    	int num = libro.getWorkbook().getNumberOfSheets();
    	for (int i = 0; i < num; i++) {
    		XSSFSheet hoja = libro.getWorkbook().getSheetAt(i);
    		if (hoja.getSheetName().contains("APU")) {
    			leerLibro(libro, crearLibro, hoja.getSheetName());	
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
    		Object[] objectVectorAux = crearLibro.addToVector(items.get(codigo).getCantidad()+"", crearLibro.getRowActualSheet(identificadorFilaQuantity));
    			crearLibro.putRowActualSheet(identificadorFilaQuantity, new Object[] {objectVectorAux[0],objectVectorAux[1],objectVectorAux[2],objectVectorAux[3],objectVectorAux[4]});
    	}
    }
}