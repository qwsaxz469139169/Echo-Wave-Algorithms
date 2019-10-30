package uk.ac.ncl.echo_wave_algorithms.tool;

import java.util.Random;

public class RandomTool {
	private static Random random = new Random();
	
	/***
	 * random get a number in a given scale
	 * @param int Object: random scale
	 * @return int Object: a random number
	 */
	public static int randomInt(int scale){
		int result = random.nextInt(scale);
		return result;
	}
	
	/***
	 * get a random nonredundant number at random in a given scale
	 * @param int Object: random scale
	 * @return int[] Object: a random number
	 */
	public static int[] randomN(int scale){
		int[] randomBox = new int[scale];	
		for (int i = 0; i < randomBox.length; i++) {
			randomBox[i] = i;
		}
		
		int n = randomInt(scale)+1;	
		int[] result = new int[n];
		
		int index = -1;
		for (int i = 0; i < result.length; i++) {
			index = randomInt(randomBox.length - i);
			
			int temp = randomBox[index];
			
			result[i] = randomBox[index];
			randomBox[index] = randomBox[randomBox.length - 1 - i];
			randomBox[randomBox.length - 1 - i] = temp;
		}	
		
        return result;
	}
	
	/***
	 * random get some number in a given scale
	 * @param int Object: random scale
	 * @return int[] Object: a random number
	 */
	public static int[] randomNRed(int scale) {
		// TODO Auto-generated method stub
		int n = randomInt(scale)+1;	
		int[] result = new int[n];
		
		for (int i = 0; i < result.length; i++){
			result[i]=randomInt(scale);
		}
		
		return result;
	}
}
