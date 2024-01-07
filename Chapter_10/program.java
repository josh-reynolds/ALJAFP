// A Little Java, A Few Patterns
// Chapter 10: The State of Things to Come


// ======================================
abstract class ... {
}

class ... extends ... {

  //-------------------------------

  public String toString(){
    return "new " + getClass().getName() + "(" + x + ", " + y + ")";
  }
}

// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("-----------------------------");
    //CartesianPt c1 = new CartesianPt(3, 4);
    //System.out.println(c1);
    //System.out.println(c1.distanceToO());
  }
}