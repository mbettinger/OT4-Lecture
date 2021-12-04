pragma solidity ^0.8.10;

contract CatContract {
    uint private nbConsumedYogurts=0;
    uint public constant MIN_HAPPY=1;
    uint public constant MIN_SICK=2;
    event Fed(uint indexed yogurts);

    function offerYogurt() public {
        nbConsumedYogurts++;
        emit Fed(nbConsumedYogurts);
    }
    function caress() public view returns (string memory){
        string memory reaction="BEURK!!! :-X";
        if(nbConsumedYogurts<MIN_HAPPY){
            reaction="HmmmrPurr!?";
        }
        else if(nbConsumedYogurts>=MIN_HAPPY && nbConsumedYogurts<MIN_SICK){
            reaction="MiauHmmmmrrr! <3 <3 <3";
        }
        return reaction;
    }
}