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

  def calculatePrice(cartItems: String): Double = {
    cartItems match {
      case cartItems if (cartItems == null || cartItems.isEmpty) =>
        throw new IllegalArgumentException(EMPTY_CART)
      case _ => ???
    }
  }
}
