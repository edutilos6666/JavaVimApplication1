public class MyAssert {
  public static boolean assertEquals(int expected, int actual) throws Exception  {
      if(expected == actual) return true ; 
      else throw new Exception(constructErrorMessage(expected , actual)); 
  }

  public static boolean assertEquals(double expected , double actual) throws Exception {
       if(expected == actual) return true ; 
       else throw new Exception(constructErrorMessage(expected, actual)); 
  }


  public static boolean assertEquals(long expected , long actual) throws Exception {
       if(expected == actual) return true ; 
       else throw new Exception(constructErrorMessage(expected, actual)); 
  }


    public static boolean assertEquals(boolean expected , boolean actual) throws Exception {
       if(expected == actual) return true ; 
       else throw new Exception(constructErrorMessage(expected, actual)); 
  }


    public static boolean assertEquals(String expected , String actual) throws Exception {
       if(expected.equals(actual)) return true ; 
       else throw new Exception(constructErrorMessage(expected, actual)); 
  }



  private static String constructErrorMessage(Object expected , Object actual) {
    return String.format("expected(%s) is not equal to actual(%s)", expected.toString() , actual.toString());  
  }
}
