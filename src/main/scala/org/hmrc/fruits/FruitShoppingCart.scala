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

  def discountOffer(cartItems: String): Double = {
    val (noOfApples: Int, noOfOranges: Int) = getNoOfApplesAndOranges(cartItems)

    val disApples = noOfApples match {
      case apples if apples == 0 => 0
      case apples if apples % 2 == 0 => apples / 2
      case _ => (noOfApples / 2) + 1
    }

    val disOranges = noOfOranges match {
      case oranges if oranges == 0 => 0
      case _ => noOfOranges - (noOfOranges / 3)
    }

    convertToPounds(disApples, disOranges)
  }

  def calculatePrice(cartItems: String): Double = {
    val (noOfApples: Int, noOfOranges: Int) = getNoOfApplesAndOranges(cartItems)
    convertToPounds(noOfApples, noOfOranges)
  }

  private def convertToPounds(noOfApples: Int, noOfOranges: Int) = {
    ((noOfApples * Fruits.Apple.id) + (noOfOranges * Fruits.Orange.id)) / 100.0
  }

  private def getNoOfApplesAndOranges(cartItems: String) = {
    var noOfApples = 0
    var noOfOranges = 0
    validateCart(cartItems).foreach { cartItem =>
      cartItem match {
        case x if x == Fruits.Apple.toString => noOfApples += 1
        case x if x == Fruits.Orange.toString => noOfOranges += 1
        case _ => throw new IllegalArgumentException(s"$cartItem invalid cart item")
      }
    }
    (noOfApples, noOfOranges)
  }
}
