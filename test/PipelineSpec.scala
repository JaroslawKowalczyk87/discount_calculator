import TestUtils.normalized
import main.Pipeline.pipeline
import org.scalatest.FunSuite

class PipelineSpec extends FunSuite {

  test("pipeline should process shopping list correctly") {
    val inputStream0 = "PriceBasket Bread Bread Soup Bread Milk Soup Apples"
    val expectedOutput0 = """Subtotal: £6.00
                            |Apples 10% off: £0.10
                            |Bread half the price if with two soup tins: £0.40
                            |Total price: £5.50""".stripMargin

    val output0 = pipeline(inputStream0)

    assert(normalized(expectedOutput0) == normalized(output0))
  }

  test("pipeline should ignore items not in the product prices DB") {
    val inputStream1 = "PriceBasket Bread Bread Soup Bread Milk Apples Apples Apples Soup Cocoa SaltVinegarCrisps Apples"
    val expectedOutput1 = """Subtotal: £9.00
                            |Apples 10% off: £0.40
                            |Bread half the price if with two soup tins: £0.40
                            |Total price: £8.20""".stripMargin

    val output1 = pipeline(inputStream1)

    assert(normalized(expectedOutput1) == normalized(output1))
  }

  test("pipeline should ignore input with something else than `PriceBasket` at the beginning") {
    val incorrectInputStream = "PrinceBasket Bread Bread Soup Milk"
    val expectedEmptyOutput = """Subtotal: £0.00
                                |(No offers available)
                                |Total price: £0.00""".stripMargin

    val emptyOutput = pipeline(incorrectInputStream)

    assert(normalized(expectedEmptyOutput) == normalized(emptyOutput))
  }

}
