package com.example.application

import com.example.basket.ShoppingBasket
import com.example.inventory.BasicInventory

object PriceBasket {
  def main(args: Array[String]): Unit = {

    val inventory = new BasicInventory
    val (errors, list) = args.map(inventory.getItemCodeFromDescription(_)).partitionMap(identity)

    // Let user know why items are missing from the bill.
    if(errors.nonEmpty){
      println("Please check spelling:")
      errors.foreach(println(_))
    }

    // run application for valid inputs.
    val shop = new ShoppingBasket(list)

    shop.checkout
  }
}
