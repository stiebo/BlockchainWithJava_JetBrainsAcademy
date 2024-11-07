package blockchain.miner;

import blockchain.trader.Trader;
import blockchain.blockchain.Block;
import blockchain.blockchain.Blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Miner extends Trader implements Callable<Block> {
    private final ArrayList<Block> currBlockchain;
    private final int N;

    public Miner (Blockchain blockchain, String name) {
        super(blockchain, name);
        this.currBlockchain = blockchain.getBlockchain();
        this.N = blockchain.getN();
    }

    @Override
    public Block call() {
        //System.out.printf("Miner #%d start mining.%n", id);
        Block newBlock = new Block(
                currBlockchain.isEmpty() ? 1 : currBlockchain.get(currBlockchain.size() - 1).getId()+1,
                new Date().getTime(),
                currBlockchain.isEmpty() ? "0" : currBlockchain.get(currBlockchain.size() - 1).getHash(),
                blockchain.getData(),
                this.name,
                100
        );
        donateVCs();
        long startTime = System.currentTimeMillis();
        //long timePassed = startTime;
        String prefixString = new String(new char[N]).replace('\0', '0');
        while (!(newBlock.getHash().substring(0, N).equals(prefixString)) &&
                !Thread.currentThread().isInterrupted())  {
            newBlock.setMagic(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
            //newBlock.setMagic(newBlock.getMagic()+1);
            newBlock.setHash(newBlock.applySha256());
            //if ((System.currentTimeMillis() - timePassed) > 1000) {
                //System.out.println("Hello from miner " + name);
            //    timePassed = System.currentTimeMillis();
            //}
        }
        newBlock.setTimeToMine(System.currentTimeMillis() - startTime);
        //System.out.printf("Miner #%d stops mining.%n", id);
        return newBlock;
    }
}
