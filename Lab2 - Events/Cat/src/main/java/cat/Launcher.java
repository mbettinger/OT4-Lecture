package cat;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.Int;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;

import java.math.BigInteger;
import java.util.Scanner;
public class Launcher {
    public static void main(String[] args) throws Exception {

        System.out.println("Trying to deposit a smart contract in the blockchain using java and web3j...");

        CatDeployer deployer = new CatDeployer();
        CatContract contract = deployer.deployContract();

        System.out.println("Contract transmitted to blockchain");

        CatCaller caller = new CatCaller(contract);

        final EthFilter ethFilter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,
                contract.getContractAddress());

        ethFilter.addSingleTopic(EventEncoder.encode(contract.FED_EVENT));// Specify the listened event
        ethFilter.addOptionalTopics("0x" + TypeEncoder.encode(new Int(contract.MIN_SICK().send()))); // Add conditions on indexed event parameters

        contract
                .fedEventFlowable(ethFilter)
                .subscribe(event -> {
                    final BigInteger yogurts = event.yogurts;
                    System.out.println("You notice that your cat gives you a weird look...");
                });

        Scanner myObj = new Scanner(System.in);
        int choice=0;
        do{
            System.out.println("What do you want to do?");
            System.out.println("1 - Offer some yogurt to your cat.");
            System.out.println("2 - Caress your cat.");
            System.out.println();
            System.out.println("0 - Leave...");
            try{
                choice = Integer.parseInt(myObj.nextLine());  // Read user input

                switch(choice){
                    case 1:
                        caller.offerYogurt();
                        break;
                    case 2:
                        caller.caress();
                        break;
                    case 0:
                        System.out.println("You left. Goodbye.");
                        break;
                    default:
                        System.out.println("What you want to do is FORBIDDEN!");
                        break;
                }
            }
            catch (Exception e){
                System.out.println(e.toString());
                System.out.println("What you want to do is FORBIDDEN!");
            }
        }while (choice!=0);

        System.out.println("Interacted with contract in blockchain.");
    }
}

