package com.example.checkout

import com.example.discounts.Voucher
import com.example.inventory.ShopItem
import org.scalatest.wordspec.AnyWordSpec

class ConsoleCheckoutTest extends AnyWordSpec with ConsoleCheckout {

  val soup: ShopItem = ShopItem(1L, "soup", 0.30)
  val apple: ShopItem = ShopItem(4L, "apples", 0.60)

  "checkDiscounts" should {
    "get 1 bread voucher" in {
      val twoSoup = Map(1L -> (soup, 2))
      val vouchers = checkDiscounts(twoSoup)
      assert(vouchers.size == 1)
    }
    "still get 1 bread voucher" in {
      val soups = Map(1L -> (soup, 3))
      val vouchers = checkDiscounts(soups)
      assert(vouchers.size == 1)
    }
    "get 50 bread voucher" in {
      val soups = Map(1L -> (soup, 100))
      val vouchers = checkDiscounts(soups)
      assert(vouchers.size == 50)
    }
    "get apple vouchers = number of apples" in {
      val twoSoup = Map(4L -> (apple, 100))
      val vouchers = checkDiscounts(twoSoup)
      assert(vouchers.size == 100)
    }
  }

  "applyVouchers" should {
    // 20% off item 4L
    val singleVoucher = Voucher("testVoucher", 4L, 0.2)

    "Apply one 20% off voucher to many target items" in {
      val result = applyVouchers((apple, 10), Seq(singleVoucher))
      assert(result.discount._2 == apple.unitPrice * 0.2)
      assert(result.subTotal == apple.unitPrice * 10)
      assert(result.total == 5.88)
    }
  }

}
