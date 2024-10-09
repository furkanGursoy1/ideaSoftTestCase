Feature: Sepete ürün ekleme

  @reg
  Scenario: Kullanici basarili bir sekilde searchte arama yapabilir ve sepete 5 adet urun ekleyebilmelidir.
    Given Kullanici anasayfada
    When Kullanici "ürün" kelimesi ile arama yapar
    And Kullanici arama sayfasina gider
    And Kullanici urun detay sayfasina gider
    And Kullanici "5" adet urunu sepete ekler
    And Kullanici sepete gider
    Then Kullanici basarili bir sekilde sepetinde urunleri gorur
