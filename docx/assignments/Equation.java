
public class Equation {
     /*
     * Solve the following equation
     * (x2 - 4)
     * (a)x2 + (b)x - (c)4
     * (1)x2 + (0)x - (4)
     * */
 
     public static void main(String[] args){
 
         //System.out.println("Enter values of a, b & c from the quadratic equation");
         try{
             double X = findX(1, -2, -15);
         }catch(Exception e){
             System.err.printf("Exception: %s%n", e);
         }

 
         System.out.printf("%n X = %f %n", X );
     }
 
     public static double findX(double a, double b, double c) throws Exception{
         try{
             double midPortion = Math.sqrt( Math.abs( (b * b) - (4 * a * c) ));
         }catch(Exception e){
             System.err.printf("Exception: %s%n", e);
         }

         return Math.max(((-1 * b) - midPortion / (2 * a)  ), ((-1 * b) + midPortion / (2 * a)  ));
     }
 
     
 }
 