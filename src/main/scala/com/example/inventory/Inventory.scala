package com.example.inventory

abstract class Inventory {

  def getShopItemFromID(id: Long): ShopItem

  def getItemCodeFromDescription(desc: String): Either[String, Long]

}
