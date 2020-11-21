package com.example.inventory

class BasicInventory extends Inventory {

  private val inventory: Map[Long, ShopItem] = Map(
    1L -> ShopItem(1L, "soup", 0.65),
    2L -> ShopItem(2L, "bread", 0.80),
    3L -> ShopItem(3L, "milk", 1.30),
    4L -> ShopItem(4L, "apples", 1.00)
    )
  private val inventoryDesc: Map[String, Long] = inventory.iterator.map(e => e._2.description -> e._1).toMap

  def getShopItemFromID(id: Long): ShopItem = {
    inventory.get(id) match {
      case Some(value) => value
      case None => throw new Exception(s"Item $id not found")
    }
  }

  def getItemCodeFromDescription(desc: String): Either[String, Long] = {
    inventoryDesc.get(desc.toLowerCase) match {
      case Some(value) => Right(inventory.get(value).get.id)
      case None => Left(s"Item ${desc} not found")
    }
  }

}
