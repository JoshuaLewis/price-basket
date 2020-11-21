package com.example.inventory

case class ShopItem(id: Long, description: String, unitPrice: BigDecimal){
  override def toString: String = s"Item: ${description}(Each:${unitPrice})"
}
