// A Little Java, A Few Patterns
// Chapter 9: Be a Good Visitor


// ======================================
abstract class PointD {
  int x;
  int y;

  PointD(int _x, int _y){
    x = _x;
    y = _y;
  }
  //-------------------------------
  abstract int distanceToO();

  boolean closerToO(PointD _p){
    return distanceToO() <= _p.distanceToO();
  }

  PointD minus(PointD _p){
    return new CartesianPt(x - _p.x, y - _p.y);
  }

}

class CartesianPt extends PointD {
  CartesianPt(int _x, int _y){
    super(_x, _y);
  }
  //-------------------------------
  int distanceToO(){
    return (int)Math.sqrt(x * x + y * y);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + x + ", " + y + ")";
  }  
}

class ManhattanPt extends PointD {
  ManhattanPt(int _x, int _y){
    super(_x, _y);
  }
  //-------------------------------
  int distanceToO(){
    return x + y;
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + x + ", " + y + ")";
  }
}

class ShadowedManhattanPt extends ManhattanPt {
  int dx;
  int dy;

  ShadowedManhattanPt(int _x, int _y, int _dx, int _dy){
    super(_x, _y);
    dx = _dx;
    dy = _dy;
  }
  //-------------------------------
  int distanceToO(){
    return super.distanceToO() + dx + dy;
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + x + ", " + y + ", " + dx + ", " + dy + ")";
  }
}

class ShadowedCartesianPt extends CartesianPt {
  int dx;
  int dy;

  ShadowedCartesianPt(int _x, int _y, int _dx, int _dy){
    super(_x, _y);
    dx = _dx;
    dy = _dy;
  }
  //-------------------------------
  int distanceToO(){
    return new CartesianPt(x + dx, y + dy).distanceToO();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + x + ", " + y + ", " + dx + ", " + dy + ")";
  }
}

// ======================================
abstract class ShapeD {
  abstract boolean accept(ShapeVisitorI _ask);
}

class Circle extends ShapeD {
  int r;

  Circle(int _r){
    r = _r;
  }
  //-------------------------------
  boolean accept(ShapeVisitorI _ask){
    return _ask.forCircle(r);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + r + ")";
  }
}

class Square extends ShapeD {
  int s;

  Square(int _s){
    s = _s;
  }
  //-------------------------------
  boolean accept(ShapeVisitorI _ask){
    return _ask.forSquare(s);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + s + ")";
  }
}

class Trans extends ShapeD {
  PointD q;
  ShapeD s;

  Trans(PointD _q, ShapeD _s){
    q = _q;
    s = _s;
  }
  //-------------------------------
  boolean accept(ShapeVisitorI _ask){
    return _ask.forTrans(q, s);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + q + ", " + s + ")";
  }
}

interface ShapeVisitorI {
  boolean forCircle(int _r);
  boolean forSquare(int _s);
  boolean forTrans(PointD _q, ShapeD _s);
}

class HasPtV implements ShapeVisitorI {
  PointD p;

  HasPtV(PointD _p){
    p = _p;
  }
  //-------------------------------
  public boolean forCircle(int _r){
    return p.distanceToO() <= _r;
  }

  public boolean forSquare(int _s){
    return (p.x <= _s) && (p.y <= _s);
  }

  public boolean forTrans(PointD _q, ShapeD _s){
    return _s.accept(new HasPtV(p.minus(_q)));
  }
}

// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("-----------------------------");
    CartesianPt c1 = new CartesianPt(3, 4);
    System.out.println(c1);
    System.out.println(c1.distanceToO());

    System.out.println("-----------------------------");
    ShadowedManhattanPt s1 = new ShadowedManhattanPt(2, 3, 1, 0);
    System.out.println(s1);
    System.out.println(s1.distanceToO());

    System.out.println("-----------------------------");
    ShadowedCartesianPt s2 = new ShadowedCartesianPt(12, 5, 3, 4);
    System.out.println(s2);
    System.out.println(s2.distanceToO());

    System.out.println("-----------------------------");
    System.out.println(new CartesianPt(3, 4).closerToO(new ShadowedCartesianPt(1, 5, 1, 2)));

    System.out.println("-----------------------------");
    Trans t1 = new Trans(new CartesianPt(5, 6), new Circle(10));
    System.out.println(t1);

    System.out.println("-----------------------------");
    Trans t2 = new Trans(new CartesianPt(5, 6), new Square(10));
    System.out.println(t2);

    System.out.println("-----------------------------");
    Circle cx1 = new Circle(10);
    System.out.println(cx1);
    System.out.println(cx1.accept(new HasPtV(new CartesianPt(10, 10))));

    System.out.println("-----------------------------");
    Square sq1 = new Square(10);
    System.out.println(sq1);
    System.out.println(sq1.accept(new HasPtV(new CartesianPt(10, 10))));

    System.out.println("-----------------------------");
    Trans t3 = new Trans(new CartesianPt(5, 6), new Circle(10));
    System.out.println(t3);
    System.out.println(t3.accept(new HasPtV(new CartesianPt(10, 10))));
  }
}
