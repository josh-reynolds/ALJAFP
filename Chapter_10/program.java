// A Little Java, A Few Patterns
// Chapter 10: The State of Things to Come


// ======================================
interface PiemanI {
  int addTop(Object _t);
  int remTop(Object _t);
  int substTop(Object _n, Object _o);
  int occTop(Object _o);
}

class PiemanM implements PiemanI {
  PieD p = new Bot();

  public int addTop(Object _t){
    p = new Top(_t, p)
    ;
    return occTop(_t);
  }

  public int remTop(Object _t){
    p = (PieD)p.accept(new RemV(_t))
    ;
    return occTop(_t);
  }

  public int substTop(Object _n, Object _o){
    p = (PieD)p.accept(new SubstV(_n, _o))
    ;
    return occTop(_n);
  }

  public int occTop(Object _o){
    return ((int)p.accept(new OccursV(_o)));
  }
}

abstract class PieD {
  abstract Object accept(PieVisitorI _ask);
}

class Bot extends PieD {
  Object accept(PieVisitorI _ask){
    return _ask.forBot();
  }

  public String toString(){
    return "new " + getClass().getName() + "()";
  }
}

class Top extends PieD {
  Object t;
  PieD r;

  Top(Object _t, PieD _r){
    t = _t;
    r = _r;
  }
  //-------------------------------
  Object accept(PieVisitorI _ask){
    return _ask.forTop(t, r);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + t + ", " + r + ")";
  }
}

interface PieVisitorI {
  Object forBot();
  Object forTop(Object _t, PieD _r);
}

class RemV implements PieVisitorI {
  Object o;

  RemV(Object _o){
    o = _o;
  }
  //-------------------------------
  public Object forBot(){
    return new Bot();
  }

  public Object forTop(Object _t, PieD _r){
    if (o.equals(_t)){
      return _r.accept(this);
    } else {
      return new Top(_t, (PieD)_r.accept(this));
    }
  }
}

class SubstV implements PieVisitorI {
  Object n;
  Object o;

  SubstV(Object _n, Object _o){
    n = _n;
    o = _o;
  }
  //-------------------------------
  public Object forBot(){
    return new Bot();
  }

  public Object forTop(Object _t, PieD _r){
    if (o.equals(_t)){
      return new Top(n, (PieD)_r.accept(this));
    } else {
      return new Top(_t, (PieD)_r.accept(this));
    }
  }
}

class OccursV implements PieVisitorI {
  Object a;

  OccursV(Object _a){
    a = _a;
  }
  //-------------------------------
  public Object forBot(){
    return 0;
  }

  public Object forTop(Object _t, PieD _r){
    if (_t.equals(a)){
      return ((int)(_r.accept(this))) + 1;
    } else {
      return _r.accept(this);
    }
  }
}

// ======================================
abstract class FishD {}
class Anchovy extends FishD {
  public String toString(){
    return "new " + getClass().getName() + "()";
  }
}
// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("-----------------------------");
    PieD p1 = new Top(new Anchovy(), 
                      new Bot());
    System.out.println(p1);

    System.out.println("-----------------------------");
    PiemanM pm1 = new PiemanM();
    System.out.println(pm1);
    System.out.println(pm1.occTop(new Anchovy()));
  }
}