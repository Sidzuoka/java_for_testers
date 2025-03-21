package model;

public record AddressData (
        String id, String firstname,
        String lastname,
        String address,
        String home,
        String email
) {
    public AddressData() {
        this("", "", "", "", "", "");

    }

    public AddressData withId(String id) {
        return new AddressData(id , this.firstname, this.lastname, this.address, this.home, this.email);
    }

    public AddressData withFirstName(String firstname) {
        return new AddressData(this.id, firstname, this.lastname, this.address, this.home, this.email);
    }

    public AddressData withLastName(String lastname) {
        return new AddressData(this.id, this.firstname, lastname, this.address, this.home, this.email);
    }

    public AddressData withAddress(String address) {
        return new AddressData(this.id, this.firstname, this.lastname, address, this.home, this.email);
    }

    public AddressData withHome(String home) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, home, this.email);
    }

    public AddressData withEmail(String email) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.home, email);
    }
}