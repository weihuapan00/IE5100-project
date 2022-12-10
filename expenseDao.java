import java.util.Arrays;
import java.util.LinkedList;

public class expenseDao{
    private String filepath;
    
    private String[] col = new String[] {"item name" , "price" , "category" ,"date" };
    // private Class[] colClass = new Class[] {String.class,Double.class,String.class,String.class};
    private Object[][] row;
    
    public expenseDao(String filepath){
        // SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy",Locale.US);
        setFilepath(filepath);
        LinkedList<String> str_list = read_csv();
        row = new Object[str_list.size()+100][4];
        for(int i =0 ; i<str_list.size(); i++){
            String str = str_list.get(i);
            LinkedList<String> list = new LinkedList<>(Arrays.asList(str.split(",")));
            for (int j=0; j<list.size(); j++){
                String obj = list.get(j);
                switch(j){
                case 0: row[i][j] = obj;
                        break;
                case 1: row[i][j] = Double.valueOf(obj);
                        break;
                case 2: row[i][j] = obj;
                        break;
                case 3: row[i][j] = obj;
                        break;
                default:  break;
                }
            }
        }
    }
    
    public String getColumnName(int index){
        return col[index];
    }


    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Object[][] getRows() {
        return row;
    }

    public void  addRow(Object[] arr) {
        Object[][] new_arr = new Object[row.length+1][4];
        for (int i=0; i< row.length; i++){
            for(int j = 0 ; j < row[i].length; j++){
                new_arr[i][j] = row[i][j];
            }
        }
        new_arr[row.length] = arr;
        setRows(new_arr);
    }

    public void setRows(Object[][] row) {
        this.row = row;
    }

    public void updateRow(int index,Object[] arr){
        row[index] = arr;
    }

    public void removeRow(int index){
        Object[][] new_arr = new Object[row.length-1][4];
        for(int i=0, k=0;i<row.length;i++){
            if(i!=index){
                new_arr[k]=row[i];
                k++;
            }
        }
        setRows(new_arr);
    }

    public String[] getCol() {
        return col;
    }


    public void setCol(String[] col) {
        this.col = col;
    }



    public LinkedList<String> read_csv(){
        fileOpener file = new fileOpener(filepath);
        file.readFile();
        LinkedList<String> str_list = file.getStr_list();
        return str_list;
    }

    public boolean write_csv(){
        return true;
    }

    public static void main(String[] args) { 
        expenseDao model = new expenseDao("test.csv");
        Object[][] row = model.getRows();
        for (int i=0; i<row.length; i++){
            for(int j=0; j<4; j++){
                System.out.println(row[i][j]);
            }
        }
        System.out.println("\nAfter update");
        

        model.updateRow(1,new Object[]{"Apple",Double.valueOf(10),"food","12-9-2022"});
        row = model.getRows();
        for (int i=0; i<row.length; i++){
            for(int j=0; j<4; j++){
                System.out.println(row[i][j]);
            }
        }
        } 
} 


    

    

