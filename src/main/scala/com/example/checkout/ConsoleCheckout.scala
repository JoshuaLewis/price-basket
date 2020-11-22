package com.example.checkout

import com.example.discounts.Discounts.currentDiscounts
import com.example.discounts.{Discount, Voucher}
import com.example.inventory.ShopItem

trait ConsoleCheckout {

  def getDiscounts: Seq[Discount] = {
    currentDiscounts
  }

  /**
    * Determine whether the basket contains the required items to [Trigger] a
    * voucher reward.
    *
    * @param basket The current basket.
    * @return Any applicable vouchers for the given basket.
    */
  def checkDiscounts(basket: Map[Long, (ShopItem, Int)]): Seq[Voucher] = {

        var vouchers: Seq[Voucher] = Seq()
        val discounts = getDiscounts

        discounts.map{ discount =>
          basket.get(discount.trigger.target) match {
            case None => {}
            case Some(itemSet) => for(v <- 1 to Math.floor(itemSet._2 / discount.trigger.count).toInt){
              vouchers = vouchers :+ discount.voucher
            }
          }
        }

        vouchers

      }

  /**
    * Convert a basket order to a receipt of purchase
    *
    * @param basket The items to provide a receipt for.
    * @return A seq of purchases containing items, prices and discounts
    */
  def finalizeOrder(basket: Map[Long, (ShopItem, Int)]): Seq[Receipt] = {

    // Compare the cart against current discounts
    val applicableDiscounts = checkDiscounts(basket)
    val savings: Seq[Voucher] = applicableDiscounts

    basket.keysIterator.map { i =>
      val item: (ShopItem, Int) = basket.get(i).get
      val applicableVouchers = savings.filter(_.target == i)
      applyVouchers(item, applicableVouchers)
    }.toSeq
  }

  /**
    * Apply as many vouchers as possible to the selected item.
    *
    * @param group An inventory item and the quantity requested
    * @param discounts Potential discounts to apply
    * @return The prices and discounts applied to an item in the basket
    */
  def applyVouchers(group: (ShopItem, Int), discounts: Seq[Voucher]): Receipt = {

    val subTotal: BigDecimal = group._1.unitPrice * group._2

    val discount: (String, BigDecimal) = if (discounts.headOption.isDefined) {
      val sum: BigDecimal = if (discounts.size > group._2) {
        group._2 * group._1.unitPrice * discounts.head.percentageDiscount
      } else {
        discounts.head.percentageDiscount * group._1.unitPrice * discounts.size
      }.setScale(2, BigDecimal.RoundingMode.HALF_EVEN)
      (s"${discounts.head.name}: ${sum}", sum)
    } else ("(No Discounts)", 0.00)

    val total: BigDecimal = subTotal - discount._2

    Receipt(group._1, subTotal, discount, total)

  }

}
