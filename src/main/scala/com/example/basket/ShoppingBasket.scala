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

  def checkout: Unit = {

    finalizeOrder(flattenBasket(basket)).foreach(println(_))

  }

  def flattenBasket(basket: Seq[Long]): Map[Long, (ShopItem, Int)] = {

    val items: Seq[ShopItem] = basket.map(k => getShopItemFromID(k))
    val itemMap = items.groupBy(_.id)
    itemMap.keysIterator.map{ k =>
      val group = itemMap.get(k)
      k -> (group.get.head, group.get.size)
    }.toMap
  }

}
