// A Little Java, A Few Patterns
// Chapter 4: Come to Our Carousel

// ======================================
abstract class ShishD {
  OnlyOnionsV ooFn = new OnlyOnionsV();
  IsVegetarianV ivFn = new IsVegetarianV();
  abstract Boolean onlyOnions();
  abstract Boolean isVegetarian();
}

class Skewer extends ShishD {
  Boolean onlyOnions(){
    return ooFn.forSkewer();
  }

  Boolean isVegetarian(){
    return ivFn.forSkewer();
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + ")";
  }
}

class Onion extends ShishD {
  ShishD s;

  Onion(ShishD _s){
    s = _s;
  }
  // -----------------------------

  Boolean onlyOnions(){
    return ooFn.forOnion(s);
  }

  Boolean isVegetarian(){
    return ivFn.forOnion(s);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + s + ")";
  }
}

class Lamb extends ShishD {
  ShishD s;

  Lamb(ShishD _s){
    s = _s;
  }
  // -----------------------------

  Boolean onlyOnions(){
    return ooFn.forLamb(s);
  }

  Boolean isVegetarian(){
    return ivFn.forLamb(s);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + s + ")";
  }
}

class Tomato extends ShishD {
  ShishD s;

  Tomato(ShishD _s){
    s = _s;
  }
  // -----------------------------

  Boolean onlyOnions(){
    return ooFn.forTomato(s);
  }

  Boolean isVegetarian(){
    return ivFn.forTomato(s);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + s + ")";
  }
}

class OnlyOnionsV {
  Boolean forSkewer(){
    return true;
  }
  Boolean forOnion(ShishD s){
    return s.onlyOnions();
  }
  Boolean forLamb(ShishD s){
    return false;
  }
  Boolean forTomato(ShishD s){
    return false;
  }
}

class IsVegetarianV {
  Boolean forSkewer(){
    return true;
  }
  Boolean forOnion(ShishD s){
    return s.isVegetarian();
  }
  Boolean forLamb(ShishD s){
    return false;
  }
  Boolean forTomato(ShishD s){
    return s.isVegetarian();
  }
}
// ======================================
abstract class PizzaD {
  RemoveAnchovyV remFn = new RemoveAnchovyV();
  TopAnchovyWithCheeseV topFn = new TopAnchovyWithCheeseV();
  SubstituteAnchovyByCheeseV subFn = new SubstituteAnchovyByCheeseV();
  abstract PizzaD removeAnchovy();
  abstract PizzaD topAnchovyWithCheese();
  abstract PizzaD substituteAnchovyByCheese();
}

class Crust extends PizzaD {
  PizzaD removeAnchovy(){
    return remFn.forCrust();
  }

  PizzaD topAnchovyWithCheese(){
    return topFn.forCrust();
  }

  PizzaD substituteAnchovyByCheese(){
    return subFn.forCrust();
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
    return remFn.forCheese(p);
  }

  PizzaD topAnchovyWithCheese(){
    return topFn.forCheese(p);
  }

  PizzaD substituteAnchovyByCheese(){
    return subFn.forCheese(p);
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
    return remFn.forOlive(p);
  }

  PizzaD topAnchovyWithCheese(){
    return topFn.forOlive(p);
  }

  PizzaD substituteAnchovyByCheese(){
    return subFn.forOlive(p);
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
    return remFn.forAnchovy(p);
  }

  PizzaD topAnchovyWithCheese(){
    return topFn.forAnchovy(p);
  }

  PizzaD substituteAnchovyByCheese(){
    return subFn.forAnchovy(p);
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
    return remFn.forSausage(p);
  }

  PizzaD topAnchovyWithCheese(){
    return topFn.forSausage(p);
  }

  PizzaD substituteAnchovyByCheese(){
    return subFn.forSausage(p);
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
    return remFn.forSpinach(p);
  }

  PizzaD topAnchovyWithCheese(){
    return topFn.forSpinach(p);
  }

  PizzaD substituteAnchovyByCheese(){
    return subFn.forSpinach(p);
  }

  public String toString(){
    return "new " + getClass().getName() + "(" + p + ")";
  }
}

class RemoveAnchovyV {
  PizzaD forCrust(){
    return new Crust();
  }
  PizzaD forCheese(PizzaD p){
    return new Cheese(p.removeAnchovy());
  }
  PizzaD forOlive(PizzaD p){
    return new Olive(p.removeAnchovy());
  }
  PizzaD forAnchovy(PizzaD p){
    return p.removeAnchovy();
  }
  PizzaD forSausage(PizzaD p){
    return new Sausage(p.removeAnchovy());
  }
  PizzaD forSpinach(PizzaD p){
    return new Spinach(p.removeAnchovy());
  }
}

class TopAnchovyWithCheeseV {
  PizzaD forCrust(){
    return new Crust();
  }
  PizzaD forCheese(PizzaD p){
    return new Cheese(p.topAnchovyWithCheese());
  }
  PizzaD forOlive(PizzaD p){
    return new Olive(p.topAnchovyWithCheese());
  }
  PizzaD forAnchovy(PizzaD p){
    return new Cheese(new Anchovy(p.topAnchovyWithCheese()));
  }
  PizzaD forSausage(PizzaD p){
    return new Sausage(p.topAnchovyWithCheese());
  }
  PizzaD forSpinach(PizzaD p){
    return new Spinach(p.topAnchovyWithCheese());
  }
}

class SubstituteAnchovyByCheeseV {
  PizzaD forCrust(){
    return new Crust();
  }
  PizzaD forCheese(PizzaD p){
    return new Cheese(p.substituteAnchovyByCheese());
  }
  PizzaD forOlive(PizzaD p){
    return new Olive(p.substituteAnchovyByCheese());
  }
  PizzaD forAnchovy(PizzaD p){
    return new Cheese(p.substituteAnchovyByCheese());
  }
  PizzaD forSausage(PizzaD p){
    return new Sausage(p.substituteAnchovyByCheese());
  }
  PizzaD forSpinach(PizzaD p){
    return new Spinach(p.substituteAnchovyByCheese());
  }
}

// ======================================
class Main {
  public static void main(String[] args){
    System.out.println("----------------------------");    

    Tomato t = new Tomato(new Onion(new Lamb(new Skewer())));
    System.out.println(t.toString());
    System.out.println("Only Onions?: " + t.onlyOnions().toString());
    System.out.println("Is Vegetarian?: " + t.isVegetarian().toString());

    Onion o = new Onion(new Onion(new Skewer()));    
    System.out.println(o.toString());
    System.out.println("Only Onions?: " + o.onlyOnions().toString());
    System.out.println("Is Vegetarian?: " + o.isVegetarian().toString());

    Onion o2 = new Onion(new Onion(new Tomato(new Skewer())));
    System.out.println(o2.toString());
    System.out.println("Only Onions?: " + o2.onlyOnions().toString());
    System.out.println("Is Vegetarian?: " + o2.isVegetarian().toString());

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

    Olive o3 = new Olive(new Anchovy(new Cheese(new Anchovy(new Crust()))));
    System.out.println(o3.toString());
    System.out.println("Remove Anchovies: " + o3.removeAnchovy().toString());
    System.out.println("Top Anchovies with Cheese: " + o3.topAnchovyWithCheese().toString());
    System.out.println("Substitute Anchovies by Cheese: " + o3.substituteAnchovyByCheese().toString());

    Spinach s = new Spinach(new Sausage(new Anchovy(new Olive(new Cheese(new Crust())))));
    System.out.println(s.toString());
    System.out.println("Remove Anchovies: " + s.removeAnchovy().toString());
    System.out.println("Top Anchovies with Cheese: " + s.topAnchovyWithCheese().toString());
    System.out.println("Substitute Anchovies by Cheese: " + s.substituteAnchovyByCheese().toString());

    System.out.println("----------------------------");    
  }
}