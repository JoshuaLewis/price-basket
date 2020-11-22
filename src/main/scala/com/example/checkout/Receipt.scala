package com.example.checkout

import com.example.inventory.ShopItem

case class Receipt(item: ShopItem, subTotal: BigDecimal, discount: (String, BigDecimal), total: BigDecimal){
  override def toString: String =
    s"""
       |${item.description} (\u00A3${item.unitPrice.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)} each)
       |Subtotal:\u00A3${subTotal.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)}
       |${discount._1}
       |Total price \u00A3${total.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)}
       |""".stripMargin
}