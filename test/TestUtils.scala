object TestUtils {
  def normalized(s: String): String = s.replaceAll("(?s)\\s+", " ").trim
}
