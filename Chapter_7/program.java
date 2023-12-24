// A Little Java, A Few Patterns
// Chapter 7: Oh My!


// ======================================
abstract class TreeD {
  
}

class Flat extends TreeD {
  Object t;
  TreeD r;

  Flat(Object _t, TreeD _r){
    t = _t;
    r = _r;
  }
  // -----------------------------
  public String toString(){
    return "new " + getClass().getName() + "(" + t + ", " + r + ")";
  }
}

class Bud extends TreeD {

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Apple {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Peach {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("----------------------------");    

    TreeD t = new Flat(new Apple(), new Flat(new Peach(), new Bud()));
    System.out.println(t.toString());

  }
}