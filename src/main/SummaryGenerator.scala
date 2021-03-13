package main

import main.DiscountFunctions.DiscountsList

object SummaryGenerator {

  def generateSummary(subtotal: Int, discounts: DiscountsList): String = {
    val subtotalString = "Subtotal: " + pencesToPounds(subtotal)

    val discountsAvailable = discounts.filter(_._2 > 0)
    val discountsString = {
      if (discountsAvailable.isEmpty) "(No offers available)"
      else discountsAvailable.map(discount => discount._1 + pencesToPounds(discount._2)).mkString("\n")
    }

    val sum_of_discounts = discountsAvailable.map(_._2).sum
    val totalString = "Total price: " + pencesToPounds(subtotal - sum_of_discounts)
    s"""
      |$subtotalString
      |$discountsString
      |$totalString
      |""".stripMargin
  }

  def pencesToPounds(pences: Int): String =
    "£" + pences/100 + "." + "%02d".format(pences%100)

}
