import java.util.Scanner;

public class getDistance {

    static char orignal[] = null;
    static char modified[] = null;
    static int table[][]= null;

    public  void getInput(String org , String mod){
        orignal = org.toCharArray();
        modified=mod.toCharArray();
        table = new int[modified.length+1][orignal.length+1];
    }
    public void populateTable(){


        for(int q=0; q<=orignal.length; q++){
            table[0][q]=q;
        }
        for(int z=0; z<=modified.length; z++){
            table[z][0] = z;
        }
        for(int i=1; i<=modified.length; i++){

            for(int j=1; j<=orignal.length; j++){
                if(modified[i-1]==orignal[j-1]){

                    if(table[i][j-1]<= table[i-1][j-1]&& table[i][j-1] <=table[i-1][j]){
                        table[i][j]=table[i][j-1];
                    }else if (table[i-1][j-1]<= table[i][j-1]&& table[i-1][j-1]<=table[i-1][j]){
                        table[i][j]=table[i-1][j-1];
                    }else {
                        table[i][j]=table[i-1][j];
                    }
                }else{
                    if(table[i][j-1]<= table[i-1][j-1]&& table[i][j-1] <=table[i-1][j]){
                        table[i][j]=table[i][j-1]+1;
                    }else if (table[i-1][j-1]<= table[i][j-1]&& table[i-1][j-1]<=table[i-1][j]){
                        table[i][j]=table[i-1][j-1]+1;
                    }else {
                        table[i][j]=table[i-1][j]+1;
                    }
                }
            }

        }
    }
    public int getHeight(){
        return modified.length;
    }
    public int getWidth(){
        return orignal.length;
    }



}
