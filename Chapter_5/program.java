// A Little Java, A Few Patterns
// Chapter 5: Objects are People, Too


// ======================================
abstract class PizzaPieD {
  RemoveAnchoviesV remFn = new RemoveAnchoviesV();
  RemoveFishV rfFn = new RemoveFishV();
  RemoveIntegerV riFn = new RemoveIntegerV();
  RemoveV rFn = new RemoveV();
  SubstituteFishV sfFn = new SubstituteFishV();
  SubstituteIntegerV siFn = new SubstituteIntegerV();
  SubstituteV sFn = new SubstituteV();

  abstract PizzaPieD removeAnchovies();
  abstract PizzaPieD removeFish(FishD f);
  abstract PizzaPieD removeInteger(Integer i);
  abstract PizzaPieD remove(Object o);
  abstract PizzaPieD substituteFish(FishD f1, FishD f2);
  abstract PizzaPieD substituteInteger(Integer i1, Integer i2);
  abstract PizzaPieD substitute(Object o1, Object o2);
}

class Bottom extends PizzaPieD {
  PizzaPieD removeAnchovies(){
    return remFn.forBottom();
  }

  PizzaPieD removeFish(FishD f){
    return rfFn.forBottom(f);
  }

  PizzaPieD removeInteger(Integer i){
    return riFn.forBottom(i);
  }

  PizzaPieD remove(Object o){
    return rFn.forBottom(o);
  }

  PizzaPieD substituteFish(FishD f1, FishD f2){
    return sfFn.forBottom(f1, f2);
  }

  PizzaPieD substituteInteger(Integer i1, Integer i2){
    return siFn.forBottom(i1, i2);
  }

  PizzaPieD substitute(Object o1, Object o2){
    return sFn.forBottom(o1, o2);
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
  PizzaPieD removeAnchovies(){
    return remFn.forTopping(t, r);
  }

  PizzaPieD removeFish(FishD f){
    return rfFn.forTopping(t, r, f);
  }

  PizzaPieD removeInteger(Integer i){
    return riFn.forTopping(t, r, i);
  }

  PizzaPieD remove(Object o){
    return rFn.forTopping(t, r, o);
  }

  PizzaPieD substituteFish(FishD f1, FishD f2){
    return sfFn.forTopping(t, r, f1, f2);
  }

  PizzaPieD substituteInteger(Integer i1, Integer i2){
    return siFn.forTopping(t, r, i1, i2);
  }

  PizzaPieD substitute(Object o1, Object o2){
    return sFn.forTopping(t, r, o1, o2);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + t + ", " + r + ")";
  }
}

class RemoveAnchoviesV {
  PizzaPieD forBottom(){
    return new Bottom();
  }

  PizzaPieD forTopping(Object t, PizzaPieD r){
    if (new Anchovy().equals(t)){
      return r.removeAnchovies(); 
    } else {
      return new Topping(t, r.removeAnchovies());
    }
  }
}

class RemoveFishV {
  PizzaPieD forBottom(FishD f){
    return new Bottom();
  }

  PizzaPieD forTopping(Object t, PizzaPieD r, FishD f){
    if (f.equals(t)){
      return r.removeFish(f); 
    } else {
      return new Topping(t, r.removeFish(f));
    }
  }
}

class RemoveIntegerV {
  PizzaPieD forBottom(Integer i){
    return new Bottom();
  }

  PizzaPieD forTopping(Object t, PizzaPieD r, Integer i){
    if (i.equals(t)){
      return r.removeInteger(i); 
    } else {
      return new Topping(t, r.removeInteger(i));
    }
  }
}

class RemoveV {
  PizzaPieD forBottom(Object o){
    return new Bottom();
  }

  PizzaPieD forTopping(Object t, PizzaPieD r, Object o){
    if (o.equals(t)){
      return r.remove(o); 
    } else {
      return new Topping(t, r.remove(o));
    }
  }
}

class SubstituteFishV {
  PizzaPieD forBottom(FishD f1, FishD f2){
    return new Bottom();
  }

  PizzaPieD forTopping(Object t, PizzaPieD r, FishD f1, FishD f2){
    if (f1.equals(t)){
      return new Topping(f2, r.substituteFish(f1, f2));
    } else {
      return new Topping(t, r.substituteFish(f1, f2));
    }
  }
}

class SubstituteIntegerV {
  PizzaPieD forBottom(Integer i1, Integer i2){
    return new Bottom();
  }

  PizzaPieD forTopping(Object t, PizzaPieD r, Integer i1, Integer i2){
    if (i1.equals(t)){
      return new Topping(i2, r.substituteInteger(i1, i2));
    } else {
      return new Topping(t, r.substituteInteger(i1, i2));
    }
  }
}

class SubstituteV {
  PizzaPieD forBottom(Object o1, Object o2){
    return new Bottom();
  }

  PizzaPieD forTopping(Object t, PizzaPieD r, Object o1, Object o2){
    if (o1.equals(t)){
      return new Topping(o2, r.substitute(o1, o2));
    } else {
      return new Topping(t, r.substitute(o1, o2));
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
    System.out.println("Remove Anchovies: " + t.removeAnchovies().toString());
    System.out.println("Remove Fish(Tuna): " + t.removeFish(new Tuna()).toString());
    System.out.println("Remove Integer(5): " + t.removeInteger(new Integer(5)).toString());
    System.out.println("Remove(Tuna): " + t.remove(new Tuna()).toString());
    System.out.println("Remove(Anchovy): " + t.remove(new Anchovy()).toString());
    System.out.println("Remove(Integer(5)): " + t.remove(new Integer(5)).toString());
    System.out.println("Remove(Zero): " + t.remove(new Zero()).toString());
    System.out.println("Substitute Fish(Anchovy, Tuna): " + t.substituteFish(new Anchovy(), new Tuna()).toString());
    System.out.println("Substitute Integer(Integer(3), Integer(17)): " + t.substituteInteger(new Integer(3), new Integer(17)).toString());
    System.out.println("Substitute(Anchovy, Salmon): " + t.substitute(new Anchovy(), new Salmon()).toString());
    System.out.println("Substitute(Integer(3), Zero)): " + t.substitute(new Integer(3), new Zero()).toString());

    System.out.println("----------------------------");    

    Topping t2 = new Topping(new Anchovy(),
                  new Topping(new Tuna(),
                   new Topping(new Anchovy(),
                    new Bottom())));
    System.out.println(t2.toString());
    System.out.println("Remove Anchovies: " + t2.removeAnchovies().toString());
    System.out.println("Remove Fish(Tuna): " + t2.removeFish(new Tuna()).toString());
    System.out.println("Remove Integer(5): " + t2.removeInteger(new Integer(5)).toString());
    System.out.println("Remove(Tuna): " + t2.remove(new Tuna()).toString());
    System.out.println("Remove(Anchovy): " + t2.remove(new Anchovy()).toString());
    System.out.println("Remove(Integer(5)): " + t2.remove(new Integer(5)).toString());
    System.out.println("Remove(Zero): " + t2.remove(new Zero()).toString());
    System.out.println("Substitute Fish(Anchovy, Tuna): " + t2.substituteFish(new Anchovy(), new Tuna()).toString());
    System.out.println("Substitute Integer(Integer(3), Integer(17)): " + t2.substituteInteger(new Integer(3), new Integer(17)).toString());
    System.out.println("Substitute(Anchovy, Salmon): " + t2.substitute(new Anchovy(), new Salmon()).toString());
    System.out.println("Substitute(Integer(3), Zero)): " + t2.substitute(new Integer(3), new Zero()).toString());

    System.out.println("----------------------------");    

    Topping t3 = new Topping(new Integer(2),
                  new Topping(new Integer(5),
                   new Topping(new Integer(2),
                    new Bottom())));
    System.out.println(t3.toString());
    System.out.println("Remove Anchovies: " + t3.removeAnchovies().toString());
    System.out.println("Remove Fish(Tuna): " + t3.removeFish(new Tuna()).toString());
    System.out.println("Remove Integer(5): " + t3.removeInteger(new Integer(5)).toString());
    System.out.println("Remove(Tuna): " + t3.remove(new Tuna()).toString());
    System.out.println("Remove(Anchovy): " + t3.remove(new Anchovy()).toString());
    System.out.println("Remove(Integer(5)): " + t3.remove(new Integer(5)).toString());
    System.out.println("Remove(Zero): " + t3.remove(new Zero()).toString());
    System.out.println("Substitute Fish(Anchovy, Tuna): " + t3.substituteFish(new Anchovy(), new Tuna()).toString());
    System.out.println("Substitute Integer(Integer(3), Integer(17)): " + t3.substituteInteger(new Integer(3), new Integer(17)).toString());
    System.out.println("Substitute(Anchovy, Salmon): " + t3.substitute(new Anchovy(), new Salmon()).toString());
    System.out.println("Substitute(Integer(3), Zero)): " + t3.substitute(new Integer(3), new Zero()).toString());

    System.out.println("----------------------------");    

    Topping t4 = new Topping(new Anchovy(),
                  new Topping(new Integer(3),
                   new Topping(new Zero(),
                    new Bottom())));
    System.out.println(t4.toString());
    System.out.println("Remove Anchovies: " + t4.removeAnchovies().toString());
    System.out.println("Remove Fish(Tuna): " + t4.removeFish(new Tuna()).toString());
    System.out.println("Remove Integer(5): " + t4.removeInteger(new Integer(5)).toString());
    System.out.println("Remove(Tuna): " + t4.remove(new Tuna()).toString());
    System.out.println("Remove(Anchovy): " + t4.remove(new Anchovy()).toString());
    System.out.println("Remove(Integer(5)): " + t4.remove(new Integer(5)).toString());
    System.out.println("Remove(Zero): " + t4.remove(new Zero()).toString());
    System.out.println("Substitute Fish(Anchovy, Tuna): " + t4.substituteFish(new Anchovy(), new Tuna()).toString());
    System.out.println("Substitute Integer(Integer(3), Integer(17)): " + t4.substituteInteger(new Integer(3), new Integer(17)).toString());
    System.out.println("Substitute(Anchovy, Salmon): " + t4.substitute(new Anchovy(), new Salmon()).toString());
    System.out.println("Substitute(Integer(3), Zero)): " + t4.substitute(new Integer(3), new Zero()).toString());

    System.out.println("----------------------------");    
  }
}