package model;

public record AddressData (
        String id,
        String firstname,
        String lastname,
        String address,
        String home,
        String email,
        String mobile,
        String work,
        String secondary) {
    public AddressData() {
        this("", "", "", "", "", "", "", "", "");

    }

    public AddressData withId(String id) {
        return new AddressData(id , this.firstname, this.lastname, this.address, this.home, this.email, this.mobile, this.work, this.secondary);
    }

    public AddressData withFirstName(String firstname) {
        return new AddressData(this.id, firstname, this.lastname, this.address, this.home, this.email, this.mobile, this.work, this.secondary);
    }

    public AddressData withLastName(String lastname) {
        return new AddressData(this.id, this.firstname, lastname, this.address, this.home, this.email, this.mobile, this.work, this.secondary);
    }

    public AddressData withAddress(String address) {
        return new AddressData(this.id, this.firstname, this.lastname, address, this.home, this.email, this.mobile, this.work, this.secondary);
    }

    public AddressData withHome(String home) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, home, this.email, this.mobile, this.work, this.secondary);
    }

    public AddressData withEmail(String email) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, email, this.mobile, this.work, this.secondary);
    }

    public AddressData withPhoto(String photo) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.mobile, this.work, this.secondary);
    }


    public AddressData withMobile(String mobile) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, mobile, this.work, this.secondary);
    }

    public AddressData withWork(String work) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.mobile, work, this.secondary);
    }

    public AddressData withSecondary(String secondary) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, this.mobile, this.work, secondary);
    }

}