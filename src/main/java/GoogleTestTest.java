import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GoogleTestTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {

    ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @Test
  public void googleTest() {
    driver = new ChromeDriver();
    driver.get("https://www.google.com/");
    driver.manage().window().setSize(new Dimension(1920, 1080));
    driver.findElement(By.name("q")).sendKeys("web test automation");
    driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@id=\'search\']//a)[1]")));
    }
    assertThat(driver.findElement(By.cssSelector(".g:nth-child(1) .LC20lb > span")).getText(), is("5 Automation Testing Tools for Web Applications for 2020 ..."));
  }

  @Test
  public void easyTest(){
    driver = new ChromeDriver();
    driver.get("https://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("web test automation");
    driver.close();
  }

  @After
  public void tearDown() {
    driver.quit();
  }
}
