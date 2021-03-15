import TestUtils.normalized
import main.Pipeline.pipeline
import org.scalatest.FunSuite

class PipelineSpec extends FunSuite {

  test("pipeline should process shopping list correctly") {
    val inputStream0 = "PriceBasket Bread Bread Soup Bread Milk Soup Apples"
    val expectedOutput0 = """Subtotal: £6.00
                            |apples 10% off: £0.10
                            |bread half the price if with two soup tins: £0.40
                            |Total price: £5.50""".stripMargin

    val output0 = pipeline(inputStream0)

    println(output0)

    assert(normalized(output0) == normalized(expectedOutput0) )
  }

  test("pipeline should ignore items not in the product prices DB") {
    val inputStream1 = "PriceBasket bread BREAD Soup Bread Milk apples Apples Apples soup Cocoa SaltVinegarCrisps apples"
    val expectedOutput1 = """Subtotal: £9.00
                            |apples 10% off: £0.40
                            |bread half the price if with two soup tins: £0.40
                            |Total price: £8.20""".stripMargin

    val output1 = pipeline(inputStream1)

    assert(normalized(output1) == normalized(expectedOutput1))
  }

  test("pipeline should ignore input with something else than `PriceBasket` at the beginning") {
    val incorrectInputStream = "PrinceBasket Bread Bread Soup Milk"
    val expectedEmptyOutput = """Subtotal: £0.00
                                |(No offers available)
                                |Total price: £0.00""".stripMargin

    val emptyOutput = pipeline(incorrectInputStream)

    assert(normalized(emptyOutput) == normalized(expectedEmptyOutput))
  }

}
