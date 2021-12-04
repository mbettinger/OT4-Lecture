package cat;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class CatDeployer {
    private final String LOCATION_SOURCE_ACCOUNT = "\\\\wsl$\\Ubuntu-20.04\\home\\mbettinger\\.ethereum\\VesterChain\\keystore\\UTC--2021-12-03T16-57-22.822667800Z--cf296d8531589629095ce6eba8bbc56959923ee5";
    private final String SOURCE_ACCOUNT_PASSWORD = "1234";

    public CatContract deployContract() throws Exception {

        // Connect to local node
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/

        // Load credentials for accessing wallet of source account
        Credentials credentials = WalletUtils.loadCredentials(SOURCE_ACCOUNT_PASSWORD, LOCATION_SOURCE_ACCOUNT);

        // Gas provider
        BigInteger GAS_PRICE = BigInteger.valueOf(50);
        BigInteger GAS_LIMIT = BigInteger.valueOf(2000000);
        StaticGasProvider gasProvider = new StaticGasProvider(GAS_PRICE, GAS_LIMIT);

        // Deploy the contract in the blockchain
        return CatContract.deploy(web3, credentials, gasProvider).send();
    }
}
