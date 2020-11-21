package com.example.checkout

import com.example.inventory.ShopItem

case class Receipt(item: ShopItem, subTotal: BigDecimal, discount: (String, BigDecimal), total: BigDecimal){
  override def toString: String =
    s"""
       |${item.description} (£${item.unitPrice.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)} each)
       |Subtotal:£${subTotal.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)}
       |${discount._1}
       |Total price £${total.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)}
       |""".stripMargin
}