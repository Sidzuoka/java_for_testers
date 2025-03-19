package manager;

import model.AddressData;
import org.openqa.selenium.By;

public class AddressHelper extends HelperBase{

    public AddressHelper(ApplicationManager manager) {
        super(manager);

    }

    public void openAddressPage() {
        if (!manager.isElementPresent(By.name("firstname"))) {
            click(By.xpath("//a[contains(text(),'add new')]"));
        }
    }


    public void openHomePage() {
        if (!manager.isElementPresent(By.linkText("home"))) {
            returnToHomePage();
        }
    }

    public void createAddress(AddressData address) {
        openAddressPage();
        fillAddressForm(address);
        submitAddressModification();
        returnToHomePage();
    }

    public void removeAddress() {
        openHomePage();
        selectAddress(); //selectAddress()
        removeSelectedAddresses();
    }

    public void modifyAddress(AddressData modifyAddress) {
        openAddressPage();
        //initAddressModification();
        //fillAddressForm(modifyAddress);
        //updateAddressModification();
        //returnToHomePage();
    }


    private void fillAddressForm(AddressData address) {
        type(By.name("firstname"), address.firstname());
        type(By.name("lastname"), address.lastname());
        type(By.name("address"), address.address());
        type(By.name("home"), address.home());
        type(By.name("email"), address.email());
    }


    private void removeSelectedAddresses() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectAddress() {
        click(By.name("selected[]"));
    }


    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void submitAddressModification() {
        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }

    private void updateAddressModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public int getCountAddress() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllAddress() {
        openHomePage();
        selectAllAddresses();
        removeSelectedAddresses();
    }

    private void selectAllAddresses() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }


    /*
        private void initAddressModification() {
            click(By.xpath("//img[@alt='edit']"));
        }


         */


}
