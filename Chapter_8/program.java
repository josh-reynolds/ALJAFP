// A Little Java, A Few Patterns
// Chapter 8: Like Father, Like Son


// ======================================
abstract class ExprD {
  abstract Object accept(ExprVisitorI _ask);
}

class Plus extends ExprD {
  ExprD l;
  ExprD r;

  Plus(ExprD _l, ExprD _r){
    l = _l;
    r = _r;
  }
  // -----------------------------
  Object accept(ExprVisitorI _ask){
    return _ask.forPlus(l, r);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + l + ", " + r + ")";
  }
}

class Diff extends ExprD {
  ExprD l;
  ExprD r;

  Diff(ExprD _l, ExprD _r){
    l = _l;
    r = _r;
  }
  // -----------------------------
  Object accept(ExprVisitorI _ask){
    return _ask.forDiff(l, r);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + l + ", " + r + ")";
  }
}

class Prod extends ExprD {
  ExprD l;
  ExprD r;

  Prod(ExprD _l, ExprD _r){
    l = _l;
    r = _r;
  }
  // -----------------------------
  Object accept(ExprVisitorI _ask){
    return _ask.forProd(l, r);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + l + ", " + r + ")";
  }
}

class Const extends ExprD {
  Object c;

  Const(Object _c){
    c = _c;
  }
  // -----------------------------
  Object accept(ExprVisitorI _ask){
    return _ask.forConst(c);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + c + ")";
  }
}

// ======================================
abstract class SetD {
  SetD add(int _i){
    if (mem(_i)){
      return this;
    } else {
      return new Add(_i, this);
    }
  }

  abstract boolean mem(int _i);
  abstract SetD plus(SetD _s);
  abstract SetD diff(SetD _s);
  abstract SetD prod(SetD _s);
}

class Empty extends SetD {
  boolean mem(int _i){
    return false;
  }

  SetD plus(SetD _s){
    return _s;
  }

  SetD diff(SetD _s){
    return new Empty();
  }

  SetD prod(SetD _s){
    return new Empty();
  }

  public String toString(){
    return "new " + getClass().getName() + "()";
  }
}

class Add extends SetD {
  int i;
  SetD s;

  Add(int _i, SetD _s){
    i = _i;
    s = _s;
  }

  // -----------------------------
  boolean mem(int _i){
    if (i == _i){
      return true;
    } else {
      return s.mem(_i);
    }
  }

  SetD plus(SetD _s){
    return s.plus(_s.add(i));
  }

  SetD diff(SetD _s){
    if (_s.mem(i)){
      return s.diff(_s);
    } else {
      return s.diff(_s).add(i);
    }
  }

  SetD prod(SetD _s){
    if (_s .mem(i)){
      return s.prod(_s).add(i);
    } else {
      return s.prod(_s);
    }
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + i + ", " + s + ")";
  }
}

// ======================================
interface ExprVisitorI {
  Object forPlus(ExprD _l, ExprD _r);
  Object forDiff(ExprD _l, ExprD _r);
  Object forProd(ExprD _l, ExprD _r);
  Object forConst(Object _c);
}

abstract class EvalD implements ExprVisitorI {
  public Object forPlus(ExprD _l, ExprD _r){
    return plus(_l.accept(this), _r.accept(this));
  }

  public Object forDiff(ExprD _l, ExprD _r){
    return diff(_l.accept(this), _r.accept(this));
  }

  public Object forProd(ExprD _l, ExprD _r){
    return prod(_l.accept(this), _r.accept(this));
  }

  public Object forConst(Object _c){
    return _c;
  }

  abstract Object plus(Object _l, Object _r);
  abstract Object diff(Object _l, Object _r);
  abstract Object prod(Object _l, Object _r);
}

class IntEvalV extends EvalD {
  Object plus(Object _l, Object _r){
    // text uses Integer (now deprecated) and Integer.intValue() like this:
    //return new Integer(((Integer)_l).intValue() + 
    //                   ((Integer)_r).intValue());
    return (int)_l + (int)_r;
  }

  Object diff(Object _l, Object _r){
    // text uses Integer (now deprecated) and Integer.intValue() like this:
    //return new Integer(((Integer)_l).intValue() - 
    //                   ((Integer)_r).intValue());
    return (int)_l - (int)_r;
  }

  Object prod(Object _l, Object _r){
    // text uses Integer (now deprecated) and Integer.intValue() like this:
    //return new Integer(((Integer)_l).intValue() * 
    //                   ((Integer)_r).intValue());
    return (int)_l * (int)_r;
  }
}

class SetEvalV extends EvalD {
  Object plus(Object _l, Object _r){
    return ((SetD)_l).plus((SetD)_r);
  }

  Object diff(Object _l, Object _r){
    return ((SetD)_l).diff((SetD)_r);
  }

  Object prod(Object _l, Object _r){
    return ((SetD)_l).prod((SetD)_r);
  }
}

// ======================================
abstract class FishD{
  public String toString(){
    return "new " + getClass().getName() + "()";
  }
}
class Mackerel extends FishD{
  public boolean equals(Object _o){
    return (_o instanceof Mackerel);
  }
}
class Tuna extends FishD{
  public boolean equals(Object _o){
    return (_o instanceof Tuna);
  }
}
class Cod extends FishD{
  public boolean equals(Object _o){
    return (_o instanceof Cod);
  }
}
class Anchovy extends FishD{
  public boolean equals(Object _o){
    return (_o instanceof Anchovy);
  }
}

// ======================================
abstract class PieD{
  abstract PieD accept(PieVisitorI _ask);
}

class Bot extends PieD {
  PieD accept(PieVisitorI _ask){ return _ask.forBot(); }

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
  // -----------------------------
  PieD accept(PieVisitorI _ask){ return _ask.forTop(t, r); }

  public String toString(){
    return "new " + getClass().getName() + "(" + t + ", " + r + ")";
  }
}

// ======================================
interface PieVisitorI {
  abstract PieD forBot();
  abstract PieD forTop(Object _t, PieD _r);
}

abstract class SubstD implements PieVisitorI {
  Object n;
  Object o;

  SubstD(Object _n, Object _o){
    n = _n;
    o = _o;
  }

  // ----------------------------
  public PieD forBot(){ return new Bot(); }
  public abstract PieD forTop(Object _t, PieD _r);
}

class SubstV implements PieVisitorI {
  Object n;
  Object o;

  SubstV(Object _n, Object _o){
    n = _n;
    o = _o;
  }

  // ----------------------------
  public PieD forBot(){ return new Bot(); }

  public PieD forTop(Object _t, PieD _r){
    if (o.equals(_t)){
      return new Top(n, _r.accept(this));
    } else {
      return new Top(_t, _r.accept(this));
    }
  }
}

class LtdSubstV extends SubstV {
  int c;

  LtdSubstV(int _c, Object _n, Object _o){
    super(_n, _o);
    c = _c;
  }

  // -----------------------------
  public PieD forTop(Object _t, PieD _r){
    if (c == 0){
      return new Top(_t, _r);
    } else {
      if (o.equals(_t)){
        return new Top(n, _r.accept(new LtdSubstV(c-1, n, o)));
      } else {
        return new Top(_t, _r.accept(this));
      }
    }
  }
}

// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("-----------------------------");
    ExprD e1 = new Plus(new Const(3), new Const(2));
    System.out.println(e1);
    System.out.println(e1.accept(new IntEvalV()));

    System.out.println("-----------------------------");
    ExprD e2 = new Plus(new Const(7),
                        new Prod(new Diff(new Const(4),
                                          new Const(3)),
                                 new Const(5)));
    System.out.println(e2);
    System.out.println(e2.accept(new IntEvalV()));

    System.out.println("-----------------------------");
    SetD s1 = new Add(4, 
                      new Add(3, 
                              new Add(10, 
                                      new Empty())));
    System.out.println(s1);
    //System.out.println(s1.accept(new SetEvalV()));
    // SetD does not have accept() so this is invalid

    System.out.println("-----------------------------");
    ExprD e3 = new Prod(new Const(new Empty().add(7)),
                       new Const(new Empty().add(3)));
    System.out.println(e3);
    System.out.println(e3.accept(new SetEvalV()));

    System.out.println("-----------------------------");
    PieD p1 = new Top(new Cod(), 
                      new Top(new Anchovy(), 
                              new Bot()));
    System.out.println(p1);
    System.out.println(p1.accept(new SubstV(new Cod(), new Anchovy())));
    System.out.println(p1.accept(new SubstV(new Mackerel(), new Cod())));
  }
}
