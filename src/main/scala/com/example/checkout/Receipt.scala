package com.example.checkout

import com.example.inventory.ShopItem

case class Receipt(item: ShopItem, subTotal: BigDecimal, discount: (String, BigDecimal), total: BigDecimal){
  override def toString: String =
    s"""
       |${item.description} (\u00A34${item.unitPrice.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)} each)
       |Subtotal:\u00A34${subTotal.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)}
       |${discount._1}
       |Total price \u00A34${total.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)}
       |""".stripMargin
}