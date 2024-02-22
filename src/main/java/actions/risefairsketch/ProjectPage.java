package actions.risefairsketch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import actions.commons.AbstractPage;
import interfaces.risefairsketch.ProjectsPageInterfaces;

public class ProjectPage extends AbstractPage {
    WebDriver driver;

    public ProjectPage(WebDriver driver_) {
        driver = driver_;
    }

    public void clickAddProject() {
        clickToElement(driver, ProjectsPageInterfaces.BTN_ADDPROJECT);
    }

    public void inputProjectInformation(String projectName, String projectDescription) {
        WebElement projectNameInput = findAnElement(driver, ProjectsPageInterfaces.TXB_TITLE);
        WebElement projectDescriptionInput = findAnElement(driver, ProjectsPageInterfaces.TXB_TYPE);
        WebElement saveButton = findAnElement(driver, ProjectsPageInterfaces.BTN_SAVE_PROJECT);

        System.out.println("Input Title data");
        projectNameInput.sendKeys(projectName);

        System.out.println("Input Project Description");
        projectDescriptionInput.sendKeys(projectDescription);

        System.out.println("Click Save");
        saveButton.click();
    }

    public void verifyProjectIsAddedSuccessfully() {
        waitForElementInvisible(driver, ProjectsPageInterfaces.POPUP_MODAL);
        // You can add assertion here to verify project addition if needed
    }
}
