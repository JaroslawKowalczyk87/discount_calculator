package main

import main.ShoppingCartFunctions.{calculateSubtotal, createShoppingBasket}
import main.DiscountFunctions.{DiscountsList, ShoppingBasket, applyAllDiscounts}
import main.SummaryGenerator.generateSummary

object Pipeline {

  def pipeline(input: String): String = {
    val shoppingBasket: ShoppingBasket = createShoppingBasket(input)
    val discountsApplied: DiscountsList = applyAllDiscounts(shoppingBasket)
    val subtotal: Int = calculateSubtotal(shoppingBasket)

    generateSummary(subtotal, discountsApplied)
  }

}
