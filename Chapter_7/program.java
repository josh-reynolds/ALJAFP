// A Little Java, A Few Patterns
// Chapter 7: Oh My!


// ======================================
interface TreeVisitorI {
  Object forBud();
  Object forFlat(FruitD _f, TreeD _t);
  Object forSplit(TreeD _l, TreeD _r);
}

class IsFlatV implements TreeVisitorI {
  public Object forBud(){ return true; }   // text says use "new Boolean(true)" but this is now deprecated
  public Object forFlat(FruitD _f, TreeD _t){
    return _t.accept(this);
  }
  public Object forSplit(TreeD _l, TreeD _r){
    return false;                          // text says use "new Boolean(false)" but this is now deprecated
  }
}

class IsSplitV implements TreeVisitorI {
  public Object forBud(){ return true; }   // see note above
  public Object forFlat(FruitD _f, TreeD _t){ 
    return false;
  }
  public Object forSplit(TreeD _l, TreeD _r){
    return (Boolean)(_l.accept(this)) && 
           (Boolean)(_r.accept(this));
    // text uses booleanValue(), something like the following:
    //   return ((Boolean)(_l.accept(this))).booleanValue() && 
    //          ((Boolean)(_r.accept(this))).booleanValue();
  }
}

class OccursV implements TreeVisitorI {
  FruitD a;

  OccursV(FruitD _a){
    a = _a;
  }
  // -----------------------------
  public Object forBud(){ return 0; }      // text says use "new Integer(0)" but this is now deprecated
  public Object forFlat(FruitD _f, TreeD _t){
    if (_f.equals(a)){
      return ((Integer)(_t.accept(this))) + 1;
    } else {
      return ((Integer)(_t.accept(this)));
    }
  }
  public Object forSplit(TreeD _l, TreeD _r){
    return ((Integer)(_l.accept(this))) + 
           ((Integer)(_r.accept(this)));
  }

  // similar to note above under IsSplitV, the text uses intValue(), like:
  //   return (((Integer)(_t.accept(this)))).intValue() + 1;
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

interface iTreeVisitorI {
  int forBud();
  int forFlat(FruitD _f, TreeD _t);
  int forSplit(TreeD _l, TreeD _r);
}

class iHeightV implements iTreeVisitorI {
  public int forBud(){ return 0; }
  public int forFlat(FruitD _f, TreeD _t){
    return _t.accept(this) + 1;
  }
  public int forSplit(TreeD _l, TreeD _r){
    return Math.max(_l.accept(this), _r.accept(this)) + 1;
  }
}

class iOccursV implements iTreeVisitorI {
  FruitD a;

  iOccursV(FruitD _a){
    a = _a;
  }
  // -----------------------------
  public int forBud(){ return 0; }
  public int forFlat(FruitD _f, TreeD _t){
    if (a.equals(_f)){
      return _t.accept(this) + 1;
    } else {
      return _t.accept(this);
    }
  }
  public int forSplit(TreeD _l, TreeD _r){
    return _l.accept(this) + _r.accept(this);
  }
}

interface tTreeVisitorI {
  TreeD forBud();
  TreeD forFlat(FruitD _f, TreeD _t);
  TreeD forSplit(TreeD _l, TreeD _r);
}

class tSubstV implements tTreeVisitorI {
  FruitD n;
  FruitD o;

  tSubstV(FruitD _n, FruitD _o){
    n = _n;
    o = _o;
  }
  // -----------------------------
  public TreeD forBud(){ return new Bud(); }

  public TreeD forFlat(FruitD _f, TreeD _t){
    if (o.equals(_f)){
      return new Flat(n, _t.accept(this));
    } else {
      return new Flat(_f, _t.accept(this));
    }
  }

  public TreeD forSplit(TreeD _l, TreeD _r){
    return new Split(_l.accept(this), _r.accept(this));    
  }
}

// ======================================
abstract class TreeD {
  abstract boolean accept(bTreeVisitorI _ask);
  abstract int accept(iTreeVisitorI _ask);
  abstract TreeD accept(tTreeVisitorI _ask);
  abstract Object accept(TreeVisitorI _ask);
}

class Bud extends TreeD {
  boolean accept(bTreeVisitorI _ask){
    return _ask.forBud();
  }

  int accept(iTreeVisitorI _ask){
    return _ask.forBud();
  }

  TreeD accept(tTreeVisitorI _ask){
    return _ask.forBud();
  }

  Object accept(TreeVisitorI _ask){
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

  int accept(iTreeVisitorI _ask){
    return _ask.forFlat(f, t);
  }

  TreeD accept(tTreeVisitorI _ask){
    return _ask.forFlat(f, t);
  }  

  Object accept(TreeVisitorI _ask){
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

  int accept(iTreeVisitorI _ask){
    return _ask.forSplit(l, r);
  }

  TreeD accept(tTreeVisitorI _ask){
    return _ask.forSplit(l, r);
  }

  Object accept(TreeVisitorI _ask){
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
    System.out.println("-----------------------------");

    TreeD t1 = new Flat(new Apple(), 
                        new Flat(new Peach(), 
                                 new Bud()));
    System.out.println(t1.toString());
    System.out.println("t1 is flat = " + t1.accept(new bIsFlatV()));
    System.out.println("t1 is split = " + t1.accept(new bIsSplitV()));
    System.out.println("t1 has fruit = " + t1.accept(new bHasFruitV()));
    System.out.println("t1 height = " + t1.accept(new iHeightV()));
    System.out.println("t1 sub Apple for Fig = " + t1.accept(new tSubstV(new Apple(),
                                                                          new Fig())));
    System.out.println("t1 count Figs = " + t1.accept(new iOccursV(new Fig())));
    System.out.println("t1 is flat = " + t1.accept(new IsFlatV()));
    System.out.println("t1 is split = " + t1.accept(new IsSplitV()));
    System.out.println("t1 count Figs = " + t1.accept(new OccursV(new Fig())));

    System.out.println("-----------------------------");
    TreeD t2 = new Flat(new Pear(), 
                        new Bud());
    System.out.println(t2.toString());
    System.out.println("t2 is flat = " + t2.accept(new bIsFlatV()));
    System.out.println("t2 is split = " + t2.accept(new bIsSplitV()));
    System.out.println("t2 has fruit = " + t2.accept(new bHasFruitV()));
    System.out.println("t2 height = " + t2.accept(new iHeightV()));
    System.out.println("t2 sub Apple for Fig = " + t2.accept(new tSubstV(new Apple(),
                                                                          new Fig())));
    System.out.println("t2 count Figs = " + t2.accept(new iOccursV(new Fig())));
    System.out.println("t2 is flat = " + t2.accept(new IsFlatV()));
    System.out.println("t2 is split = " + t2.accept(new IsSplitV()));
    System.out.println("t2 count Figs = " + t2.accept(new OccursV(new Fig())));

    System.out.println("-----------------------------");
    TreeD t3 = new Split(new Bud(), 
                         new Flat(new Fig(), 
                                  new Split(new Bud(), 
                                            new Bud())));
    System.out.println(t3.toString());
    System.out.println("t3 is flat = " + t3.accept(new bIsFlatV()));
    System.out.println("t3 is split = " + t3.accept(new bIsSplitV()));
    System.out.println("t3 has fruit = " + t3.accept(new bHasFruitV()));
    System.out.println("t3 height = " + t3.accept(new iHeightV()));
    System.out.println("t3 sub Apple for Fig = " + t3.accept(new tSubstV(new Apple(),
                                                                          new Fig())));
    System.out.println("t3 count Figs = " + t3.accept(new iOccursV(new Fig())));
    System.out.println("t3 is flat = " + t3.accept(new IsFlatV()));
    System.out.println("t3 is split = " + t3.accept(new IsSplitV()));
    System.out.println("t3 count Figs = " + t3.accept(new OccursV(new Fig())));

    System.out.println("-----------------------------");
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
    System.out.println("t4 height = " + t4.accept(new iHeightV()));
    System.out.println("t4 sub Apple for Fig = " + t4.accept(new tSubstV(new Apple(),
                                                                          new Fig())));
    System.out.println("t4 count Figs = " + t4.accept(new iOccursV(new Fig())));
    System.out.println("t4 is flat = " + t4.accept(new IsFlatV()));
    System.out.println("t4 is split = " + t4.accept(new IsSplitV()));
    System.out.println("t4 count Figs = " + t4.accept(new OccursV(new Fig())));

    System.out.println("-----------------------------");
    TreeD t5 = new Bud();
    System.out.println(t5.toString());
    System.out.println("t5 is flat = " + t5.accept(new bIsFlatV()));
    System.out.println("t5 is split = " + t5.accept(new bIsSplitV()));
    System.out.println("t5 has fruit = " + t5.accept(new bHasFruitV()));
    System.out.println("t5 height = " + t5.accept(new iHeightV()));
    System.out.println("t5 sub Apple for Fig = " + t5.accept(new tSubstV(new Apple(),
                                                                          new Fig())));
    System.out.println("t5 count Figs = " + t5.accept(new iOccursV(new Fig())));
    System.out.println("t5 is flat = " + t5.accept(new IsFlatV()));
    System.out.println("t5 is split = " + t5.accept(new IsSplitV()));
    System.out.println("t5 count Figs = " + t5.accept(new OccursV(new Fig())));

    System.out.println("-----------------------------");
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
    System.out.println("t6 height = " + t6.accept(new iHeightV()));
    System.out.println("t6 sub Apple for Fig = " + t6.accept(new tSubstV(new Apple(),
                                                                          new Fig())));
    System.out.println("t6 count Figs = " + t6.accept(new iOccursV(new Fig())));
    System.out.println("t6 is flat = " + t6.accept(new IsFlatV()));
    System.out.println("t6 is split = " + t6.accept(new IsSplitV()));
    System.out.println("t6 count Figs = " + t6.accept(new OccursV(new Fig())));

    System.out.println("-----------------------------");
    TreeD t7 = new Split(new Bud(),
                         new Bud());
    System.out.println(t7.toString());
    System.out.println("t7 is flat = " + t7.accept(new bIsFlatV()));
    System.out.println("t7 is split = " + t7.accept(new bIsSplitV()));
    System.out.println("t7 has fruit = " + t7.accept(new bHasFruitV()));
    System.out.println("t7 height = " + t7.accept(new iHeightV()));
    System.out.println("t7 sub Apple for Fig = " + t7.accept(new tSubstV(new Apple(),
                                                                          new Fig())));
    System.out.println("t7 count Figs = " + t7.accept(new iOccursV(new Fig())));
    System.out.println("t7 is flat = " + t7.accept(new IsFlatV()));
    System.out.println("t7 is split = " + t7.accept(new IsSplitV()));
    System.out.println("t7 count Figs = " + t7.accept(new OccursV(new Fig())));

    System.out.println("-----------------------------");
    TreeD t8 = new Split(new Split(new Flat(new Fig(),
                                            new Bud()),
                                   new Flat(new Fig(),
                                            new Bud())),
                         new Flat(new Fig(),
                                  new Flat(new Lemon(),
                                           new Flat(new Apple(),
                                                    new Bud()))));
    System.out.println(t8.toString());
    System.out.println("t8 is flat = " + t8.accept(new bIsFlatV()));
    System.out.println("t8 is split = " + t8.accept(new bIsSplitV()));
    System.out.println("t8 has fruit = " + t8.accept(new bHasFruitV()));
    System.out.println("t8 height = " + t8.accept(new iHeightV()));
    System.out.println("t8 sub Apple for Fig = " + t8.accept(new tSubstV(new Apple(),
                                                                          new Fig())));
    System.out.println("t8 count Figs = " + t8.accept(new iOccursV(new Fig())));
    System.out.println("t8 is flat = " + t8.accept(new IsFlatV()));
    System.out.println("t8 is split = " + t8.accept(new IsSplitV()));
    System.out.println("t8 count Figs = " + t8.accept(new OccursV(new Fig())));
  }
}