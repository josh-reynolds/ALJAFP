// A Little Java, A Few Patterns
// Chapter 3: What's New?

// ======================================
abstract class PizzaD {
  abstract PizzaD removeAnchovy();
  abstract PizzaD topAnchovyWithCheese();
  abstract PizzaD substituteAnchovyByCheese();
}

class Crust extends PizzaD {
  PizzaD removeAnchovy(){
    return new Crust();
  }

  PizzaD topAnchovyWithCheese(){
    return new Crust();
  }

  PizzaD substituteAnchovyByCheese(){
    return new Crust();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Cheese extends PizzaD {
  PizzaD p;

  Cheese(PizzaD _p){
    p = _p;
  }
  // -----------------------------
  PizzaD removeAnchovy(){
    return new Cheese(p.removeAnchovy());
  }

  PizzaD topAnchovyWithCheese(){
    return new Cheese(p.topAnchovyWithCheese());
  }

  PizzaD substituteAnchovyByCheese(){
    return new Cheese(p.substituteAnchovyByCheese());
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + p + ")";
  }
}

class Olive extends PizzaD {
  PizzaD p;

  Olive(PizzaD _p){
    p = _p;
  }
  // -----------------------------
  PizzaD removeAnchovy(){
    return new Olive(p.removeAnchovy());
  }

  PizzaD topAnchovyWithCheese(){
    return new Olive(p.topAnchovyWithCheese());
  }

  PizzaD substituteAnchovyByCheese(){
    return new Olive(p.substituteAnchovyByCheese());
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + p + ")";
  }
}

class Anchovy extends PizzaD {
  PizzaD p;

  Anchovy(PizzaD _p){
    p = _p;
  }
  // -----------------------------
  PizzaD removeAnchovy(){
    return p.removeAnchovy();
  }

  PizzaD topAnchovyWithCheese(){
    return new Cheese(new Anchovy(p.topAnchovyWithCheese()));
  }

  PizzaD substituteAnchovyByCheese(){
    return new Cheese(p.substituteAnchovyByCheese());
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + p + ")";
  }
}

class Sausage extends PizzaD {
  PizzaD p;

  Sausage(PizzaD _p){
    p = _p;
  }
  // -----------------------------
  PizzaD removeAnchovy(){
    return new Sausage(p.removeAnchovy());
  }

  PizzaD topAnchovyWithCheese(){
    return new Sausage(p.topAnchovyWithCheese());
  }

  PizzaD substituteAnchovyByCheese(){
    return new Sausage(p.substituteAnchovyByCheese());
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + p + ")";
  }
}

class Spinach extends PizzaD {
  PizzaD p;

  Spinach(PizzaD _p){
    p = _p;
  }
  // -----------------------------
  PizzaD removeAnchovy(){
    return new Spinach(p.removeAnchovy());
  }

  PizzaD topAnchovyWithCheese(){
    return new Spinach(p.topAnchovyWithCheese());
  }

  PizzaD substituteAnchovyByCheese(){
    return new Spinach(p.substituteAnchovyByCheese());
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + p + ")";
  }
}
// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("----------------------------");    

    Anchovy a = new Anchovy(new Olive(new Anchovy(new Anchovy(new Cheese(new Crust())))));
    System.out.println(a.toString());
    System.out.println("Remove Anchovies: " + a.removeAnchovy().toString());
    System.out.println("Top Anchovies with Cheese: " + a.topAnchovyWithCheese().toString());
    System.out.println("Substitute Anchovies by Cheese: " + a.substituteAnchovyByCheese().toString());

    Cheese c = new Cheese(new Anchovy(new Cheese(new Crust())));
    System.out.println(c.toString());
    System.out.println("Remove Anchovies: " + c.removeAnchovy().toString());
    System.out.println("Top Anchovies with Cheese: " + c.topAnchovyWithCheese().toString());
    System.out.println("Substitute Anchovies by Cheese: " + c.substituteAnchovyByCheese().toString());

    Olive o = new Olive(new Anchovy(new Cheese(new Anchovy(new Crust()))));
    System.out.println(o.toString());
    System.out.println("Remove Anchovies: " + o.removeAnchovy().toString());
    System.out.println("Top Anchovies with Cheese: " + o.topAnchovyWithCheese().toString());
    System.out.println("Substitute Anchovies by Cheese: " + o.substituteAnchovyByCheese().toString());

    Spinach s = new Spinach(new Sausage(new Anchovy(new Olive(new Cheese(new Crust())))));
    System.out.println(s.toString());
    System.out.println("Remove Anchovies: " + s.removeAnchovy().toString());
    System.out.println("Top Anchovies with Cheese: " + s.topAnchovyWithCheese().toString());
    System.out.println("Substitute Anchovies by Cheese: " + s.substituteAnchovyByCheese().toString());

    System.out.println("----------------------------");    
  }
}