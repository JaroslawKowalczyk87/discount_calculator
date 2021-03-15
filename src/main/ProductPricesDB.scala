package main

// In production, this would probably by handled by some database.

object ProductPricesDB {
  val productPrices: Map[String, Int] =
    Map("soup" -> 65, "bread" -> 80, "milk" -> 130, "apples" -> 100)
}
