// A Little Java, A Few Patterns
// Chapter 6: Boring Protocols


// ======================================
abstract class PizzaPieD {
  abstract PizzaPieD accept(PizzaPieVisitorI ask);
}

class Bottom extends PizzaPieD {
  PizzaPieD accept(PizzaPieVisitorI ask){
    return ask.forBottom();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Topping extends PizzaPieD {
  Object t;
  PizzaPieD r;

  Topping(Object _t, PizzaPieD _r){
    t = _t;
    r = _r;
  }
  // -----------------------------
  PizzaPieD accept(PizzaPieVisitorI ask){
    return ask.forTopping(t, r);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + t + ", " + r + ")";
  }
}

interface PizzaPieVisitorI {
  PizzaPieD forBottom();
  PizzaPieD forTopping(Object t, PizzaPieD r);
}

class RemoveV implements PizzaPieVisitorI {
  Object o;

  RemoveV(Object _o){
    o = _o;
  }
  // -----------------------------
  public PizzaPieD forBottom(){
    return new Bottom();
  }

  public PizzaPieD forTopping(Object t, PizzaPieD r){
    if (o.equals(t)){
      return r.accept(this); 
    } else {
      return new Topping(t, r.accept(this));
    }
  }
}

class SubstituteV implements PizzaPieVisitorI {
  Object n;
  Object o;

  SubstituteV(Object _n, Object _o){
    n = _n;
    o = _o;
  }
  // -----------------------------
  public PizzaPieD forBottom(){
    return new Bottom();
  }

  public PizzaPieD forTopping(Object t, PizzaPieD r){
    if (o.equals(t)){
      return new Topping(n, r.accept(this));
    } else {
      return new Topping(t, r.accept(this));
    }
  }
}

class LimitedSubstitutionV implements PizzaPieVisitorI {
  int c;
  Object n;
  Object o;

  LimitedSubstitutionV(int _c, Object _n, Object _o){
    c = _c;
    n = _n;
    o = _o;
  }
  // -----------------------------
  public PizzaPieD forBottom(){
    return new Bottom();
  }

  public PizzaPieD forTopping(Object t, PizzaPieD r){
    if (c == 0){
      return new Topping(t, r);
    } else {
      if (o.equals(t)){
        return new Topping(n, r.accept(new LimitedSubstitutionV(c-1, n, o)));
      } else {
        return new Topping(t, r.accept(this));
      }
    }
  }
}
// ======================================
abstract class FishD {
  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
class Anchovy extends FishD {
  public boolean equals(Object o){
    return (o instanceof Anchovy);
  }
}
class Salmon extends FishD {
  public boolean equals(Object o){
    return (o instanceof Salmon);
  }
}
class Tuna extends FishD {
  public boolean equals(Object o){
    return (o instanceof Tuna);
  }
}
// ======================================
abstract class NumD {}

class OneMoreThan extends NumD {
  NumD predecessor;

  OneMoreThan(NumD _p){
    predecessor = _p;
  }
  // -----------------------------

  public boolean equals(Object o){
    if (o instanceof OneMoreThan){
      return (predecessor.equals(((OneMoreThan)o).predecessor));
    } else {
      return false;
    }
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + predecessor + ")";
  }
}

class Zero extends NumD {
  public boolean equals(Object o){
    return (o instanceof Zero);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}
// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("----------------------------");    

    Topping t = new Topping(new Tuna(), new Bottom());
    System.out.println(t.toString());
    System.out.println("Accept(Remove(Tuna)): " + t.accept(new RemoveV(new Tuna())).toString());
    System.out.println("Accept(Remove(Anchovy)): " + t.accept(new RemoveV(new Anchovy())).toString());
    System.out.println("Accept(Remove(Integer(5))): " + t.accept(new RemoveV(new Integer(5))).toString());
    System.out.println("Accept(Remove(Zero)): " + t.accept(new RemoveV(new Zero())).toString());
    System.out.println("Accept(Substitute(Salmon, Anchovy)): " + t.accept(new SubstituteV(new Salmon(), new Anchovy())).toString());
    System.out.println("Accept(Substitute(Zero, Integer(3))): " + t.accept(new SubstituteV(new Zero(), new Integer(3))).toString());

    System.out.println("----------------------------");    

    Topping t2 = new Topping(new Anchovy(),
                  new Topping(new Tuna(),
                   new Topping(new Anchovy(),
                    new Bottom())));
    System.out.println(t2.toString());
    System.out.println("Accept(Remove(Tuna)): " + t2.accept(new RemoveV(new Tuna())).toString());
    System.out.println("Accept(Remove(Anchovy)): " + t2.accept(new RemoveV(new Anchovy())).toString());
    System.out.println("Accept(Remove(Integer(5))): " + t2.accept(new RemoveV(new Integer(5))).toString());
    System.out.println("Accept(Remove(Zero)): " + t2.accept(new RemoveV(new Zero())).toString());
    System.out.println("Accept(Substitute(Salmon, Anchovy)): " + t2.accept(new SubstituteV(new Salmon(), new Anchovy())).toString());
    System.out.println("Accept(Substitute(Zero, Integer(3))): " + t2.accept(new SubstituteV(new Zero(), new Integer(3))).toString());

    System.out.println("----------------------------");    

    Topping t3 = new Topping(new Integer(2),
                  new Topping(new Integer(5),
                   new Topping(new Integer(2),
                    new Bottom())));
    System.out.println(t3.toString());
    System.out.println("Accept(Remove(Tuna)): " + t3.accept(new RemoveV(new Tuna())).toString());
    System.out.println("Accept(Remove(Anchovy)): " + t3.accept(new RemoveV(new Anchovy())).toString());
    System.out.println("Accept(Remove(Integer(5))): " + t3.accept(new RemoveV(new Integer(5))).toString());
    System.out.println("Accept(Remove(Zero)): " + t3.accept(new RemoveV(new Zero())).toString());
    System.out.println("Accept(Substitute(Salmon, Anchovy)): " + t3.accept(new SubstituteV(new Salmon(), new Anchovy())).toString());
    System.out.println("Accept(Substitute(Zero, Integer(3))): " + t3.accept(new SubstituteV(new Zero(), new Integer(3))).toString());

    System.out.println("----------------------------");    

    Topping t4 = new Topping(new Anchovy(),
                  new Topping(new Integer(3),
                   new Topping(new Zero(),
                    new Bottom())));
    System.out.println(t4.toString());
    System.out.println("Accept(Remove(Tuna)): " + t4.accept(new RemoveV(new Tuna())).toString());
    System.out.println("Accept(Remove(Anchovy)): " + t4.accept(new RemoveV(new Anchovy())).toString());
    System.out.println("Accept(Remove(Integer(5))): " + t4.accept(new RemoveV(new Integer(5))).toString());
    System.out.println("Accept(Remove(Zero)): " + t4.accept(new RemoveV(new Zero())).toString());
    System.out.println("Accept(Substitute(Salmon, Anchovy)): " + t4.accept(new SubstituteV(new Salmon(), new Anchovy())).toString());
    System.out.println("Accept(Substitute(Zero, Integer(3))): " + t4.accept(new SubstituteV(new Zero(), new Integer(3))).toString());

    System.out.println("----------------------------");    

    Topping t5 = new Topping(new Anchovy(),
                  new Topping(new Tuna(),
                   new Topping(new Anchovy(),
                    new Topping(new Tuna(),
                     new Topping(new Anchovy(),
                      new Bottom())))));
    System.out.println(t5.toString());
    System.out.println("Accept(Remove(Tuna)): " + t5.accept(new RemoveV(new Tuna())).toString());
    System.out.println("Accept(Limited Substitution(2, Salmon, Anchovy)): " + t5.accept(new LimitedSubstitutionV(2, new Salmon(), new Anchovy())).toString());
  }
}