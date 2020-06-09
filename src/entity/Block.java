package entity;

import service.crypto.HashGenerator;
import util.ObjectUtils;
import java.util.*;

public class Block {

    private Integer index;
    private Calendar timestamp;
    private HashMap<String, Object> data;
    private String previousHash;
    private String hash;

    public Block(Integer index, HashMap<String, Object> data, String previousHash)  {
        this.index = index;
        this.timestamp = Calendar.getInstance();
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public Block(HashMap<String,Object> data) {
        this.data = data;
        this.timestamp = Calendar.getInstance();
    }

    public String calculateHash() {

        try {
            String messageToEncode = this.index + this.previousHash + this.timestamp + this.data.toString();
            return HashGenerator.getHash("sha-256", messageToEncode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public HashMap<String, Object> getInfo()  {

        return ObjectUtils.objectMapper(this);

    }

    public String toString() {
        try {
            return this.getInfo().toString();
        } catch (Exception e) {
            return super.toString();
        }

    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Date getTimestamp() {
        return timestamp.getTime();
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
}
