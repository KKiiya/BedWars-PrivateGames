package me.notlewx.privategames.utils;

import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.support.Support;
import java.util.HashMap;

import static me.notlewx.privategames.PrivateGames.support;

public class GeneratorProperties {
    private final Object gen;
    public HashMap<Object, Properties> genProps = new HashMap<>();
    private static final HashMap<IPrivatePlayer, GeneratorProperties> playerGenProps = new HashMap<>();

    public GeneratorProperties(Object gen) {
        this.gen = gen;
        if (support == Support.BEDWARS1058) {
            IGenerator g = (IGenerator) gen;
            genProps.put(g, new Properties(g.getDelay(), g.getAmount(), g.getSpawnLimit()));
        } else {
            com.tomkeuper.bedwars.api.arena.generator.IGenerator g = (com.tomkeuper.bedwars.api.arena.generator.IGenerator) gen;
            genProps.put(g, new Properties((int) g.getDelay(), g.getAmount(), g.getSpawnLimit()));
        }
    }

    public Properties getProperties(Object gen) {
        return genProps.get(gen);
    }
    public Object getGenerator() {
        return this.gen;
    }
    public static GeneratorProperties getGeneratorProperties(IPrivatePlayer player) {
        return playerGenProps.get(player);
    }
    public static void setGeneratorProperties(IPrivatePlayer player, GeneratorProperties props) {
        playerGenProps.put(player, props);
    }
    public static void removeGeneratorProperties(IPrivatePlayer player) {
        playerGenProps.remove(player);
    }

    public static class Properties {
        private int delay;
        private int amount;
        private int spawnLimit;

        public Properties(int delay, int amount, int spawnLimit) {
            this.delay = delay;
            this.amount = amount;
            this.spawnLimit = spawnLimit;
        }

        public int getAmount() {
            return amount;
        }

        public int getDelay() {
            return delay;
        }

        public int getSpawnLimit() {
            return spawnLimit;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public void setSpawnLimit(int spawnLimit) {
            this.spawnLimit = spawnLimit;
        }
    }
}
