import main.DiscountFunctions.DiscountsList
import main.SummaryGenerator.{generateSummary, pencesToPounds}
import org.scalatest.FunSuite

class SummaryGeneratorSpec extends FunSuite {

  def normalized(s: String): String = s.replaceAll("(?s)\\s+", " ").trim

  test("summary should be generated as expected") {
    val subtotal = 500
    val discountsList: DiscountsList = List(("First discount: ", 10), ("Second discount: ", 150))

    val summaryExpected =
      """
        |Subtotal: £5.00
        |First discount: £0.10
        |Second discount: £1.50
        |Total price: £3.40
        |""".stripMargin
    val summaryGenerated = generateSummary(subtotal, discountsList)

    assert(normalized(summaryGenerated) == normalized(summaryExpected))

  }

  test("empty discounts list should be handled correctly") {
    val subtotal = 500
    val discountsList: DiscountsList = List()

    val summaryExpected =
      """
        |Subtotal: £5.00
        |(No offers available)
        |Total price: £5.00
        |""".stripMargin
    val summaryGenerated = generateSummary(subtotal, discountsList)

    assert(summaryGenerated == summaryExpected)

  }

  test("pencesToPound functions should work correctly") {
    assert(pencesToPounds(100) == "£1.00")
    assert(pencesToPounds(150) == "£1.50")
    assert(pencesToPounds(1) == "£0.01")
  }


}
