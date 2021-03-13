package main

import main.Pipeline.pipeline


object Main extends App {

  var input = "stuff"
  println("Input, please!")

  while(input != "stop"){
    input = scala.io.StdIn.readLine()
    if(input == "stop") {
      println("Thanks for stopping by!")
    }
    else {
      val result = pipeline(input)
      println(result)
      println("More input!")
    }
  }


}