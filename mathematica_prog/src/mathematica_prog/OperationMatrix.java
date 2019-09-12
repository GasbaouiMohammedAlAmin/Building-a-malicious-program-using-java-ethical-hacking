/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathematica_prog;

import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author amine gasa
 */
public class OperationMatrix {
    int mat_A[][];
    int mat_B[][];
    int mat_Result[][];
    int row_matA,column_matA;
    int row_matB,column_matB;

    public OperationMatrix(int row_matA, int column_matA, int row_matB, int column_matB) {
        this.row_matA = row_matA;
        this.column_matA = column_matA;
        this.row_matB = row_matB;
        this.column_matB = column_matB;
      this.mat_A=new int[row_matA][column_matA];
      this.mat_B=new int[row_matB][column_matB];
      this.mat_Result=new int[row_matA][column_matB];
    }
    
    void readsMatrix(JTable table1,JTable table2){
          for (int i = 0; i < row_matA; i++) {
            for (int j = 0; j < column_matA; j++) {
                mat_A[i][j] = Integer.parseInt(table1.getValueAt(i, j) + "");
            }
    }
              for (int i = 0; i < row_matB; i++) {
            for (int j = 0; j < column_matB; j++) {
                mat_B[i][j] = Integer.parseInt(table2.getValueAt(i, j) + "");
            }
    }
}
  void addition(){
       for (int i = 0; i < row_matA; i++) {
            for (int j = 0; j < column_matA; j++) {
                mat_Result[i][j] = mat_A[i][j] + mat_B[i][j];

            }
        }
  }  
  void substraction(){
       for (int i = 0; i < row_matA; i++) {
            for (int j = 0; j < column_matA; j++) {
                mat_Result[i][j] = mat_A[i][j] - mat_B[i][j];

            }
        }
  } 
  void multiplicationBy_elements(){
       for (int i = 0; i < row_matA; i++) {
            for (int j = 0; j < column_matA; j++) {
                mat_Result[i][j] = mat_A[i][j] * mat_B[i][j];

            }
        }
  } 
  void division(){
       for (int i = 0; i < row_matA; i++) {
            for (int j = 0; j < column_matA; j++) {
                                 if (mat_B[i][j] != 0) {
                    mat_Result[i][j] = mat_A[i][j] / mat_B[i][j];
                } else {
                    JOptionPane.showMessageDialog(null, "division by zero ", "error enter", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
  } 
  void multiplicatioMatrix(){
      for (int k = 0; k < column_matA; k++) {
            for (int i = 0; i < row_matA; i++) {
                for (int j = 0; j < column_matB; j++) {
                    mat_Result[i][j] += mat_A[i][k] * mat_B[k][j];
                }
            }
        }
  }
  void writeResults(JTable table3){
      for (int i = 0; i < row_matA; i++) {
            for (int j = 0; j < column_matB; j++) {
                table3.setValueAt(mat_Result[i][j], i, j);

            }
        }
  }
}
