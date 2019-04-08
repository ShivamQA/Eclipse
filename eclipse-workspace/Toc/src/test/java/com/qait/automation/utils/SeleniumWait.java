package com.qait.automation.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;

public class SeleniumWait {

	WebDriver driver;
	WebDriverWait wait;
	FluentWait<WebDriver> fluentwait;

	int timeout;

	public SeleniumWait(WebDriver driver, int timeout) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.fluentwait = new FluentWait<WebDriver>(driver);
		this.timeout = timeout;
	}

	/**
	 * Returns webElement found by the locator if element is visible
	 *
	 * @param locator
	 * @return
	 */
	public WebElement getWhenVisible(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public WebElement getWhenClickable(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleIs(expectedPagetitle)) != null;
	}

	public boolean waitForPageTitleToContain(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleContains(expectedPagetitle)) != null;
	}

	public WebElement waitForElementToBeVisible(WebElement element, long timeout) {
		fluentwait.withTimeout(timeout, TimeUnit.SECONDS);
		fluentwait.pollingEvery(1, TimeUnit.SECONDS);
		fluentwait.ignoring(StaleElementReferenceException.class);
		return (WebElement) fluentwait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElementToBePresent(By locator, long timeout) {
		fluentwait.withTimeout(timeout, TimeUnit.SECONDS);
		fluentwait.pollingEvery(1, TimeUnit.SECONDS);
		fluentwait.ignoring(StaleElementReferenceException.class);
		return (WebElement) fluentwait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	  public void waitForFrameToBeAvailableAndSwitchToIt(By locator) {
	       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	   }
	   public void waitForFrameToBeAvailableAndSwitchToIt(String frameId) {
	       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
	   }
	// public List<WebElement> waitForElementsToBeVisible(List<WebElement>
	// elements) {
	// return (List<WebElement>)
	// wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	// }
	// public List<WebElement> waitForElementsToBePresent(By locator) {
	// return (List<WebElement>)
	// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	// }

	public List<WebElement> waitForElementsToBePresent(By locator, long timeout) {
		if (timeout == 0)
			timeout = 30;
		fluentwait.withTimeout(timeout, TimeUnit.SECONDS);
		fluentwait.pollingEvery(1, TimeUnit.SECONDS);
		fluentwait.ignoring(StaleElementReferenceException.class);
		return (List<WebElement>) fluentwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements, long timeout) {
		if (timeout == 0)
			timeout = 30;
		fluentwait.withTimeout(timeout, TimeUnit.SECONDS);
		fluentwait.pollingEvery(1, TimeUnit.SECONDS);
		fluentwait.ignoring(StaleElementReferenceException.class);
		return (List<WebElement>) fluentwait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public boolean waitForElementToBeInVisible(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) != null;
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
		return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void clickWhenReady(By locator) {
		WebElement element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public void waitForMsgToastToDisappear() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.className("toast-message")).isDisplayed() && i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		}
		resetImplicitTimeout(timeout);
	}

	public void waitForElementToDisappear(WebElement element) {
		int i = 0;
		resetImplicitTimeout(2);
		try {
			while (element.isDisplayed() && i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		}
		resetImplicitTimeout(timeout);
	}

	public void resetImplicitTimeout(int newTimeOut) {
		try {
			driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
		} catch (Exception e) {
		}
	}

	// TODO Implement Wait for page load for page synchronizations
	public void waitForPageToLoadCompletely() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*")));
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	public WebElement waitForElementToBePresent(By locator) {
		return (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public List<WebElement> waitForElementsToBePresent(By locator) {
		return (List<WebElement>) wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public void resetExplicitTimeout(int newTimeOut) {
    	this.wait = new WebDriverWait(driver, newTimeOut);
    } 
	
	public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
	
	public WebElement waitForElementToBeVisible(WebElement elements) {
        return wait.until(ExpectedConditions.visibilityOf(elements));
    }
	
	public int getTimeout() {
    	return timeout;
    }
    
}
