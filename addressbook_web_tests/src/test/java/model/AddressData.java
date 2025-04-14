package model;

public record AddressData (
        String id,
        String firstname,
        String lastname,
        String address,
        String home,
        String email,
        String email2,
        String email3,
        String mobile,
        String work,
        String secondary) {
    public AddressData() {
        this("", "", "", "", "", "", "", "", "", "", "");

    }

    public AddressData withId(String id) {
        return new AddressData(id , this.firstname, this.lastname, this.address, this.home, this.email, this.email2, this.email3, this.mobile, this.work, this.secondary);
    }

    public AddressData withFirstName(String firstname) {
        return new AddressData(this.id, firstname, this.lastname, this.address, this.home, this.email, this.email2, this.email3, this.mobile, this.work, this.secondary);
    }

    public AddressData withLastName(String lastname) {
        return new AddressData(this.id, this.firstname, lastname, this.address, this.home, this.email, this.email2, this.email3, this.mobile, this.work, this.secondary);
    }

    public AddressData withAddress(String address) {
        return new AddressData(this.id, this.firstname, this.lastname, address, this.home, this.email, this.email2, this.email3, this.mobile, this.work, this.secondary);
    }

    public AddressData withHome(String home) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, home, this.email, this.email2, this.email3, this.mobile, this.work, this.secondary);
    }

    public AddressData withEmail(String email) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, email, this.email2, this.email3, this.mobile, this.work, this.secondary);
    }

    public AddressData withEmail2(String email2) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, email2, this.email3, this.mobile, this.work, this.secondary);
    }

    public AddressData withEmail3(String email3) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, email2, email3, this.mobile, this.work, this.secondary);
    }

    public AddressData withPhoto(String photo) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.email2, this.email3, this.mobile, this.work, this.secondary);
    }


    public AddressData withMobile(String mobile) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.email2, this.email3, mobile, this.work, this.secondary);
    }

    public AddressData withWork(String work) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.email2, this.email3, this.mobile, work, this.secondary);
    }

    public AddressData withSecondary(String secondary) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.email2, this.email3, this.mobile, this.work, secondary);
    }

}