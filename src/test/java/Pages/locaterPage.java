package Pages;

import Utilities.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class locaterPage extends basePage {

    @FindBy(css = ".logo")
    public WebElement Anasayfalogo;

    @FindBy(css = ".logo>a>img")
    public WebElement imgLogo;

    @FindBy(css = ".search")
    public WebElement searchBox;

    @FindBy(css = ".search>form>button")
    public WebElement searchButton;

    @FindBy(css = "#results-page .showcase-title")
    public WebElement searchPageUrunAdi;

    @FindBy(css = "#results-page .showcase-price-new")
    public WebElement searchPageUrunFiyat;

    @FindBy(css = ".product-title")
    public WebElement urunDetayPageAdi;

    @FindBy(css = ".product-price-old")
    public WebElement urunDetayPageFiyat;

    @FindBy(id = "qty-input")
    public WebElement urunAdetInput;

    @FindBy(css = ".add-to-cart-button")
    public WebElement sepeteEkleBtn;

    @FindBy(css = ".cart-amount")
    public WebElement sepetUrunMiktariIcon;

    @FindBy(css = ".cart-menu")
    public WebElement sepetBtn;

    @FindBy(css = ".cart-item-name>div")
    public WebElement sepetUrunAdi;

    @FindBy(css = ".cart-item-quantity .form-control")
    public WebElement sepetUrunAdet;

    @FindBy(css = "[data-selector='total-amount']")
    public WebElement sepetToplamFiyat;

    public static String sepetUrunToplamFiyat(String fiyat, int toplamUrunAdedi) {
        String fiyatString = fiyat.replace(" TL", "").replace(",", ".");
        BigDecimal fiyatAsDecimal = new BigDecimal(fiyatString);
        BigDecimal toplamUrunAdediAsDecimal = new BigDecimal(toplamUrunAdedi);
        BigDecimal result = fiyatAsDecimal.multiply(toplamUrunAdediAsDecimal);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        String formattedResult = decimalFormat.format(result);
        Constants.SEPETE_EKLENEN_URUN_TOPLAM_FIYATI = formattedResult;
        System.out.println("Sepet toplam fiyat = " + Constants.SEPETE_EKLENEN_URUN_TOPLAM_FIYATI);
        return Constants.SEPETE_EKLENEN_URUN_TOPLAM_FIYATI;
    }

}
