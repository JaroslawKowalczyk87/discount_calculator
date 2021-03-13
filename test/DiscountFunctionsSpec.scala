import main.DiscountFunctions.{apples10pcOff, applyAllDiscounts, breadHalfPriceFor2SoupTins}
import main.ProductPricesDB.productPrices
import org.scalatest.FunSuite

class DiscountFunctionsSpec extends FunSuite {

  test("10 percent of apple price is reduced") {
    for (i <- 1 to 5) {
      val shoppingBasket = Map("Apples" -> i)
      val discountOnApples = apples10pcOff(shoppingBasket)
      assert(discountOnApples._1 == "Apples 10% off: ")
      assert(discountOnApples._2 == i * productPrices.getOrElse("Apples",0) / 10)
    }

  }

  test("bread discount works for multiplication of soup can pairs") {
    for (i <- 1 to 5) {
      val shoppingBasket = Map("Bread" -> i, "Soup" -> 2*i)
      val discountOnBread = breadHalfPriceFor2SoupTins(shoppingBasket)
      assert(discountOnBread._1 == "Bread half the price if with two soup tins: ")
      assert(discountOnBread._2 == i * productPrices.getOrElse("Bread", 0) / 2)
    }
  }

  test("odd number of cans don't affect the discount") {
    for (i <- 1 to 5) {
      val numOfSoups = i * 2 + 1
      val shoppingBasket = Map("Bread" -> i, "Soup" -> numOfSoups)
      val discountOnBread = breadHalfPriceFor2SoupTins(shoppingBasket)
      assert(discountOnBread._1 == "Bread half the price if with two soup tins: ")
      assert(discountOnBread._2 == i * productPrices.getOrElse("Bread", 0) / 2)
    }
  }

  test("discount is only applied as many times, as there is bread in the basket") {
    for (i <- 1 to 5) {
      val numOfSoups = (i + 1) * 2
      val shoppingBasket = Map("Bread" -> i, "Soup" -> numOfSoups)
      val discountOnBread = breadHalfPriceFor2SoupTins(shoppingBasket)
      assert(discountOnBread._1 == "Bread half the price if with two soup tins: ")
      assert(discountOnBread._2 == i * productPrices.getOrElse("Bread", 0) / 2)
    }
  }

  test("apply all discounts") {
    val number_of_bread = 1
    val number_of_soups = 2
    val number_of_apples = 3
    val shoppingBasket = Map("Bread" -> number_of_bread, "Soup" -> number_of_soups, "Apples" -> number_of_apples)

    val discounts = applyAllDiscounts(shoppingBasket)
    assert(discounts.length == 2)
    assert(discounts(0)._1 == "Apples 10% off: ")
    assert(discounts(0)._2 == number_of_apples * productPrices.getOrElse("Apples", 0) / 10)
    assert(discounts(1)._1 == "Bread half the price if with two soup tins: ")
    assert(discounts(1)._2 == number_of_bread * productPrices.getOrElse("Bread", 0) / 2)
  }
}
