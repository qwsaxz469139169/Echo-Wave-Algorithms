package uk.ac.ncl.echo_wave_algorithms.tool;

import org.ujmp.core.Matrix;
import org.ujmp.core.calculation.Calculation.Ret;
import org.ujmp.core.doublematrix.DenseDoubleMatrix2D;

public class MatrixTool {
	
	//Matrix sum columns
	public  int sumColumns(int column,DenseDoubleMatrix2D matrix){
		Matrix selectColumns = matrix.selectColumns(Ret.NEW, column);

		int valueSum = (int) selectColumns.getValueSum();
		
		return valueSum;	
	}
}
