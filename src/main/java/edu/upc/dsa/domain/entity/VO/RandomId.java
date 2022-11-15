package edu.upc.dsa.domain.entity.VO;

import java.util.UUID;

public class RandomId {
    public static String getId() {
        return UUID.randomUUID().toString();
    }
}
