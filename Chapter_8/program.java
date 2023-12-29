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

interface ExprVisitorI {
  Object forPlus(ExprD _l, ExprD _r);
  Object forDiff(ExprD _l, ExprD _r);
  Object forProd(ExprD _l, ExprD _r);
  Object forConst(Object _c);
}

class IntEvalV implements ExprVisitorI {
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

// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("-----------------------------");

    ExprD e1 = new Plus(new Const(3), new Const(2));
    System.out.println(e1.accept(new IntEvalV()));

    ExprD e2 = new Plus(new Const(7),
                        new Prod(new Diff(new Const(4),
                                          new Const(3)),
                                 new Const(5)));
    System.out.println(e2.accept(new IntEvalV()));
  }
}
