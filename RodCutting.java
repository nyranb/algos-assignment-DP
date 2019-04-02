/**
 * Rod cutting problem described in Chapter 15 of textbook
 * Author: Nyran Bonilla
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
    //initialize arr for memoization
    int [] memo = new int[lengthPrices.length+1];

    //set all values to -1 for comparison later
    for(int i=0; i<=lengthPrices.length;i++) {
	      memo[i] = -1;
	  }
	    return rodCuttingRecurAux(lengthPrices, rodLength, memo);
	  }

  //memoization helper function
	public int rodCuttingRecurAux(int[] prices, int rodLength, int[] memo) {
    int result;

    //memoization initialization
    if(memo[rodLength]>=0) {
	      return memo[rodLength];
	    }

      //base case - if the rodlength is 0, return 0
	    if(rodLength==0) {
	      result = 0;
	    } else {
  	    result = Integer.MIN_VALUE;
  	    for(int i=0; i<rodLength; i++) {
          //break rod into pieces and find best prices with choosen combinations
          //set result to highest price
          result=Math.max(result, prices[i]+rodCuttingRecurAux(prices,rodLength-(i+1),memo));
  	    }
	    }
	    memo[rodLength] = result;
	    return result;
  }

  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
	  int[] resultsArr = new int [rodLength+1];
	    resultsArr[0] = 0;

	    for (int i = 1; i<=rodLength; i++)
	        {
	            int max = Integer.MIN_VALUE;
	            for (int j = 0; j < i; j++)
	                max= Math.max(max,lengthPrices[j] + resultsArr[i-j-1]);
	            resultsArr[i] = max;
	        }

	        return resultsArr[rodLength];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
