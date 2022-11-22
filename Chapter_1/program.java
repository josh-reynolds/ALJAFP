// A Little Java, A Few Patterns
// Chapter 1: Modern Toys

// ======================================
abstract class SeasoningD {
  public String toString(){
    return "new " + getClass().getName() + "()";
  }
}

class Salt extends SeasoningD {}
class Pepper extends SeasoningD {}
class Thyme extends SeasoningD {}
class Sage extends SeasoningD {}
// ======================================
abstract class PointD {}

class ManhattanPt extends PointD {
  int x;
  int y;

  ManhattanPt(int _x, int _y){
    x = _x;
    y = _y;
  }
  // -----------------------------

  public String toString(){
    return "new " + getClass().getName() + "(" + x + ", " + y + ")";
  }
}

class CartesianPt extends PointD {
  int x;
  int y;

  CartesianPt(int _x, int _y){
    x = _x;
    y = _y;
  }
  // -----------------------------

  public String toString(){
    return "new " + getClass().getName() + "(" + x + ", " + y + ")";
  }
}
// ======================================
abstract class NumD {}

class Zero extends NumD {
  public String toString(){
    return "new " + getClass().getName() + "()";
  }
}

class OneMoreThan extends NumD {
  NumD predecessor;

  OneMoreThan(NumD _p){
    predecessor = _p;
  }
  // -----------------------------

  public String toString(){
    return "new " + getClass().getName() + "(" + predecessor + ")";
  }
}
// ======================================
abstract class LayerD {}

class Base extends LayerD {
  Object o;

  Base(Object _o){
    o = _o;
  }
  // -----------------------------

  public String toString(){
    return "new " + getClass().getName() + "(" + o + ")";
  }
}

class Slice extends LayerD {
  LayerD l;

  Slice(LayerD _l){
    l = _l;
  }
  // -----------------------------

  public String toString(){
    return "new " + getClass().getName() + "(" + l + ")";
  }
}
// ======================================
class Main {
  public static void main(String[] args){
    Salt s = new Salt();
    System.out.println(s.toString());

    Pepper p = new Pepper();
    System.out.println(p.toString());

    Thyme t = new Thyme();
    System.out.println(t.toString());

    Sage sg = new Sage();
    System.out.println(sg.toString());

    ManhattanPt mp = new ManhattanPt(3,4);
    System.out.println(mp.toString());

    CartesianPt cp = new CartesianPt(7,12);
    System.out.println(cp.toString());

    Zero z = new Zero();
    System.out.println(z.toString());

    OneMoreThan omt = new OneMoreThan(new Zero());
    System.out.println(omt.toString());

    OneMoreThan omt2 = new OneMoreThan(new OneMoreThan(new Zero()));
    System.out.println(omt2.toString());

    Base b = new Base(new Zero());
    System.out.println(b.toString());

    Slice slc = new Slice(new Base(new Pepper()));
    System.out.println(slc.toString());

    // per the book convention, this is "nonsense"
    Base bz = new Base(5);
    System.out.println(bz.toString());

    // preferred approach
    // though this now throws a compiler warning - deprecated
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Integer.html#%3Cinit%3E(int)
    Base bz2 = new Base(new Integer(5));
    System.out.println(bz2.toString());
  }
}