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
  }
}
