/**
 * Glass Falling
 *Author: Nyran Bonilla
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
    //base case - if only 1 sheet then we must try every floor once
    if(sheets==1) {
      return floors;
    }

    //base case - if the floors are 1 or 0, then there can only be 1 or 0 trials
    if(floors==0 || floors==1) {
      return floors;
    }

    //set min variable to max_value in order to find proper minimum
    int minimum = Integer.MAX_VALUE;
    int result;

    //there can only be 2 possibilties, either the glass breaks and you have to
    //check the floors below the current floor with one less sheet or it doesn't break
    //and we have to check the floors above
    for(int i=1; i<=floors; i++) {
      result = Math.max((glassFallingRecur(i-1,sheets-1)),(glassFallingRecur(floors-i,sheets)));
      minimum = Math.min(result,minimum);
    }
    //add 1 to the final result because we must include the current floor
    return minimum+1;
  }

  // Optional:
  // Pick whatever ;parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets) {
    //initialize array for memoization
   int memo[][] = new int[sheets+1][floors+1];
   return gfMemoHelper(floors, sheets, memo);

 }
  //helper function for memoization
 public int gfMemoHelper(int floors, int sheets, int[][] memo) {
   //memoization initialization
  if(memo[sheets][floors] != 0) {
     return memo[sheets][floors];
   }
   //if the floors are 1 or 0, then there can only be 1 or 0 trials
   if(floors==1 || floors==0) {
     return floors;
   }
   //if only 1 sheet then we must try every floor once
   if(sheets==1) {
     return floors;
   }

   //worst case would be number of floors
   memo[sheets][floors] = floors;
   for(int k=1; k<=floors; k++) {
     int result = Math.max(gfMemoHelper(k-1,sheets-1,memo), gfMemoHelper(floors-k,sheets,memo));
     if(result<memo[sheets][floors]) {
       memo[sheets][floors] = result+1;
     }
   }

  return memo[sheets][floors];


   }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
    int [][] arr = new int [sheets+1][floors+1];

    //set all floors to 1 if only 1 sheet since the minimum trials would be the floors
    for(int i=1; i <= floors; i++) {
      arr[1][i] = i;
    }

    //set all the sheets at floor 0 and 1 to the floor number
    for(int i=1; i<=sheets; i++) {
      arr[i][0] = 0;
      arr[i][1] = 1;
    }

    for (int i = 2; i <=sheets ; i++) {
      for (int j = 2; j <=floors ; j++) {
        arr[i][j] = Integer.MAX_VALUE; //set the min number of trials to min
        int temp;
        //find worst case for each subproblem [i][j]
        for (int k = 1; k <=j ; k++) {
          temp = 1 + Math.max(arr[i-1][k-1], arr[i][j-k]);
          //if temp is less than current entry, then set current entry to temp
          arr[i][j] = Math.min(temp,arr[i][j]);
        }
      }
    }

    return arr[sheets][floors];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingRecur(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
  }
}
