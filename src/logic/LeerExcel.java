package logic;

import java.util.Vector;

import entities.TypeElement;

/**
 *
 * @author jeol
 */
public class LeerExcel {

    public static void main(String args[]) {
       
    	ExcelBook libro = new ExcelBook();
    	
    	libro.readExcelFile("E:\\seta_projects\\testExcel\\", "PRESUPUESTO Y APU-ELECTRICOS-VR2.xlsx");
    	libro.readSheet("APU");
//    	int colum = -1;
//    	double acum = 0;
//    	double acumTotal = 0;
//    	boolean isSubtotal = false;
    	
    	
    	Vector<TypeElement> typeElements = libro.createTypesMaterials();
    	for (int i = 0; i < typeElements.size(); i++) {
			System.out.println(typeElements.get(i).getName());
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