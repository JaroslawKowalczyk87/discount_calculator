package main

object PricePrinter {

  def print(subtotal: Int, discounts: List[(String, Int)]): String = {
    val subtotalString = "Subtotal: " + pencesToPounds(subtotal)
    val discountsString = {
      if (discounts.isEmpty) "(No offers available)"
      else discounts.map(discount => discount._1 + pencesToPounds(discount._2)).mkString("\n")
    }
    val sum_of_discounts = discounts.map(_._2).sum
    val totalString = "Total price: " + pencesToPounds(subtotal - sum_of_discounts)
    s"""
      |$subtotalString
      |$discountsString
      |$totalString
      |""".stripMargin
  }

  def pencesToPounds(pences: Int): String =
    "£" + pences/100 + "." + pences%100

}
