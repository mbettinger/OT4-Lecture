package cat;

public class CatCaller {

    private CatContract contract;

    public CatCaller(CatContract contract) {
        this.contract = contract;
    }

    public void offerYogurt() throws Exception {
        contract.offerYogurt().send();
        System.out.println("You offered your cat some yogurt.");
    }

    public void caress() throws Exception {
        String output = contract.caress().send();
        System.out.println("You caressed your cat.\r\nCat: "+output);
    }
}
