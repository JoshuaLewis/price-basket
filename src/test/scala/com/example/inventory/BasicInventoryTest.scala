package com.example.inventory

import org.scalatest.wordspec.AnyWordSpec

class BasicInventoryTest extends AnyWordSpec {

  val apple: ShopItem = ShopItem(4L, "apples", 1.00)

  "Basic Inventory" should {
    "load apples from description" in {

      val inv = new BasicInventory
      val searchTerms = Seq("Apples", "apples", "aPPLes")

      searchTerms.map(term => assert(inv.getItemCodeFromDescription(term) === Right(4L)))
    }

    "load apples from item code" in {

      val inv = new BasicInventory
      val result = inv.getShopItemFromID(4L)

      assert(result === apple)

    }
  }


}
