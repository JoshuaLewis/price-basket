package com.example.inventory

import org.scalatest.wordspec.AnyWordSpec

class BasicInventoryTest extends AnyWordSpec {

  val apple: ShopItem = ShopItem(1L, "apple", 0.35)

  "Basic Inventory" should {
    "load apples from description" in {

      val inv = new BasicInventory
      val searchTerms = Seq("Apple", "apple", "aPPLe")

      searchTerms.map(term => assert(inv.getItemCodeFromDescription(term) === Right(apple)))
    }

    "load apples from item code" in {

      val inv = new BasicInventory
      val result = inv.getShopItemFromID(1L)

      assert(result === Right(apple))

    }
  }


}
