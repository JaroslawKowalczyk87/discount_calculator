package main

import ProductPricesDB.productPrices

object DiscountFunctions {

  type ShoppingBasket = Map[String, Int]

  type Discount = (String, Int)

  type DiscountsList = List[Discount]

  type DiscountCalculatorFunction = ShoppingBasket => Discount

  val apples10pcOff: DiscountCalculatorFunction =
    shoppingCart => {
      val numberOfApples = shoppingCart.getOrElse("Apples", 0)
      val priceOfApples = productPrices.getOrElse("Apples", 0)
      ("Apples 10% off: ", numberOfApples * priceOfApples / 10)
    }

  val breadHalfPriceFor2SoupTins: DiscountCalculatorFunction =
    shoppingCart => {
      val couplesOfSoupTins = shoppingCart.getOrElse("Soup", 0)/2
      val numberOfBreadLoafs = shoppingCart.getOrElse("Bread", 0)
      val numberOfBreadLoafsWithDiscount = Math.min(couplesOfSoupTins, numberOfBreadLoafs)
      val priceOfBread = productPrices.getOrElse("Bread", 0)
      ("Bread half the price if with two soup tins: ", numberOfBreadLoafsWithDiscount * priceOfBread / 2)
    }

  val currentDiscounts: List[DiscountCalculatorFunction] = List(apples10pcOff, breadHalfPriceFor2SoupTins)

  val applyAllDiscounts: ShoppingBasket => DiscountsList = basket => currentDiscounts.map(discount => discount(basket))


}
