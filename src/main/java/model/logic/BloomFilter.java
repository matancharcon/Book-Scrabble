package model.logic;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.lang.Math;
public class BloomFilter {

     int m_size;
     BitSet m_bit;
     String[] args;
    MessageDigest md;
    public BloomFilter(int size,String... ags)  {
       m_bit=new BitSet(size);
       m_size=size;
        args=ags;
    }

    void add(String ToAdd) {
        int numofbit;
        byte[] bytes = ToAdd.getBytes();
        byte[] digset;
        BigInteger c;

        for (int i = 0; i < args.length; i++) {
            try {
                md = MessageDigest.getInstance(args[i]);
                digset = md.digest(bytes);
                c = new BigInteger(digset);
                numofbit = Math.abs(c.intValue());;
                numofbit = numofbit % m_size;
                m_bit.set(numofbit);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

        boolean contains(String s){
            int numofbit;
            byte[] digset;
            BigInteger c;
            for (int i = 0; i < args.length; i++) {
                try {
                    md = MessageDigest.getInstance(args[i]);
                    digset = md.digest(s.getBytes());
                    c = new BigInteger(digset);
                    numofbit = Math.abs(c.intValue());
                    numofbit = numofbit % m_size;
                    if (!m_bit.get(numofbit)) {
                        return false;
                    }
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }

                }
            return true;
            }
            public String toString(){
              String new_s=new String();
              for(int i=0;i<m_bit.length();i++){
                  if(m_bit.get(i)==false)
                      new_s+="0";
                  else new_s+="1";
              }
              return new_s;

            }






}
