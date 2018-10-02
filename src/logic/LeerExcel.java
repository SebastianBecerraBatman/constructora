package logic;


/**
 *
 * @author jeol
 */
public class LeerExcel {

    public static void main(String args[]) {
       
    	ExcelBook libro = new ExcelBook();
    	
    	libro.readExcelFile("E:\\seta_projects\\constructora\\documentos_constructora\\", "FORMATO MEMORIAS DE CANTIDADES-ELECTRICAS.xlsx");
    	//prueba del primer commit 
    	//en serio es una prueba
    	libro.readSheet("10.1");
    	for (int i = 0; i < libro.getDataMap().size(); i++) {
    		for (int j = 0; j < libro.getDataMap().get(i).length; j++) {
				System.out.print(libro.getCellType(i, j)+" ");
			}
    		System.out.println();
		}

    	
    	  	
    }
}