package main

import main.Pipeline.pipeline


object Main extends App {

  inputHandler





  def inputHandler: Unit = {
    println("Please provide us with a shopping list preceded by 'PriceBasket'")
    val input = scala.io.StdIn.readLine()

    if(input == "stop") {
      println("Thanks for stopping by!")
    }
    else {
      val result = pipeline(input)
      println(result)
      inputHandler
    }
  }

}