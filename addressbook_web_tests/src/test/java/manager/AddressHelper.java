package manager;

import model.AddressData;
import org.openqa.selenium.By;

public class AddressHelper extends HelperBase1{

    public AddressHelper(AppMng appMng) {
        super(appMng);

    }

    public void openAddressPage() {
        if (!appMng.isElementPresent1(By.name("firstname"))) {
            click(By.xpath("//a[contains(text(),'add new')]"));
        }
    }

    public boolean isAddressPresent() {
        openHomePage();
        return appMng.isElementPresent1(By.id("2"));
    }

    public void openHomePage() {
        if (!appMng.isElementPresent1(By.linkText("home"))) {
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
        removeSelectedAddress();
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


    private void removeSelectedAddress() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectAddress() {
        click(By.id("2"));
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


    /*
        private void initAddressModification() {
            click(By.xpath("//img[@alt='edit']"));
        }


         */


}
