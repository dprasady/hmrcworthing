package org.hmrc.fruits

import org.scalatest.FlatSpec

class FruitShoppingCartTest extends FlatSpec {

  val apple = Fruits.Apple.toString
  val orange = Fruits.Orange.toString

  "empty list" should "throw exception" in {
    val exceptionMsg = intercept[IllegalArgumentException] {
      FruitShoppingCart.calculatePrice("")
    }
    assert(exceptionMsg.getMessage == FruitShoppingCartErrorCodes.EMPTY_CART)
  }

  "null list" should "throw exception" in {
    val exceptionMsg = intercept[IllegalArgumentException] {
      FruitShoppingCart.calculatePrice(null)
    }
    assert(exceptionMsg.getMessage == FruitShoppingCartErrorCodes.EMPTY_CART)
  }

  "single apple item in the shopping cart" should "return 0.6 pounds" in {
    assert(FruitShoppingCart.calculatePrice(s"$apple") == 0.6)
  }

  "single orange item in the shopping cart" should "return 0.25 pounds" in {
    assert(FruitShoppingCart.calculatePrice(s"$orange") == 0.25)
  }

  "wrong item in the shopping cart" should "throw exception sample invalid cart item" in {
    val exceptionMsg = intercept[IllegalArgumentException] {
      FruitShoppingCart.calculatePrice("test")
    }
    assert(exceptionMsg.getMessage == "test invalid cart item")
  }

  "multiple shopping items Apple, Apple, Orange, Apple" should "return 2.05 pounds" in {
    assert(FruitShoppingCart.calculatePrice(s"$apple, $apple, $orange, $apple") == 2.05)
  }

  "multiple shopping with invalid item Apple, Apple, dummy, Orange, Apple" should "throw dummy invalid cart item" in {
    val exceptionMsg = intercept[IllegalArgumentException] {
      FruitShoppingCart.calculatePrice(s"$apple, $apple, dummy, $orange, $apple")
    }
    assert(exceptionMsg.getMessage == "dummy invalid cart item")
  }

  "discounted apple pricing with one item" should "return 0.6 pounds" in {
    assert(FruitShoppingCart.discountOffer(s"$apple") == 0.6)
  }

  "discounted apple pricing with two items" should "return 0.6 pounds after the discount" in {
    assert(FruitShoppingCart.discountOffer(s"$apple, $apple") == 0.6)
  }

  "discounted apple pricing with three items" should "return 1.2 pounds after the discount" in {
    assert(FruitShoppingCart.discountOffer(s"$apple, $apple, $apple") == 1.2)
  }

  "discounted apple pricing with four items" should "return 1.2 pounds after the discount" in {
    assert(FruitShoppingCart.discountOffer(s"$apple, $apple, $apple, $apple") == 1.2)
  }

  "discounted orange pricing with one item" should "return 0.25 pounds" in {
    assert(FruitShoppingCart.discountOffer(s"$orange") == 0.25)
  }

  "discounted orange pricing with two items" should "return 0.50 pounds after the discount" in {
    assert(FruitShoppingCart.discountOffer(s"$orange, $orange") == 0.5)
  }

  "discounted orange pricing with three items" should "return 0.50 pounds after the discount" in {
    assert(FruitShoppingCart.discountOffer(s"$orange, $orange, $orange") == 0.5)
  }

  "discounted orange pricing with four items" should "return 0.75 pounds after the discount" in {
    assert(FruitShoppingCart.discountOffer(s"$orange, $orange, $orange, $orange") == 0.75)
  }

  "discounted orange pricing with six items" should "return 0.75 pounds after the discount" in {
    assert(FruitShoppingCart.discountOffer(s"$orange, $orange, $orange, $orange, $orange, $orange") == 1.0)
  }

  "discounted apple and orange pricing" should "return 0.75 pounds after the discount" in {//1.2 + 0.5
    assert(FruitShoppingCart.discountOffer(s"$apple, $apple, $apple, $orange, $orange, $orange") == 1.7)
  }

}
