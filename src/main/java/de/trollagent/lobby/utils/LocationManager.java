package de.trollagent.lobby.utils;

import de.trollagent.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LocationManager {

    private Lobby lobby;
    private final File file;
    private YamlConfiguration config;
    private ArrayList<String> locations = new ArrayList<>();

    public LocationManager(File file, Lobby lobby) {
        this.file = file;
        File dir = new File("plugins/Lobby");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.lobby = lobby;
        }
        config = config.loadConfiguration(file);

        if (config.get("locations") != null) {
            locations = (ArrayList<String>) config.get("locations");
        }
    }

    public void saveLocation(String name, Location location) {
        String input = location.getWorld().getName() + "~" + location.getX() + "~" + location.getY() + "~" + location.getZ() + "~" + location.getYaw() + "~" + location.getPitch();
        config.set(name + ".Location", input);
        locations.add(name);
        config.set("locations",locations);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean existLocation(String name) {
        return config.contains(name);
    }

    public Location getLocation(String name) {
        String loc = config.getString(name + ".Location");
        String[] data = loc.split("~");
        World world = Bukkit.getWorld(data[0]);
        double x = Double.valueOf(data[1]);
        double y = Double.valueOf(data[2]);
        double z = Double.valueOf(data[3]);
        float yaw = Float.valueOf(data[4]);
        float pitch = Float.valueOf(data[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public ArrayList<String> getLocations(){
        return (ArrayList<String>) config.get("locations");
    }

    public void removeLocation(String name) {
        config.set(name + ".Location", null);
        config.set(name, null);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        locations.remove(name);
        config.set("locations",locations);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
