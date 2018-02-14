package org.hmrc.fruits

import org.scalatest.FlatSpec

class FruitShoppingCartTest extends FlatSpec {

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

  "single apple item in the shopping cart" should "0.6 pounds" in {
    assert(FruitShoppingCart.calculatePrice("Apple") == 0.6)
  }

  "single orange item in the shopping cart" should "0.25 pounds" in {
    assert(FruitShoppingCart.calculatePrice("Orange") == 0.25)
  }

  "multiple shopping items Apple, Apple, Orange, Apple" should "2.05 pounds" in {
    assert(FruitShoppingCart.calculatePrice("Apple, Apple, Orange, Apple") == 2.05)
  }
}
