package main


object Main extends App {

  val shoppingCart = Map("Apples" -> 1, "Bread" -> 2, "Soup" -> 4, "Milk" -> 1, "Cocoa" -> 5)

  val filteredCart = ShoppingCartFunctions.deleteProductsNotInStock(shoppingCart)

  println(filteredCart)

  val listOfDiscounts = DiscountFunctions.currentDiscounts.map(x => x(filteredCart))
  val subtotal = ShoppingCartFunctions.calculateSubtotal(filteredCart)

  print(PricePrinter.print(subtotal, listOfDiscounts))

}
