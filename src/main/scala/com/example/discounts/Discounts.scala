package com.example.discounts

case class Discount(name: String, trigger: Trigger, voucher: Voucher)
case class Voucher(name: String, target: Long, percentageDiscount: Double)
case class Trigger(target: Long, count: Int)


/**
  * Store of current discounts
  * To create a new discount:
  * provide a discount name, a Basket state required to Trigger the discount
  * and a percentage reward.
  * A 241 offer on item code 1L could be created by Trigger(1L, 2) -> Voucher("", 1L, 1.0)
  */
object Discounts {

  val buy2soupGet1HalfPriceBread = Discount("Buy 2 soup get 1 half price bread", Trigger(1L, 2), Voucher("Buy 2 soup get 1 half price bread", 2L, 0.5))
  val tenPercentOffApples = Discount("10% Off Apples", Trigger(4L, 1), Voucher("10% Off Apples", 4L, 0.1))

  val currentDiscounts: Seq[Discount] = Seq(buy2soupGet1HalfPriceBread, tenPercentOffApples)

}