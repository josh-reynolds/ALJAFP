// A Little Java, A Few Patterns
// Chapter 2: Methods to Our Madness

// ======================================
abstract class PointD {
  int x;
  int y;

  PointD(int _x, int _y){
    x = _x;
    y = _y;
  }
  // -----------------------------

  abstract int distanceToO();

  Boolean closerToO(PointD p){
    return distanceToO() <= p.distanceToO();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + x + ", " + y + ")";
  }
}

class ManhattanPt extends PointD {
  ManhattanPt(int _x, int _y){
    super(_x, _y);
  }
  // -----------------------------

  int distanceToO(){
    return x + y;
  }
}

class CartesianPt extends PointD {
  CartesianPt(int _x, int _y){
    super(_x, _y);
  }
  // -----------------------------

  int distanceToO(){
    return (int)Math.sqrt(x * x + y * y);
  }
}
// ======================================
abstract class ShishD {
  abstract Boolean onlyOnions();
  abstract Boolean isVegetarian();
}

class Skewer extends ShishD {
  Boolean onlyOnions(){
    return true;
  }

  Boolean isVegetarian(){
    return true;
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Onion extends ShishD {
  ShishD s;

  Onion(ShishD _s){
    s = _s;
  }
  // -----------------------------

  Boolean onlyOnions(){
    return s.onlyOnions();
  }

  Boolean isVegetarian(){
    return s.isVegetarian();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + s + ")";
  }
}

class Lamb extends ShishD {
  ShishD s;

  Lamb(ShishD _s){
    s = _s;
  }
  // -----------------------------

  Boolean onlyOnions(){
    return false;
  }

  Boolean isVegetarian(){
    return false;
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + s + ")";
  }
}

class Tomato extends ShishD {
  ShishD s;

  Tomato(ShishD _s){
    s = _s;
  }
  // -----------------------------

  Boolean onlyOnions(){
    return false;
  }

  Boolean isVegetarian(){
    return s.isVegetarian();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + s + ")";
  }
}
// ======================================
abstract class KebabD {
  abstract Boolean isVeggie();
  abstract Object whatHolder();
}

class Holder extends KebabD {
  Object o;

  Holder(Object _o){
    o = _o;
  }
  // -----------------------------

  Boolean isVeggie(){
    return true;
  }

  Object whatHolder(){
    return o;
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + o + ")";
  }
}

class Shallot extends KebabD {
  KebabD k;

  Shallot(KebabD _k){
    k = _k;
  }
  // -----------------------------

  Boolean isVeggie(){
    return k.isVeggie();
  }

  Object whatHolder(){
    return k.whatHolder();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + k + ")";
  }
}

class Shrimp extends KebabD {
  KebabD k;

  Shrimp(KebabD _k){
    k = _k;
  }
  // -----------------------------

  Boolean isVeggie(){
    return false;
  }

  Object whatHolder(){
    return k.whatHolder();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + k + ")";
  }
}

class Radish extends KebabD {
  KebabD k;

  Radish(KebabD _k){
    k = _k;
  }
  // -----------------------------

  Boolean isVeggie(){
    return k.isVeggie();
  }

  Object whatHolder(){
    return k.whatHolder();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + k + ")";
  }
}

class Pepper extends KebabD {
  KebabD k;

  Pepper(KebabD _k){
    k = _k;
  }
  // -----------------------------

  Boolean isVeggie(){
    return k.isVeggie();
  }

  Object whatHolder(){
    return k.whatHolder();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + k + ")";
  }
}

class Zucchini extends KebabD {
  KebabD k;

  Zucchini(KebabD _k){
    k = _k;
  }
  // -----------------------------

  Boolean isVeggie(){
    return k.isVeggie();
  }

  Object whatHolder(){
    return k.whatHolder();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + k + ")";
  }
}
// ======================================
abstract class RodD {}
class Dagger extends RodD {  
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
class Sabre extends RodD {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
class Sword extends RodD {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
// ======================================
abstract class PlateD {}
class Gold extends PlateD {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
class Silver extends PlateD {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
class Brass extends PlateD {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
class Copper extends PlateD {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
class Wood extends PlateD {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("----------------------------");    

    ManhattanPt mp = new ManhattanPt(3,4);
    System.out.println(mp.toString());
    System.out.println("Distance to Origin: " + mp.distanceToO());
    System.out.println("Closer to Origin: " + mp.closerToO(new ManhattanPt(7,15)));
    System.out.println("Closer to Origin: " + mp.closerToO(new CartesianPt(1,1)));

    CartesianPt cp = new CartesianPt(3,4);
    System.out.println(cp.toString());
    System.out.println("Distance to Origin: " + cp.distanceToO());
    System.out.println("Closer to Origin: " + cp.closerToO(new CartesianPt(1,1)));
    System.out.println("Closer to Origin: " + cp.closerToO(new ManhattanPt(7,15)));

    System.out.println("----------------------------");    

    Tomato t = new Tomato(new Lamb(new Onion(new Skewer())));
    System.out.println(t.toString());
    System.out.println("Only Onions: " + t.onlyOnions());
    System.out.println("Is Vegetarian: " + t.isVegetarian());

    Onion o = new Onion(new Onion(new Onion(new Skewer())));
    System.out.println(o.toString());
    System.out.println("Only Onions: " + o.onlyOnions());
    System.out.println("Is Vegetarian: " + o.isVegetarian());

    Onion o2 = new Onion(new Onion(new Tomato(new Skewer())));
    System.out.println(o2.toString());
    System.out.println("Only Onions: " + o2.onlyOnions());
    System.out.println("Is Vegetarian: " + o2.isVegetarian());

    Skewer s = new Skewer();
    System.out.println(s.toString());
    System.out.println("Only Onions: " + s.onlyOnions());
    System.out.println("Is Vegetarian: " + s.isVegetarian());

    System.out.println("----------------------------");    

    Radish r = new Radish(new Shrimp(new Shallot(new Holder(new Sabre()))));
    System.out.println(r.toString());
    System.out.println("Is Veggie: " + r.isVeggie());
    System.out.println("What Holder: " + r.whatHolder());

    Shrimp shr = new Shrimp(new Holder(new Copper()));
    System.out.println(shr.toString());
    System.out.println("Is Veggie: " + shr.isVeggie());
    System.out.println("What Holder: " + shr.whatHolder());

    Shallot shl = new Shallot(new Radish(new Shallot(new Holder(new Gold()))));
    System.out.println(shl.toString());
    System.out.println("Is Veggie: " + shl.isVeggie());
    System.out.println("What Holder: " + shl.whatHolder());

    Zucchini z = new Zucchini(new Pepper(new Shallot(new Radish(new Shallot(new Holder(new Gold()))))));
    System.out.println(z.toString());
    System.out.println("Is Veggie: " + z.isVeggie());
    System.out.println("What Holder: " + z.whatHolder());

    System.out.println("----------------------------");    
  }
}