import main.ProductPricesDB.productPrices
import main.ShoppingCartFunctions.{calculateSubtotal, createShoppingBasket}
import org.scalatest.FunSuite

class ShoppingCartFunctionsSpec extends FunSuite {

  val inputStream0 = "PriceBasket bread bread soup bread milk soup apples"
  val expectedBasket0 = Map("bread" -> 3, "soup" -> 2, "milk" -> 1, "apples" -> 1)
  val expectedTotal0 = productPrices.getOrElse("bread", 0) * 3 +
    productPrices.getOrElse("soup", 0) * 2 +
    productPrices.getOrElse("milk", 0) * 1 +
    productPrices.getOrElse("apples", 0) * 1

  val inputStream1 = "PriceBasket bread bread soup bread milk apples apples apples soup Cocoa SaltVinegarCrisps apples"
  val expectedBasket1 = Map("bread" -> 3, "soup" -> 2, "milk" -> 1, "apples" -> 4)
  val expectedTotal1 = productPrices.getOrElse("bread", 0) * 3 +
    productPrices.getOrElse("soup", 0) * 2 +
    productPrices.getOrElse("milk", 0) * 1 +
    productPrices.getOrElse("apples", 0) * 4

  val incorrectInputStream = "PrinceBasket bread bread soup milk"

  test("input should be processed correctly") {
    val basketCreated = createShoppingBasket(inputStream0)
    assert(basketCreated.equals(expectedBasket0))
    assert(calculateSubtotal(basketCreated) == expectedTotal0)
  }

  test("products not in the price DB should be ignored") {
    val basketCreated = createShoppingBasket(inputStream1)
    assert(basketCreated.equals(expectedBasket1))
    assert(calculateSubtotal(basketCreated) == expectedTotal1)
  }

  test("if the input stream doesn't start with 'PriceBasket' empty basket should be returned") {
    val emptyBasket = createShoppingBasket(incorrectInputStream)
    assert(emptyBasket.equals(Map()))
    assert(calculateSubtotal(emptyBasket) == 0)
  }
}
