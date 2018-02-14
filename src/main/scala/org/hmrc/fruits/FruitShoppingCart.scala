package org.hmrc.fruits

object Fruits extends Enumeration {
  val Apple = Value(60)
  val Orange = Value(25)
}

object FruitShoppingCartErrorCodes {
  val EMPTY_CART = "cartItems shouldn't be empty"
}

object FruitShoppingCart {

  import FruitShoppingCartErrorCodes._

  def validateCart(cartItems: String): Array[String] = cartItems match {
    case cartItems if cartItems == null || cartItems.isEmpty =>
      throw new IllegalArgumentException(EMPTY_CART)
    case _ =>
      cartItems.split(",\\s")
  }

  def discountOffer(cartItems: String): Double = ???

  def calculatePrice(cartItems: String): Double = {
    var sum = 0.0
    validateCart(cartItems).foreach { cartItem =>
      cartItem match {
        case x if x == Fruits.Apple.toString => sum += Fruits.Apple.id
        case x if x == Fruits.Orange.toString => sum += Fruits.Orange.id
        case _ => throw new IllegalArgumentException(s"$cartItem invalid cart item")
      }
    }
    sum / 100
  }
}
