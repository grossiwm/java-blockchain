package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Blockchain {

    private ArrayList<Block> chain;

    public Blockchain() {
        this.chain = new ArrayList<Block>();
        this.chain.add(generateGenesis());
    }

    public boolean isChainValid() {

        for (int i = 1; i < this.chain.size(); i++) {
            Block currentBlock = this.chain.get(i);
            Block previousBlock = this.chain.get(i - 1);

            if (
                    !currentBlock.getHash()
                            .equals(currentBlock.calculateHash()) || !currentBlock.getPreviousHash()
                            .equals(previousBlock.getHash())
            )
            {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Block> getChain() {
        return this.chain;
    }

    public void addBlock(Block block) {
        block.setIndex(this.getLatestBlock().getIndex() + 1);
        block.setPreviousHash(this.getLatestBlock().getHash());
        block.setHash(block.calculateHash());
        this.chain.add(block);
    }

    public Block getLatestBlock() {
        ArrayList<Block> chain = this.chain;
        return chain.get(chain.size() - 1);
    }

    public String toString() {
        HashMap<Integer, Object> result = new HashMap<Integer, Object>();

        for (Block block : this.chain) {
            result.put(block.getIndex(), block.toString());
        }

        return result.toString();
    }

    private Block generateGenesis()  {
        HashMap<String, Object> genesisData  = new HashMap<String, Object>();
        genesisData.put("genesis", true);
        return new Block(0, genesisData, "0");
    }


}
