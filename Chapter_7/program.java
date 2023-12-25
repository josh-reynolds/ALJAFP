// A Little Java, A Few Patterns
// Chapter 7: Oh My!


// ======================================
abstract class TreeD {}

class Bud extends TreeD {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Flat extends TreeD {
  FruitD f;
  TreeD t;

  Flat(FruitD _f, TreeD _t){
    f = _f;
    t = _t;
  }
  // -----------------------------
  public String toString(){
    return "new " + getClass().getName() + "(" + f + ", " + t + ")";
  }
}

class Split extends TreeD {
  TreeD l;
  TreeD r;

  Split(TreeD _l, TreeD _r){
    l = _l;
    r = _r;
  }
  // -----------------------------
  public String toString(){
    return "new " + getClass().getName() + "(" + l + ", " + r + ")";
  }
}



// ======================================
abstract class FruitD {}

class Apple extends FruitD {
  public boolean equals(Object _o){
    return (_o instanceof Apple); 
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Peach extends FruitD {
  public boolean equals(Object _o){
    return (_o instanceof Peach); 
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Pear extends FruitD {
  public boolean equals(Object _o){
    return (_o instanceof Pear); 
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Fig extends FruitD {
  public boolean equals(Object _o){
    return (_o instanceof Fig); 
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Lemon extends FruitD {
  public boolean equals(Object _o){
    return (_o instanceof Lemon); 
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("----------------------------");    

    TreeD t1 = new Flat(new Apple(), 
                        new Flat(new Peach(), 
                                 new Bud()));
    System.out.println(t1.toString());

    TreeD t2 = new Flat(new Pear(), 
                        new Bud());
    System.out.println(t2.toString());

    TreeD t3 = new Split(new Bud(), 
                         new Flat(new Fig(), 
                                  new Split(new Bud(), 
                                            new Bud())));
    System.out.println(t3.toString());

    TreeD t4 = new Split(new Split(new Bud(), 
                                   new Flat(new Lemon(), 
                                            new Bud())),
                         new Flat(new Fig(), 
                                  new Split(new Bud(), 
                                  new Bud())));
    System.out.println(t4.toString());

  }
}