import main.ProductPricesDB.productPrices
import main.ShoppingCartFunctions.{calculateSubtotal, createShoppingBasket}
import org.scalatest.FunSuite

class ShoppingCartFunctionsSpec extends FunSuite {

  val inputStream0 = "PriceBasket Bread Bread Soup Bread Milk Soup Apples"
  val expectedBasket0 = Map("Bread" -> 3, "Soup" -> 2, "Milk" -> 1, "Apples" -> 1)
  val expectedTotal0 = productPrices.getOrElse("Bread", 0) * 3 +
    productPrices.getOrElse("Soup", 0) * 2 +
    productPrices.getOrElse("Milk", 0) * 1 +
    productPrices.getOrElse("Apples", 0) * 1

  val inputStream1 = "PriceBasket Bread Bread Soup Bread Milk Apples Apples Apples Soup Cocoa SaltVinegarCrisps Apples"
  val expectedBasket1 = Map("Bread" -> 3, "Soup" -> 2, "Milk" -> 1, "Apples" -> 4)
  val expectedTotal1 = productPrices.getOrElse("Bread", 0) * 3 +
    productPrices.getOrElse("Soup", 0) * 2 +
    productPrices.getOrElse("Milk", 0) * 1 +
    productPrices.getOrElse("Apples", 0) * 4

  val incorrectInputStream = "PrinceBasket Bread Bread Soup Milk"

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
