package main

import main.DiscountFunctions.{ShoppingBasket, applyAllDiscounts}
import main.ShoppingCartFunctions.{calculateSubtotal, createShoppingBasket}


object Main extends App {

  // TODO add input and output

  val inputString = "PriceBasket Bread Bread Soup Bread Milk Soup Cocoa Cocoa Apples"
  val shoppingBasket: ShoppingBasket = createShoppingBasket(inputString)

  println(shoppingBasket)

  val listOfDiscounts = applyAllDiscounts(shoppingBasket)
  val subtotal = calculateSubtotal(shoppingBasket)

  print(SummaryGenerator.generateSummary(subtotal, listOfDiscounts))

}