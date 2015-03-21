/*------------------------------------------------------------------------------
 * Project  : Data Enlightenment
 * Component: data-enlightenment
 * Author   : saynoom
 * Creation : 21.03.2015 01:22:35
 *------------------------------------------------------------------------------
 */
package com.sixgroup.dfi.hackathon.dataenlightenment.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author saynoom
 */
public class InstructionGenerator {

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        if (file.exists())
            throw new IllegalArgumentException("File " + file + " already exists.");
        int amount = Integer.parseInt(args[1]);
        int maxWeight = args.length >= 3 ? Integer.parseInt(args[2]) : 32;

        Random random = new SecureRandom();
        String[] keys = VDF.getKeys().toArray(new String[0]);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < amount; i++) {
                String predecessor = keys[random.nextInt(keys.length)];
                String success = keys[random.nextInt(keys.length)];
                int weight = random.nextInt(maxWeight);

                writer.write(predecessor);
                writer.write(" -> ");
                writer.write(success);
                writer.write(" = ");
                writer.write(Integer.toString(weight));
                writer.write('\n');
            }
        }
    }
}
