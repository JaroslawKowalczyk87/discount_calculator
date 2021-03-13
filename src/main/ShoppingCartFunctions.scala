package main

import ProductPricesDB.productPrices
import main.DiscountFunctions.ShoppingBasket

object ShoppingCartFunctions {

  val stock = productPrices.keys.toList

  def createShoppingBasket(input: String): ShoppingBasket = {
    val inputTokenized = input.split(" ")
    if (inputTokenized.head != "PriceBasket")  {
      println("Command should start with 'PriceBasket', ignoring the input")
      Map()
    }
    else inputTokenized.tail.filter(stock.contains(_)).groupBy(identity).mapValues(_.length)
  }


  def calculateSubtotal(shoppingBasket: ShoppingBasket): Int =
    shoppingBasket.map(x => x._2 * productPrices.getOrElse(x._1,0)).sum

}
