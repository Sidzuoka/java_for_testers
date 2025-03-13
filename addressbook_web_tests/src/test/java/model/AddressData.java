package model;

public record AddressData (
        String firstname,
        String lastname,
        String address,
        String home,
        String email
) {
    public AddressData() {
        this("", "", "", "", "");

    }

    public AddressData withFirstName(String firstname) {
        return new AddressData(firstname, this.lastname, this.address, this.home, this.email);
    }

    public AddressData withLastName(String lastname) {
        return new AddressData(this.firstname, lastname, this.address, this.home, this.email);
    }

    public AddressData withAddress(String address) {
        return new AddressData(this.firstname, this.lastname, address, this.home, this.email);
    }

    public AddressData withHome(String home) {
        return new AddressData(this.firstname, this.lastname, this.address, home, this.email);
    }

    public AddressData withEmail(String email) {
        return new AddressData(this.firstname, this.lastname, this.address, this.home, email);
    }
}