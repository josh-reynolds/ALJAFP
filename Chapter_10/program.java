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
    return _ask.forBot(this);
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
    return _ask.forTop(this);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + t + ", " + r + ")";
  }
}

interface PieVisitorI {
  Object forBot(Bot _that);
  Object forTop(Top _that);
}

class RemV implements PieVisitorI {
  Object o;

  RemV(Object _o){
    o = _o;
  }
  //-------------------------------
  public Object forBot(Bot _that){
    return _that;
  }

  public Object forTop(Top _that){
    if (o.equals(_that.t)){
      return _that.r.accept(this);
    } else {
      return new Top(_that.t, (PieD)_that.r.accept(this));
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
  public Object forBot(Bot _that){
    return _that;
  }

  public Object forTop(Top _that){
    if (o.equals(_that.t)){
      _that.t = n
      ;
      _that.r.accept(this)
      ;
      return _that;
    } else {
      _that.r.accept(this)
      ;
      return _that;
    }
  }
}

class OccursV implements PieVisitorI {
  Object a;

  OccursV(Object _a){
    a = _a;
  }
  //-------------------------------
  public Object forBot(Bot _that){
    return 0;
  }

  public Object forTop(Top _that){
    if (_that.t.equals(a)){
      return ((int)(_that.r.accept(this))) + 1;
    } else {
      return _that.r.accept(this);
    }
  }
}

// ======================================
// text doesn't include these, assumes already present or 
// easily reconstructible (which is true) - also
// does not remind us we need to override equals...

abstract class FishD {}
class Anchovy extends FishD {
  public String toString(){
    return "new " + getClass().getName() + "()";
  }
  public boolean equals(Object _o){
    return _o instanceof Anchovy;
  }
}
class Tuna extends FishD {
  public String toString(){
    return "new " + getClass().getName() + "()";
  }
  public boolean equals(Object _o){
    return _o instanceof Tuna;
  }
}
class Salmon extends FishD {
  public String toString(){
    return "new " + getClass().getName() + "()";
  }
  public boolean equals(Object _o){
    return _o instanceof Salmon;
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
    PiemanI pm1 = new PiemanM();
    System.out.println(pm1);
    System.out.println(pm1.occTop(new Anchovy()));
    System.out.println(pm1.addTop(new Anchovy()));
    System.out.println(pm1.substTop(new Tuna(), new Anchovy()));
    System.out.println(pm1.occTop(new Anchovy()));
    System.out.println(pm1.occTop(new Salmon()));
    //pm1.p = new Bot();   - not exposed by Interface, will not compile

    System.out.println("-----------------------------");
    PiemanI pm2 = new PiemanM();
    System.out.println(pm2);
    System.out.println(pm2.addTop(new Anchovy()));
    System.out.println(pm2.addTop(new Anchovy()));
    System.out.println(pm2.addTop(new Salmon()));
    System.out.println(pm2.addTop(new Tuna()));
    System.out.println(pm2.addTop(new Tuna()));
    System.out.println(pm2.substTop(new Tuna(), new Anchovy()));
    System.out.println(pm2.remTop(new Tuna()));
    System.out.println(pm2.occTop(new Salmon()));
  }
}