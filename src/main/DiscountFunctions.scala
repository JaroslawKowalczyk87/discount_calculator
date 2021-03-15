package main

import ProductPricesDB.productPrices

object DiscountFunctions {

  type ShoppingBasket = Map[String, Int]

  type Discount = (String, Int)

  type DiscountsList = List[Discount]

  type DiscountCalculatorFunction = ShoppingBasket => Discount

  val apples10pcOff: DiscountCalculatorFunction =
    shoppingCart => {
      val numberOfApples = shoppingCart.getOrElse("apples", 0)
      val priceOfApples = productPrices.getOrElse("apples", 0)
      ("apples 10% off: ", numberOfApples * priceOfApples / 10)
    }

  val breadHalfPriceFor2SoupTins: DiscountCalculatorFunction =
    shoppingCart => {
      val couplesOfSoupTins = shoppingCart.getOrElse("soup", 0)/2
      val numberOfBreadLoafs = shoppingCart.getOrElse("bread", 0)
      val numberOfBreadLoafsWithDiscount = Math.min(couplesOfSoupTins, numberOfBreadLoafs)
      val priceOfBread = productPrices.getOrElse("bread", 0)
      ("bread half the price if with two soup tins: ", numberOfBreadLoafsWithDiscount * priceOfBread / 2)
    }

  val currentDiscounts: List[DiscountCalculatorFunction] = List(apples10pcOff, breadHalfPriceFor2SoupTins)

  val applyAllDiscounts: ShoppingBasket => DiscountsList = basket => currentDiscounts.map(discount => discount(basket))


}
