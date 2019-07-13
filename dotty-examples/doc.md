

----
Dotty
===============

----

Scala 2.12  
Scala 2.13  
Scala 2.14
Scala 3.0 => Dotty

# Articles de cette présentation #

Article sur Dotty:  
[https://medium.com/@sinisalouc/whats-new-in-scala-3-28d9c11eec30]

Dotty: Dependent Object Type:  
* Dotty: <https://stackoverflow.com/questions/51530856/what-exactly-is-dotty>  
* Pdf: <http://lampwww.epfl.ch/~amin/dot/fool.pdf>


Type singleton:
* <https://dzone.com/articles/design-patterns-using-singleton-types-in-scala>

* <https://jobs.zalando.com/tech/blog/scala-three-experiment/?gh_src=4n3gxh1>

* <https://blogs.oracle.com/sundararajan/misunderstaning-scalas-singleton-types>

Type level programming:
* <http://gigiigig.github.io/tlp-step-by-step/introduction.html>

Dependent type:
* <https://kubuszok.com/2018/kinds-of-types-in-scala-part-3/>


 
  
# Retour sur Scala #

## typage
```
case class LinkUserClient(userId: Long, clientId: Long)  
```

### type ###
```
type UserId = Int
type ClientId = Int
case class LinkUserClient(userId: UserId, clientId: ClientId)  

LinkUserClient(1, 1)
res31: LinkUserClient = LinkUserClient(1,1)
```

javap -c LinkUserClient.class: 
```
public long userId();  
public long clientId();
public LinkUserClient(long, long);  
```

### newtype ###
```
case class UserId(id: Long) extends AnyVal  
case class ClientId(id: Long) extends AnyVal  
case class LinkUserClient(userId: UserId, clientId: ClientId)  
val clientId = ClientId(1)  
val userId = UserId(10)  
  
LinkUserClient(clientId, userId)  
<console>:16: error: type mismatch;  
 found   : UserId
 required: ClientId

val link = LinkUserClient(userId, clientId)

scala> LinkUserClient(userId, clientId)
res3: LinkUserClient = LinkUserClient(UserId(1),ClientId(1))
```

javap -c LinkUserClient.class
```
public LinkUserClient(long, long);
```

## Implicit ##

```
def f(a: Int)(implicit b: Int) = a + b

type myF: implicit b: Int => Int

def f(a: Int): myF ???
```  

## Self type ##
[https://docs.scala-lang.org/tour/self-types.html]  
Self-types are a way to declare that a trait must be mixed into another trait, even though it doesn’t directly extend it. That makes the members of the dependency available without imports.




## Union, Intersection Type ##

### Rappels ###

* trait A
* class A1 extends A
* class A2 extends A
* class A3 extends A

class B extends A1 with A2 with A3  
    $\neq$  
class B extends A1 with A2 with A3  

### Intersection ###
A & B $\equiv$ B & A  
symmétrique  

cf url avec Cas Singleton 

### Union ###
def help(id: UserName | Password)


### Literal singleton type ###

val t: 42 = 42  
:type t  
Int

val a: t.type = 43  
                  Found:    Int(43)  
                  Required: Int(42)(t)  

#### Singleton type ####
Introduction:  
https://blogs.oracle.com/sundararajan/misunderstaning-scalas-singleton-types

```
object a {
    val b = 10L
}
:type a
type W = a
val c: W = 10 //erreur
val c: W = a
```

url: <https://dzone.com/articles/design-patterns-using-singleton-types-in-scala>

#### exemple: calcul matriciel ####
Vérification des tailles pour multiplier deux matrices entre elles:  
<https://jobs.zalando.com/tech/blog/scala-three-experiment/?gh_src=4n3gxh1>  
tester: 
```
type Dim = Singleton & Int

final case class Matrix[A <: Dim, B <: Dim](n: A, m: B) {
  def *[C <: Dim](other: Matrix[B, C]): Matrix[A, C] = 
    Matrix(n, other.m)
}
```
sans A <: Dim  => A: Dim (cf. Int(10) sous type de Int)

et:  
=> Singleton & Int

### Type alias ###

```
type User = {
  val name: String
  val surname: String
}
```


### Type lambda ###

Either[String, ?]  
signifie:  
A => Either[String, A]  
différent de:
Either[String, _]

```
type T[X] = (X, X)
// defined alias type T = [X] => (X, X)  
```

### Dependent Type ###
Rappels TypeLevel programming:  
cf. type class, phantom type, dependent type (Shapeless par exemple):
* <http://gigiigig.github.io/tlp-step-by-step/introduction.html>  

* <https://kubuszok.com/2018/kinds-of-types-in-scala-part-3/>  

Dotty signifie Dependent Object Type  
=> En quoi il se rapproche du langage Idris par exemple ? Ou en est-on ?

Principe:  
une fonction prend une valeur et renvoie une autre valeur:
$f(x) => x$

ou prend un type et renvoie un autre type:
$T: A => B$  
Exemple:  
```type T[A] = Future[Either[String, A]]```

Ici, on mixte les 2, les valeurs et les types en valeur de retour:  
Le type renvoyé dépend de la valeur du résultat.

L'idée est d'ajouter une contrainte supplémentaire sur les types.
cf. exemple matriciel
cf. phantom type (quid relation avec dependent type)

On met en équation les types en quelques sortes. L'incovénient est que le type résultant devient indéterminé => à approfondir

[https://kubuszok.com/2018/kinds-of-types-in-scala-part-3/}]
```
class X {
  type Y = String
  val y: Y = "y"
}

val x1 = new X
val x2 = new X
def y(x: X)(y: x.Y): Unit = ()

y(x1)(x2.y) // no complaints: x1.Y = String = x2.Y
```

```
trait X {
  type Y = String
  val y: Y = "y"
}

class X2 extends X

val x1 = new X2
val x2 = new X2

def y(x: X)(y: x.Y): Unit = ()

y(x1)(x2.y) // no complaints: x1.Y = String = x2.Y
```

```
:kind ({ type T[A] = Either[String, A] })#T
scala.util.Either[String,?]'s kind is F[+A]
```
