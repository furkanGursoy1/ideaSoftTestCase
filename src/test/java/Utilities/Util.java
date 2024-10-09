package Utilities;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class Util {

    public static void sleep(int second) {
        /**
         * Bir bekleme işlemi yapar.
         *
         * @param second değeri kadar bekler
         */
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void scrollToElement(WebElement element) {
        /**
         * Bir WebElement'e kaydırma işlemi yapar.
         *
         * @param element Kaydırılacak WebElement
         */
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        Util.sleep(1);
    }
}
