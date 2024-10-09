package StepDefinitions;

import Pages.locaterPage;
import Utilities.Constants;
import Utilities.Driver;
import Utilities.Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

public class stepDefinitions {
    locaterPage LocaterPage = new locaterPage();

    @Given("Kullanici anasayfada")
    public void kullanici_anasayfada() {
        //Kullanici anasayfada gelmesi gereken urli, logo ve logonun linki doğru geliyor mu kontrolünü yapar.
        String currentUrl = Driver.get().getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://testcase.myideasoft.com/");
        Assert.assertTrue(LocaterPage.Anasayfalogo.isEnabled());
        Assert.assertEquals(LocaterPage.imgLogo.getAttribute("src"), "https://ideacdn.net/idea/kh/32/myassets/std_theme_files/tpl-fexx/assets/uploads/logo.png?revision=1653389725");

    }

    @When("Kullanici {string} kelimesi ile arama yapar")
    public void kullanici_kelimesi_ile_arama_yapar(String arananUrun) {
        //Arama kutusunda senaryoda belirtilen stringi aratır.
        //Senaryo üzerinden string degiştirilmesi yeterlidir. Dinamik bir sekilde calısır.
        LocaterPage.searchBox.click();
        JavascriptExecutor js = (JavascriptExecutor) Driver.get();
        js.executeScript("document.querySelector('.search input[name=\"q\"]').value = '" + arananUrun + "';");
        LocaterPage.searchButton.click();
    }

    @When("Kullanici arama sayfasina gider")
    public void kullanici_arama_sayfasina_gider() {
        //Arama sayfasında listelenen ürünün adının ve fiyatını stringlere atar. Listelenen ürüne tıklar.
        Util.scrollToElement(LocaterPage.searchPageUrunAdi);
        Constants.SEARCHPAGE_URUN_ADI = LocaterPage.searchPageUrunAdi.getText();
        Constants.SEARCHPAGE_URUN_FIYAT = LocaterPage.searchPageUrunFiyat.getText();
        System.out.println("Ürün Adı = " + Constants.SEARCHPAGE_URUN_ADI);
        System.out.println("Ürün Fiyatı = " + Constants.SEARCHPAGE_URUN_FIYAT);
        LocaterPage.searchPageUrunAdi.click();
    }

    @When("Kullanici urun detay sayfasina gider")
    public void kullanici_urun_detay_sayfasina_gider() {
        //Arama sayfasındaki ürün adı ve fiyatı ile ürün detay sayfasindaki değerlere eşit mi kontrol eder.
        Assert.assertEquals(Constants.SEARCHPAGE_URUN_ADI, LocaterPage.urunDetayPageAdi.getText());
        Assert.assertEquals(Constants.SEARCHPAGE_URUN_FIYAT, LocaterPage.urunDetayPageFiyat.getText());
    }

    @And("Kullanici {string} adet urunu sepete ekler")
    public void kullaniciAdetUrunuSepeteEkler(String urunAdet) {
        //Senaryoda belirtilen ürün adedi kadar ürünü sepetine ekler. Dinamik bir sekilde calısır.
        //Sağ üst köşede bulunan sepet iconunda ürün adedi yazıyor mu kontrol edilir.
        Constants.SEPETE_EKLENEN_URUN_ADEDI = urunAdet;
        LocaterPage.urunAdetInput.sendKeys(urunAdet);
        System.out.println("Sepete Eklenen Ürün Adedi = " + urunAdet);
        LocaterPage.sepeteEkleBtn.click();
        Util.sleep(2);
        System.out.println("Sepet İconu Ürün Adedi = " + LocaterPage.sepetUrunMiktariIcon.getText());
        Assert.assertEquals(LocaterPage.sepetUrunMiktariIcon.getText(), urunAdet);
    }

    @When("Kullanici sepete gider")
    public void kullanici_sepete_gider() {
        //Sepet Butonuna tiklar
        LocaterPage.sepetBtn.click();
    }

    @Then("Kullanici basarili bir sekilde sepetinde urunleri gorur")
    public void kullaniciBasariliBirSekildeSepetindeUrunleriGorur() {
        //Sepete doğru ürün eklendiğini kontrol eder.
        //Ürün detay sayfasinda belirtilen ürün miktarı kadar sepete eklendi mi kontrol eder.
        //Ürün fiyati doğru şekilde geliyor mu kontrol eder.
        Assert.assertEquals(Constants.SEARCHPAGE_URUN_ADI, LocaterPage.sepetUrunAdi.getText());
        System.out.println("SEPET_URUN_ADEDI = " + LocaterPage.sepetUrunAdet.getAttribute("value"));
        Assert.assertEquals(Constants.SEPETE_EKLENEN_URUN_ADEDI, LocaterPage.sepetUrunAdet.getAttribute("value"));
        int urunAdedi = Integer.parseInt(Constants.SEPETE_EKLENEN_URUN_ADEDI);
        LocaterPage.sepetUrunToplamFiyat(Constants.SEARCHPAGE_URUN_FIYAT, urunAdedi);
        String sepetToplamFiyati = LocaterPage.sepetToplamFiyat.getText().replace(" TL", "").replace(",", ".");
        Assert.assertEquals(sepetToplamFiyati, Constants.SEPETE_EKLENEN_URUN_TOPLAM_FIYATI);
    }
}
