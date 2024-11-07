package blockchain;

import blockchain.blockchain.Blockchain;
import blockchain.messenger.MsgBotExecutor;
import blockchain.miner.Coordinator;
import blockchain.nodes.Nodes;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        Coordinator coordinator = new Coordinator(blockchain);
        MsgBotExecutor msgBotExecutor = new MsgBotExecutor(blockchain);
        msgBotExecutor.activate();
        coordinator.run();
        msgBotExecutor.stop();
    }
}
