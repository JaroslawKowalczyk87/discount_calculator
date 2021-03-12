package main

import ProductPricesDB.productPrices

object ShoppingCartFunctions {

  val stock = productPrices.keys.toList

  val deleteProductsNotInStock: Map[String, Int] => Map[String, Int] =
    _.filter(x => stock.contains(x._1))

  val calculateSubtotal: Map[String, Int] => Int =
    shoppingCart => shoppingCart.map(x => x._2 * productPrices.getOrElse(x._1,0)).sum

}
