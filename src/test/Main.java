package test;

import entity.*;

import java.util.HashMap;

public class Main {
    public static void main(String args[]) {

        HashMap<String,Object> data1 = new HashMap<String,Object>();
        HashMap<String,Object> data2 = new HashMap<String,Object>();

        data1.put("amount", 1999.98);
        data2.put("amount", 2799.23);
        Blockchain blockchain = new Blockchain();

        blockchain.addBlock(new Block(data1));
        blockchain.addBlock(new Block(data2));

        System.out.println(blockchain.toString());
        System.out.println(blockchain.isChainValid());

    }
}
