package logic;


/**
 *
 * @author jeol
 */
public class CrearExcel {

    public static void main(String args[]) {
       
    	ExcelBook libro = new ExcelBook();
    	
    	libro.newSheet("Hoja Uno");
    	libro.selectSheet("Hoja Uno");
    	libro.putRowActualSheet(0, new Object[]{"Identificador", "Nombre", "Apellidos"});
    	libro.putRowActualSheet(1, new Object[]{1, "Jorge", "Luna"});
    	libro.writeActualSheet();
  
    	libro.newSheet("Hoja Dos");
    	libro.selectSheet("Hoja Dos");
    	libro.putRowActualSheet(0, new Object[]{"Identificador", "Nombre", "Apellidos"});
    	libro.putRowActualSheet(1, new Object[]{1, "Pedro", "Perez"});
    	libro.writeActualSheet();
    	
    	libro.createExcelFile("C:/Proyectos/testExcel/", "PruebaExcel.xlsx");
    	
    }
}