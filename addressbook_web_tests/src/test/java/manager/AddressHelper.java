package manager;

import io.qameta.allure.Step;
import model.AddressData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

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

    public void createAddressGr(AddressData address, GroupData group) {
        openAddressPage();
        fillAddressForm(address);
        selectGroup(group);
        submitAddressModification();
        returnToHomePage();
    }

    public void addAddressToGr(AddressData address, GroupData group) {
        openHomePage();
        selectAddress(address);
        selectToGroup(group);
        submitAddressAddToGr();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
       new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());//Select - для работы с выпадающими списками
    }

    private void selectToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());//Select - для работы с выпадающими списками
    }

    private void submitAddressAddToGr() {
        click(By.xpath("//input[@value=\'Add to\']"));
    }


    public void removeAddress(AddressData address) {
        openHomePage();
        selectAddress(address);
        removeSelectedAddresses();
        returnToHomePage();
    }

    public void modifyAddress(AddressData address, AddressData modifyAddress) {
        openHomePage();
        initAddressModification(address);
        fillAddressForm(modifyAddress);
        updateAddressModification();
        returnToHomePage();
    }


    private void fillAddressForm(AddressData address) {
        type(By.name("firstname"), address.firstname());
        type(By.name("lastname"), address.lastname());
        type(By.name("address"), address.address());
        type(By.name("home"), address.home());
        type(By.name("email"), address.email());
        //attach(By.name("photo"), address.photo());

    }


    private void removeSelectedAddresses() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectAddress(AddressData address) {
        click(By.cssSelector(String.format("input[value='%s']", address.id())));
    }

    /*
    private void selectAllAddress() {
        click(By.xpath("//input[@onclick=\'MassSelection()\']"));
    }

     */




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

    @Step
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

    public List<AddressData> getList() {
        openHomePage();
        var address = new ArrayList<AddressData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name=entry]"));
        for (var tr : trs) {
            var fieldLastname = tr.findElement(By.cssSelector("td.center + td"));
            var lastname = fieldLastname.getText();
            var fieldFirstName = tr.findElement(By.cssSelector("tr[name=entry] td:nth-child(3)"));
            var firstname = fieldFirstName.getText();
            //System.out.println(lastname);
            var checkbox = tr.findElement(By.cssSelector("input[name='selected[]']"));
            var id = checkbox.getDomAttribute("value");
            //System.out.println(id);
            address.add(new AddressData().withId(id).withLastName(lastname).withFirstName(firstname));

        }
        return address;
    }


        private void initAddressModification(AddressData address) {
            click(By.cssSelector(String.format("a[href='edit.php?id=%s'] img", address.id())));
        }


    public void removeAddressFromGr(AddressData address, GroupData group) {
        openHomePage();
        selectFromGroup(group);
        selectAddress(address);
        submitRemoveAddFromGr();
        returnToHomePage();

    }


    private void selectFromGroup(GroupData group) {
        new Select(manager.driver.findElement(By.xpath("//select[@name=\'group\']"))).selectByValue(group.id());
    }

    private void submitRemoveAddFromGr() {
        click(By.xpath("//input[@name=\'remove\']"));

    }


    public String getPhones(AddressData address) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", address.id()))).getText();
                                                //подъем на два ур. вверх
                                                //td[6] - в строке ищем ячейку слева-направо 6-ую
    }

    public Map<String, String> getPhonesDict() {
        var result = new HashMap<String, String>(); //содержит соотв-е тел. - id
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row: rows) {
            var id = row.findElement(By.tagName("input")).getDomAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }


    public String getOneAddress(AddressData addressData) {
        openHomePage();
        var id = addressData.id();
        var lastname = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[2]", addressData.id()))).getText();
        var firstname = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[3]", addressData.id()))).getText();
        var address = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", addressData.id()))).getText();
        var email = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", addressData.id()))).getText();
        var phone = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", addressData.id()))).getText();

        return (id + "\n" + firstname + "\n" + lastname + "\n" + address + "\n" + email + "\n" + phone);
    }

}
