// A Little Java, A Few Patterns
// Chapter 7: Oh My!


// ======================================
abstract class TreeD {
  abstract boolean accept(bTreeVisitorI _ask);
}

interface bTreeVisitorI {
  boolean forBud();
  boolean forFlat(FruitD _f, TreeD _t);
  boolean forSplit(TreeD _l, TreeD _r);
}

class bIsFlatV implements bTreeVisitorI {
  public boolean forBud(){ return true; }
  public boolean forFlat(FruitD _f, TreeD _t){ return _t.accept(this); }
  public boolean forSplit(TreeD _l, TreeD _r){ return false; }
}

class bIsSplitV implements bTreeVisitorI {
  public boolean forBud(){ return true; }
  public boolean forFlat(FruitD _f, TreeD _t){ return false; }
  public boolean forSplit(TreeD _l, TreeD _r){
    return _l.accept(this) && _r.accept(this); }
}

class bHasFruitV implements bTreeVisitorI {
  public boolean forBud(){ return false; }
  public boolean forFlat(FruitD _f, TreeD _t){ return true; }
  public boolean forSplit(TreeD _l, TreeD _r){
    return _l.accept(this) || _r.accept(this); }
}

class Bud extends TreeD {
  boolean accept(bTreeVisitorI _ask){
    return _ask.forBud();
  }

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
  boolean accept(bTreeVisitorI _ask){
    return _ask.forFlat(f, t);
  }

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
  boolean accept(bTreeVisitorI _ask){
    return _ask.forSplit(l, r);
  }

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
    System.out.println("t1 is flat = " + t1.accept(new bIsFlatV()));
    System.out.println("t1 is split = " + t1.accept(new bIsSplitV()));
    System.out.println("t1 has fruit = " + t1.accept(new bHasFruitV()));

    TreeD t2 = new Flat(new Pear(), 
                        new Bud());
    System.out.println(t2.toString());
    System.out.println("t2 is flat = " + t2.accept(new bIsFlatV()));
    System.out.println("t2 is split = " + t2.accept(new bIsSplitV()));
    System.out.println("t2 has fruit = " + t2.accept(new bHasFruitV()));

    TreeD t3 = new Split(new Bud(), 
                         new Flat(new Fig(), 
                                  new Split(new Bud(), 
                                            new Bud())));
    System.out.println(t3.toString());
    System.out.println("t3 is flat = " + t3.accept(new bIsFlatV()));
    System.out.println("t3 is split = " + t3.accept(new bIsSplitV()));
    System.out.println("t3 has fruit = " + t3.accept(new bHasFruitV()));

    TreeD t4 = new Split(new Split(new Bud(), 
                                   new Flat(new Lemon(), 
                                            new Bud())),
                         new Flat(new Fig(), 
                                  new Split(new Bud(), 
                                  new Bud())));
    System.out.println(t4.toString());
    System.out.println("t4 is flat = " + t4.accept(new bIsFlatV()));
    System.out.println("t4 is split = " + t4.accept(new bIsSplitV()));
    System.out.println("t4 has fruit = " + t4.accept(new bHasFruitV()));

    TreeD t5 = new Bud();
    System.out.println(t5.toString());
    System.out.println("t5 is flat = " + t5.accept(new bIsFlatV()));
    System.out.println("t5 is split = " + t5.accept(new bIsSplitV()));
    System.out.println("t5 has fruit = " + t5.accept(new bHasFruitV()));

    TreeD t6 = new Split(new Split(new Bud(),
                                   new Split(new Bud(),
                                             new Bud())),
                         new Split(new Bud(),
                                   new Split(new Bud(),
                                             new Bud())));
    System.out.println(t6.toString());
    System.out.println("t6 is flat = " + t6.accept(new bIsFlatV()));
    System.out.println("t6 is split = " + t6.accept(new bIsSplitV()));
    System.out.println("t6 has fruit = " + t6.accept(new bHasFruitV()));
  }
}