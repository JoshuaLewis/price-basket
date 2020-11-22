package com.example.basket

import com.example.checkout.ConsoleCheckout
import com.example.inventory.{BasicInventory, ShopItem}

class ShoppingBasket(existingBasket: Seq[Long]) extends BasicInventory with ConsoleCheckout {

  private var basket = this.existingBasket

  def getBasket: Seq[Long] = {
    this.basket
  }

  def addToBasket(items: Seq[Long]): Unit = {
    basket = basket ++ items
  }

  /**
    * Takes the current basket and prints the results to the command line.
    * Returns the results per item and combined as shown in the assignment.
    */
  def checkout: Unit = {

    val receipt = finalizeOrder(flattenBasket(basket))
    receipt.foreach(println(_))
    val (total, subTotal, (discountStrings, discountValue)): (BigDecimal, BigDecimal, (Seq[String], BigDecimal)) = receipt.map{ item =>
      (item.total, item.subTotal, item.discount)
    }.foldLeft((BigDecimal(0.00), BigDecimal(0.00), (Seq("(No Discounts)"), BigDecimal(0.00)))){
      case ((a, b, (c,d)),(z, y, (x, t))) => (a+z, b+y, (c.concat(Seq(x)), d+t))
    }

    println(s"\nBasket Sub-Total: ${subTotal}")
    println(s"\nDiscounts Applied:")
    val applied = discountStrings.filter(_ != "(No Discounts)")
    if(applied.isEmpty){
      println("(No Discounts)")
    } else applied.map(println(_))
    println(s"\nTotal Discount: ${discountValue}")
    println(s"Basket Total: ${total}")

  }

  /**
    * Convert the simple Seq of long to a flattened basket using the current inventory.
    *
    * @param basket list of item ids
    * @return Map of item id -> Inventory listing and quantity.
    */
  def flattenBasket(basket: Seq[Long]): Map[Long, (ShopItem, Int)] = {

    val items: Seq[ShopItem] = basket.map(k => getShopItemFromID(k))
    val itemMap = items.groupBy(_.id)
    itemMap.keysIterator.map{ k =>
      val group = itemMap.get(k)
      k -> (group.get.head, group.get.size)
    }.toMap
  }

}
