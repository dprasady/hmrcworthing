package org.hmrc.fruits

import org.scalatest.FlatSpec

class FruitShoppingCartTest extends FlatSpec {

  "empty list" should "throw exception" in {
    val exceptionMsg = intercept[IllegalArgumentException] {
      FruitShoppingCart.calculatePrice("")
    }
    assert(exceptionMsg.getMessage === FruitShoppingCartErrorCodes.EMPTY_CART)
  }
}
