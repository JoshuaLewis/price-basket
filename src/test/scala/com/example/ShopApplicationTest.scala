package com.example

import com.example.application.PriceBasket
import org.scalatest.wordspec.AnyWordSpec

class ShopApplicationTest extends AnyWordSpec {

  val app = PriceBasket

  "The application" should {
    "reject invalid inputs" in {
      app.main(Array("cheese", "chocolate"))
    }

    "Accept all the valid items" in {
      app.main(Array("Soup", "milk", "Bread", "Apples"))
    }

    "Apply the specified discounts" in {
      app.main(Array("soup", "soup", "Bread", "bread"))
    }

    "Apply All discounts" in {
      app.main(Array("soup", "soup", "Bread", "bread", "apples", "apples"))
    }

  }




}
